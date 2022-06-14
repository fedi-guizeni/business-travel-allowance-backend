package com.pfe22.ava.controller;

import com.pfe22.ava.entities.AppUsers.HttpResponse;
import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.AppUsers.Userprincipal;
import com.pfe22.ava.JwtSecurity.JWTTokenProvider;
import com.pfe22.ava.Service.UserService;
import com.pfe22.ava.exception.AppUsers.EmailExistException;
import com.pfe22.ava.exception.AppUsers.EmailNotFoundException;
import com.pfe22.ava.exception.AppUsers.UserNotFoundException;
import com.pfe22.ava.exception.AppUsers.UsernameExistException;
import com.pfe22.ava.exception.ExceptionHandling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;

import java.util.List;

import static com.pfe22.ava.constant.SecurityConstant.JWT_TOKEN_HEADER;

@RestController
@RequestMapping(path = {"/","/user"})
@CrossOrigin("http://localhost:4200")
public class UserRessource extends ExceptionHandling {
    private UserService userService ;
    private AuthenticationManager authenticationManager;
    private JWTTokenProvider jwtTokenProvider;


    @Autowired
    public UserRessource(UserService userService, AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }




    @PostMapping("/login")
    public ResponseEntity<User> login (@RequestBody User user) {

        authetificate(user.getUsername(),user.getPassword());
        User loginuser= userService.findByUsername(user.getUsername());
        Userprincipal userprincipal= new Userprincipal(loginuser);
        HttpHeaders jwtheader = getJwtHeader(userprincipal);
        return new ResponseEntity<>(loginuser , jwtheader , HttpStatus.OK);


    }



    @PostMapping("/register")
    public ResponseEntity<User> register (@RequestBody User user) throws EmailExistException, UsernameExistException, UserNotFoundException, MessagingException {

        User newUser = userService.register(user.getFirstName(), user.getLastName(),user.getUsername(), user.getEmail());

        return new ResponseEntity<>(newUser , HttpStatus.OK);


    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('APPROVE')")
    public ResponseEntity<User> addNewUser(@RequestParam("firstname") String firstname ,
                                           @RequestParam("lastname") String lastname ,
                                           @RequestParam("username") String username ,
                                           @RequestParam("email") String email ,
                                           @RequestParam("role") String role ,
                                           @RequestParam("isActive") String isActive ,
                                           @RequestParam("isNonLocked") String isNonLocked ) throws UserNotFoundException, EmailExistException, UsernameExistException, MessagingException {

        User newUser = userService.addNewUser(firstname , lastname , username , email , role , Boolean.parseBoolean(isActive)   , Boolean.parseBoolean(isNonLocked));
        return  new ResponseEntity<>(newUser , HttpStatus.OK) ;

    }
    @PostMapping("/update")
    @PreAuthorize("hasAnyAuthority('APPROVE')")
    public ResponseEntity<User> updateUser(@RequestParam("currentUsername") String currentUsername ,
                                           @RequestParam("firstname") String firstname,
                                           @RequestParam("lastname") String lastname ,
                                           @RequestParam("username") String username ,
                                           @RequestParam("email") String email ,
                                           @RequestParam("role") String role ,
                                           @RequestParam("isActive") String isActive ,
                                           @RequestParam("isNonLocked") String isNonLocked ) throws UserNotFoundException, EmailExistException, UsernameExistException {

        User updateUser = userService.updateUser(currentUsername ,firstname , lastname , username , email , role , Boolean.parseBoolean(isActive)   , Boolean.parseBoolean(isNonLocked));
        return  new ResponseEntity<>(updateUser , HttpStatus.OK) ;

    }
    @GetMapping("/find/{username}")
    public  ResponseEntity<User> getUser(@PathVariable("username") String username){
        User user = userService.findByUsername(username);
        return  new ResponseEntity<>(user , HttpStatus.OK) ;


    }

    @GetMapping("/list")
    @PreAuthorize("hasAnyAuthority('APPROVE')")
    public  ResponseEntity<List<User>> getAllUsers() {
      List<User> users = userService.getUsers();
        return  new ResponseEntity<>(users , HttpStatus.OK) ;


    }

    @GetMapping("/resetpassword/{email}")
    //@PreAuthorize("hasAnyAuthority('APPROVE')")
    public  ResponseEntity<HttpResponse>  resetPassword(@PathVariable("email")String email) throws EmailNotFoundException, MessagingException {
        userService.resetPassword(email);
        return  response(HttpStatus.OK , "Email envoyé à : " +email) ;


    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority('APPROVE')")
    public ResponseEntity<HttpResponse> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return response(HttpStatus.OK , "Utilisateur supprimé avec succès");

    }

    private ResponseEntity<HttpResponse> response(HttpStatus httpStatus, String msg) {

        return new  ResponseEntity<>(new HttpResponse(httpStatus.value() ,
                httpStatus,httpStatus.getReasonPhrase().toUpperCase(),
                msg.toUpperCase() ),httpStatus);
    }


    private HttpHeaders getJwtHeader(Userprincipal user) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(JWT_TOKEN_HEADER  , jwtTokenProvider.generateJwtToken(user));
        return headers;
    }

    private void authetificate(String username, String password) {
        authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(username , password));

    }
}
