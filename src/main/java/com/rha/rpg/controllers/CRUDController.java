package com.rha.rpg.controllers;

import com.rha.rpg.authentication.NoActiveUserException;
import com.rha.rpg.controllers.environment.WebContext;
import com.rha.rpg.model.Entitlement;
import com.rha.rpg.model.Persistable;
import com.rha.rpg.model.Game;
import com.rha.rpg.model.PermissionException;
import com.rha.rpg.model.User;
import com.rha.rpg.persistence.ClassNotManagedException;
import com.rha.rpg.persistence.EntityNotFoundException;
import com.rha.rpg.persistence.GameRepository;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Aaron
 */
@Controller
@RequestMapping("/CRUD")
/**
 * Manages CRUD Actions for the RHA Directory application.
 */
public class CRUDController {
    
    @Autowired
    private transient WebContext webContext;
    @Autowired
    private transient GameRepository gameRepositoryImpl;
    @Autowired
    private Validator validator;
    private final ObjectMapper MAPPER = new ObjectMapper();
    
    
    @RequestMapping(value = "/Read/Game/{id}", method=RequestMethod.GET)
    public @ResponseBody Map readGame(HttpServletResponse response, HttpServletRequest request,
            @PathVariable("id") Long id) throws NoActiveUserException, EntityNotFoundException, IOException, PermissionException {
        User user = webContext.getActiveUser();
        if (!user.getRole().hasEntitlement(Entitlement.READ)) {
            throw new PermissionException(Entitlement.READ);
        }
        return gameRepositoryImpl.findById(id).toMap();
    }
    
    @RequestMapping(value = "/Create/Game", method=RequestMethod.POST)
    @Transactional
    public void createGame(HttpServletResponse response, HttpServletRequest request) throws NoActiveUserException, 
            ClassNotManagedException, IOException, PermissionException {
        User user = webContext.getActiveUser();
        if (!user.getRole().hasEntitlement(Entitlement.CREATE)) {
            throw new PermissionException(Entitlement.CREATE);
        }
        if (request.getHeader("Accept").contains("application/json")) {
            response.setContentType("application/json; charset=UTF-8");
        } 
        else {
            // IE workaround
            response.setContentType("text/html; charset=UTF-8");
        }
        Game game = new Game();
        ServletRequestDataBinder binder = new EntityBinder(request,user,game,"entity"); 
        binder.bind(request);
        Set<ConstraintViolation<Object>> failures = new EntityValidator(validator,game).validate();
        if (!failures.isEmpty()) {
            Map<String,Object> rtn = validationMessages(failures);
            setHeader(request,response);
            response.getWriter().write(MAPPER.writeValueAsString(rtn));
            return;
        }
        game.persist();
        // RESPONSE
        Map<String,Object> rtn = success(game);
        setHeader(request,response);
        response.getWriter().write(MAPPER.writeValueAsString(rtn));
    }
    
    @RequestMapping(value = "/Update/Game/{id}", method=RequestMethod.POST)
    @Transactional
    public void updateGame(HttpServletResponse response, HttpServletRequest request,
            @PathVariable("id") Long id) throws NoActiveUserException, EntityNotFoundException, ClassNotManagedException, IOException, PermissionException {
        User user = webContext.getActiveUser();
        if (!user.getRole().hasEntitlement(Entitlement.UPDATE)) {
            throw new PermissionException(Entitlement.UPDATE);
        }
        if (request.getHeader("Accept").contains("application/json")) {
            response.setContentType("application/json; charset=UTF-8");
        } 
        else {
            // IE workaround
            response.setContentType("text/html; charset=UTF-8");
        }
        Game term = gameRepositoryImpl.findById(id);
        term.detach();
        ServletRequestDataBinder binder = new EntityBinder(request,user,term,"entity"); 
        binder.bind(request);
        Set<ConstraintViolation<Object>> failures = new EntityValidator(validator,term).validate();
        if (!failures.isEmpty()) {
            Map<String,Object> rtn = validationMessages(failures);
            setHeader(request,response);
            response.getWriter().write(MAPPER.writeValueAsString(rtn));
            return;
        }
        term = term.update();
        // RESPONSE
        Map<String,Object> rtn = success(term);
        setHeader(request,response);
        response.getWriter().write(MAPPER.writeValueAsString(rtn));
    }
    
    @RequestMapping(value = "/Delete/Game/{id}", method=RequestMethod.POST)
    @Transactional
    public void deleteGame(HttpServletResponse response, HttpServletRequest request,
            @PathVariable("id") Long id) throws NoActiveUserException, EntityNotFoundException, ClassNotManagedException, IOException, PermissionException {
        User user = webContext.getActiveUser();
        if (!user.getRole().hasEntitlement(Entitlement.DELETE)) {
            throw new PermissionException(Entitlement.DELETE);
        }
        if (request.getHeader("Accept").contains("application/json")) {
            response.setContentType("application/json; charset=UTF-8");
        } 
        else {
            // IE workaround
            response.setContentType("text/html; charset=UTF-8");
        }
        Game term = gameRepositoryImpl.findById(id);
        term.delete();
        // RESPONSE
        Map<String,Object> rtn = new HashMap<>();
        rtn.put("validationError",false);
        setHeader(request,response);
        response.getWriter().write(MAPPER.writeValueAsString(rtn));
    }
    
    private Map<String,Object> validationMessages(Set<ConstraintViolation<Object>> failures) {
        Map<String,Object> failureMessages = new HashMap<>();
        failureMessages.put("validationError",true);
        for (ConstraintViolation failure : failures) {
            failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
        }
        return failureMessages;
    }
    
    private void setHeader(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader("Accept").contains("application/json")) {
            response.setContentType("application/json; charset=UTF-8");
        } 
        else {
            // IE workaround
            response.setContentType("text/html; charset=UTF-8");
        }
    }
    
    private Map<String,Object> success(final Persistable entity) {
        Map<String,Object> map = entity.toMap();
        map.put("validationError",false);
        return map;
    }
    
}
