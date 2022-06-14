package com.pfe22.ava.security;
import com.pfe22.ava.filter.JwtAcessDeniedHandler;
import com.pfe22.ava.filter.JwtAuthontificationEntryPoint;
import com.pfe22.ava.filter.JwtauthorizationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static com.pfe22.ava.constant.SecurityConstant.PUBLIC_URLS;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private JwtauthorizationFilter jwtauthorizationFilter;
    private JwtAcessDeniedHandler jwtAcessDeniedHandler;
    private JwtAuthontificationEntryPoint jwtAuthontificationEntryPoint;
    private UserDetailsService userDetailsService;
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

  @Autowired
  public  WebSecurityConfig (JwtauthorizationFilter jwtauthorizationFilter,
                             JwtAcessDeniedHandler jwtAcessDeniedHandler,
                             JwtAuthontificationEntryPoint jwtAuthontificationEntryPoint,
                             @Qualifier("UserDetailsService")UserDetailsService userDetailsService,
                             BCryptPasswordEncoder bCryptPasswordEncoder){

      this.bCryptPasswordEncoder=bCryptPasswordEncoder;
      this.jwtAcessDeniedHandler=jwtAcessDeniedHandler;
      this.jwtauthorizationFilter=jwtauthorizationFilter;
      this.userDetailsService=userDetailsService;
      this.jwtAuthontificationEntryPoint=jwtAuthontificationEntryPoint;
  }




    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http.csrf().disable().cors().and()
              .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
              .and().authorizeRequests().antMatchers(PUBLIC_URLS).permitAll()
              .anyRequest().authenticated()
              .and()
              .exceptionHandling().accessDeniedHandler(jwtAcessDeniedHandler)
              .authenticationEntryPoint(jwtAuthontificationEntryPoint)
              .and()
              .addFilterBefore(jwtauthorizationFilter , UsernamePasswordAuthenticationFilter.class);



    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean () throws  Exception{
      return super.authenticationManagerBean();
    }
}
