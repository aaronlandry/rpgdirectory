package com.rha.rpg.controllers.environment;

import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.web.util.matcher.RegexRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * A class that manages exceptions to CSRF checks.  Currently does not do much, 
 * but will likely be expanded in the future.
 * @author alandry
 */
public class CsrfSecurityRequestMatcher implements RequestMatcher {
    
    private Pattern allowedMethods = Pattern.compile("^(GET|HEAD|TRACE|OPTIONS)$");
    private RegexRequestMatcher unprotectedMatcher = new RegexRequestMatcher("/j_spring_security_check", null);
 
    @Override
    public boolean matches(HttpServletRequest request) {
        if(allowedMethods.matcher(request.getMethod()).matches()){
            return false;
        }
        return !unprotectedMatcher.matches(request);
    }
    
}