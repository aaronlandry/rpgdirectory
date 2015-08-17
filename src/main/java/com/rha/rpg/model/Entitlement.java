package com.rha.rpg.model;

/**
 * User entitlements, now very simple CRUD operations for Game operations.
 * @author Aaron
 */
public enum Entitlement {
    READ, UPDATE, CREATE, DELETE;
    
    @Override
    public String toString() {
        return name() + " Games";
    }
    
}
