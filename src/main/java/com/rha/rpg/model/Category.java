package com.rha.rpg.model;

import com.rha.rpg.persistence.PersistableEnum;

/**
 * A category of Game.
 * @author Aaron
 */
public enum Category implements PersistableEnum {
    VIDEO(1,"Video Game"),
    TABLETOP(2,"Tabletop RPG"),
    BOARD(3,"Board Game");
    
    // FULFILL CONTRACT FOR PersistableEnums
    public static Category findForValue(Integer value) {
        for (Category category : Category.values()) {
            if (category.getValue().equals(value)) {
                return category;
            }
        }
        return null;
    }
    
    private Integer value;
    private String label;
    
    Category(Integer value, String label) {
        this.value = value;
        this.label = label;
    }
    
    @Override
    public String toString() {
        return getLabel();
    }

    @Override
    public String getLabel() {
        return label;
    }
    
    @Override
    public Integer getValue() {
        return value;
    }
    
    @Override
    public String getHtmlLabel() {
        return getLabel();
    }
    
    // For consistency with Persistables
    
    @Override
    public String getSortableString() {
        return getLabel();
    }
    
    @Override
    public Integer getId() {
        return getValue();
    }
    
}
