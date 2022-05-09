package com.pfe22.ava.JwtSecurity;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.pfe22.ava.entities.AppUsers.Userprincipal;
import com.pfe22.ava.constant.SecurityConstant;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import  static  com.pfe22.ava.constant.SecurityConstant.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import  static java.util.Arrays.stream;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JWTTokenProvider {

    @Value( value = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJhdWQiOiJ1c2VyIE1hbmFnZW1lbnQiLCJzdWIiOiJ6eWdhLTAwMSIsImlzcyI6IkFyYWIgVHVuaXNpYW4gQmFuayIsImV4cCI6MTY0NTM3NDIxNCwiaWF0IjoxNjQ0OTQyMjE0LCJhdXRob3JpdGllcyI6WyJSRUFEIiwiVVBEQVRFIiwiQ1JFQVRFIiwiREVMRVRFIl19.Je9OW5hzN0nPP4GavZXikDAfUamWdegGgEFIhLdlOMqjUrIKxSOLRCLVnQzUJbCMt90RCwVD-9zfG94GNo-QaA")
    public String secret;

    public String generateJwtToken(Userprincipal userprincipal){

        String[] claims= getClaimsFromUser(userprincipal);
        return JWT.create().withIssuer(GET_ATB).withAudience(GET_ADMINISTRATION)
                .withIssuedAt(new Date()).withSubject(userprincipal.getUsername()).withArrayClaim(SecurityConstant.AUTHORITIES , claims)
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(secret.getBytes()));

    }

    public  List<GrantedAuthority> getAuthorities(String token){
        String[] claims = getClaimsFromToken(token);
        return stream(claims).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    public Authentication getAuthentication (String username,
                                             List<GrantedAuthority> authorities,
                                             HttpServletRequest request){

        UsernamePasswordAuthenticationToken userpasswordToken = new UsernamePasswordAuthenticationToken(
                username ,  null , authorities
        );
        userpasswordToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        return  userpasswordToken;
    }

    public  boolean isTokenValid(String username , String token){
        JWTVerifier verifier = getJWtverifier();
        return StringUtils.isNotEmpty(username) && !isTokenExpired(verifier, token);

    }
    public  String getSubject(String token){
        JWTVerifier verifier = getJWtverifier();
        return  verifier.verify(token).getSubject();
    }

    private boolean isTokenExpired(JWTVerifier verifier, String token) {
        Date expiration = verifier.verify(token).getExpiresAt();
        return expiration.before(new Date());
    }

    private String[] getClaimsFromToken(String token) {
        JWTVerifier verifier = getJWtverifier();

        return verifier.verify(token).getClaim(AUTHORITIES).asArray(String.class);
    }

    private JWTVerifier getJWtverifier() {
        JWTVerifier verifier ;
        try {
            Algorithm algorithm =HMAC512(secret);
            verifier = JWT.require(algorithm).withIssuer(GET_ATB).build();
        }catch (JWTVerificationException exception){
            throw  new JWTVerificationException(TOKEN_CANNOT_BE_VERIDIED);
        }
        return verifier;
    }


    private String[] getClaimsFromUser(Userprincipal user) {

        List<String> authorities = new ArrayList<>();
        for (GrantedAuthority grantedAuthority :user.getAuthorities()){
            authorities.add(grantedAuthority.getAuthority());
        }
        return authorities.toArray(new String[0]);
    }

}
