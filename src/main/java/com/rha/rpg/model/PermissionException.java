package com.rha.rpg.model;

/**
 *
 * @author Aaron
 */
public class PermissionException extends Exception {
 
    /**
     * Empty constructor.
     */
    public PermissionException() {
    
    }

    /**
     * Constructor defining a message.
     * @param message Error message
     */
    public PermissionException(String message) {
        super(message);
    }
    
    /**
     * Constructor defining message and exception chain.
     * @param message Error message
     * @param chain Exception chain
     */
    public PermissionException(String message, Exception chain) {
        super (message,chain);
    }
    
    /**
     * Constructor defining exception chain.
     * @param chain
     */
    public PermissionException(Exception chain) {
        super (chain);
    }
    
    public PermissionException(Entitlement entitlement) {
        super("You lack permission to: " + entitlement.toString());
    }
    
}