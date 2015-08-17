package com.rha.rpg.authentication;

import com.rha.rpg.persistence.UserRepository;
import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.ExceptionMappingAuthenticationFailureHandler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Manage/track authentication failures.
 * @author Aaron
 */
@Service
public class AuthenticationFailureHandlerImpl extends ExceptionMappingAuthenticationFailureHandler {

    @Autowired
    private UserRepository userRepositoryImpl;
    
    public AuthenticationFailureHandlerImpl() {
        super();
        Map<String, String> exceptionMappings = new HashMap<>();
        exceptionMappings.put(InternalAuthenticationServiceException.class.getCanonicalName(), "/login.form?message=Sign in unsuccessful.  Please try again");
        exceptionMappings.put(BadCredentialsException.class.getCanonicalName(), "/login.form?message=Sign in unsuccessful.  Please try again");
        exceptionMappings.put(CredentialsExpiredException.class.getCanonicalName(), "/login.form?message=Sign in unsuccessful.  Please try again");
        exceptionMappings.put(LockedException.class.getCanonicalName(), "/login.form?message=Your account has been locked.  Mostly likely this is because you have made too many unsuccessful attempts to sign in.  Please try again after 30 minutes.");
        exceptionMappings.put(DisabledException.class.getCanonicalName(), "/login.form?message=Sign in unsuccessful.  Please try again");
        exceptionMappings.put(AccessDeniedException.class.getCanonicalName(), "/login.form?message=Sign in unsuccessful.  Please try again");       
        setExceptionMappings(exceptionMappings);
    }
    
    @Override
    @Transactional
    public void onAuthenticationFailure(HttpServletRequest hsr, HttpServletResponse hsr1, AuthenticationException ex) throws IOException, ServletException {
        /* DO WHATEVER CUSTOM THING WE WANT TO DO ON LOGIN FAILURE, EVENTUALLY WE WANT TO TRACK THIS, SOMETHING SIMILAR TO THE LOGIC BELOW! */
        /*
        try {
            User user = userRepositoryImpl.findByUsername(hsr.getParameter("j_username"));
            if (user != null) {
                user.setFailedLoginAttempts(user.getFailedLoginAttempts()+1);
                if (user.getFailedLoginAttempts() >= ... some constant ...) {
                    user.setAccountLockoutExpires(... some appropriate time from now ...);
                }
            }
        }
        catch(Exception e) {
            ... log it ...
        }
        */
        super.onAuthenticationFailure(hsr, hsr1, ex);
    }
    
}