package com.rha.rpg.controllers;
 
import com.rha.rpg.controllers.environment.WebContext;
import com.rha.rpg.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller for authentication-related pages.
 * @author Aaron
 */
@Controller
@RequestMapping
public class AuthenticationController {
    
    @Autowired
    private WebContext webContext;
    
    @RequestMapping("/login")
    public String loginPrompt(Model model, RedirectAttributes redirectAttributes, HttpServletRequest request, 
            @RequestParam(value="message",required = false) String message) {
        User user = null;
        try {
            user = webContext.getActiveUser();
        }
        catch(Exception e) { }
        if (user != null) {
            redirectAttributes.addAllAttributes(request.getParameterMap());
            return "redirect:/index.do";
        }
        model.addAttribute("message", message);
        return "/login";
    }
    
    @RequestMapping("/denied")
    public String denied() {
        return "/denied";
    }

    @RequestMapping("/login/failure")
    public String loginFailure(Model model, HttpServletResponse response) {
        model.addAttribute("message","Sign in unsuccessful.  Please try again.");
        return "/login";
    }
    
    @RequestMapping("/logout/success")
    public String logoutSuccess(Model model, HttpServletResponse response) {
        model.addAttribute("message", "You have signed out.");
        return "/login";
    }
    
}