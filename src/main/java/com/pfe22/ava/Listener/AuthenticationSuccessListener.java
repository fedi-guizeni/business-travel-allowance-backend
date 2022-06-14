package com.pfe22.ava.Listener;


import com.pfe22.ava.Service.LoginAttemptService;
import com.pfe22.ava.entities.AppUsers.User;
import com.pfe22.ava.entities.AppUsers.Userprincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationSuccessListener {
    private LoginAttemptService loginAttemptService;

    @Autowired
    public AuthenticationSuccessListener(LoginAttemptService loginAttemptService) {
        this.loginAttemptService = loginAttemptService;
    }

    @EventListener
    public void onAuthSuccess(AuthenticationSuccessEvent event){
        Object principal = event.getAuthentication().getPrincipal();
        if (principal instanceof Userprincipal){
            Userprincipal user =(Userprincipal) event.getAuthentication().getPrincipal();
            loginAttemptService.evicUserFromLoginAttemptCache(user.getUsername());
        }

    }

}
