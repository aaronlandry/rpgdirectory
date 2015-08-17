package com.rha.rpg.controllers.editors;

import com.rha.rpg.model.Category;
import java.beans.PropertyEditorSupport;

/**
 *
 * @author Aaron
 */

public class CategoryEditor extends PropertyEditorSupport {

    public CategoryEditor() {
        super();
    }
    
    @Override
    public void setAsText(String text) {
        if (text == null || text.toLowerCase().equals("null")) {
            setValue(null);
        } 
        else {
            Integer id = Integer.valueOf(text);
            if (id.equals(0)) {
                setValue(null);
                return;
            }
            setValue(Category.findForValue(id));
        }
    }
    
    @Override
    public String getAsText() {
        Category value = (Category)getValue();
        return (value != null ? value.getValue().toString() : "");
    }
    
}
