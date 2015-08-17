package com.rha.rpg.controllers;

import com.rha.rpg.authentication.NoActiveUserException;
import com.rha.rpg.persistence.ClassNotManagedException;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Primary controller, returns index page.
 * @author Aaron
 */
@Controller
public class DirectoryController {
    
    // RETURN THE INDEX AS A MODEL OBJECT
    @RequestMapping(value={"/home","/dashboard","/index"})
    public ModelAndView fetchIndex(HttpServletRequest req, HttpServletResponse res) 
            throws NoActiveUserException, ClassNotManagedException, InstantiationException, IllegalAccessException, 
                JsonGenerationException, JsonMappingException, IOException {
        return new ModelAndView("/index"); 
    }
    
}
