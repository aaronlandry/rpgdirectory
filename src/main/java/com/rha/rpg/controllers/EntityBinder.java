package com.rha.rpg.controllers;

import com.rha.rpg.controllers.editors.CategoryEditor;
import com.rha.rpg.controllers.editors.StringEscapeEditor;
import com.rha.rpg.model.Category;
import com.rha.rpg.model.User;
import com.rha.rpg.persistence.ClassNotManagedException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.bind.ServletRequestDataBinder;

/**
 * An implementation of ServletRequestDataBinder that adds support for transforming 
 * custom RHA data types to/from from representations to model representations.  This
 * should be more automated, but there are small number of classes currently, so 
 * we manually map them.
 * @author Aaron
 */
// EntityIntrospector bean will be referenced in final constructor
// I will forget to add a preConstruction directive then, so I am doing it now!
@Configurable(preConstruction=true,dependencyCheck=true)    
public class EntityBinder extends ServletRequestDataBinder {
    
    public EntityBinder(HttpServletRequest request, User user, Object entity, String string) throws ClassNotManagedException {
        super(entity,string);
        registerCustomEditor(String.class, new StringEscapeEditor());
        registerCustomEditor(Category.class, new CategoryEditor());
    }
    
}
