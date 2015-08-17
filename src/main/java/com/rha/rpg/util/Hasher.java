package com.rha.rpg.util;

/**
 * Service to simplify hashcode generation (and safely handle nulls).
 * <br /><br />
 * Note:  This doesn't handle Arrays, which it could via reflection in the Object
 * signature.  
 * <br /><br />
 * Also Note:  This service is scoped as prototype.  A separate instance is injected into
 * every requesting bean.  This does not currently accomplish anything (this could all
 * be handled through static methods), but it may in the future.
 * <br /><br?
 * Final Note: Objects of this class are fully chainable.
 * @author Aaron
 */
public class Hasher {

    private int value;

    /**
     * Adds a hashed value of the passed double to the internal hashcode.
     * @param d 
     * @return The HasherService
     */
    public Hasher hash(double d) {
        return hash(Double.doubleToLongBits(d));
    }

    /**
     * Adds a hashed value of the passed object to the internal hashcode.
     * @param objectToHash The object to hash
     * @return The HasherService
     */
    public Hasher hash(Object objectToHash) {
        if (objectToHash != null) {
            value = _hash(value, objectToHash.hashCode());
        }
        return this;
    }

    /**
     * Gets the internally stored hash value (comprised of the cumulative hash values
     * generated by calls to hash().
     * @return Composite hashcode
     */
    public int getValue() {
        return value;
    }

    /**
     * Internal multiplier used for term generation.
     */
    private static final int PRIME = 37;

    private static int _hash(int seed, int c) {
        return PRIME * seed + c;
    }

}

