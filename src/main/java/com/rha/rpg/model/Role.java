package com.rha.rpg.model;

import com.rha.rpg.persistence.PersistableEnum;
import java.util.Arrays;
import java.util.List;

/**
 * A role for a User, currently only a collection of Entitlements.
 * @author Aaron
 */
public enum Role implements PersistableEnum {
    STANDARD(1,"Standard", Entitlement.CREATE, Entitlement.UPDATE, Entitlement.READ),
    ADMIN(2,"Admin", Entitlement.values());
    
    // FULFILL CONTRACT FOR PersistableEnums
    public static Role findForValue(Integer value) {
        for (Role role : Role.values()) {
            if (role.getValue().equals(value)) {
                return role;
            }
        }
        return null;
    }
    
    private Integer value;
    private String label;
    private List<Entitlement> entitlements = null;
    
    Role(Integer value, String label, Entitlement... entitlements) {
        this.value = value;
        this.label = label;
        this.entitlements = Arrays.asList(entitlements);
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
    
    public Boolean hasEntitlement(Entitlement entitlement) {
        return entitlements != null && entitlements.contains(entitlement);
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
