package com.rha.rpg.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.groups.Default;

/**
 * Executes validation groups in order.  Unlike a sequence, executes
 * subsequent groups even if a prior group failed.  However, if multiple
 * groups fail for the same property, only the first match is returned.  Currently,
 * the system only execute against as single validation group (Default), but it is configured for 
 * future expansion.
 * @author Aaron
 */
public class EntityValidator {
    
    // THE IDEA HERE IS THAT WE WOULD SEPARATE CONSTRAINTS INTO PRIMARY AND SECONDARY CATEGORIES
    // EXPENSIVE CHECKS, SUCH AS THOSE THAT REQUIRE DB ACCESS, WOULD BE GROUPED AS SECONDARY
    // AND ONLY EXECUTED IN THE CASE OF PRIMARY SUCCESS ... NYI
    private static final List<Class> primaryConstraints = new ArrayList<>();
    private Validator validator;
    private Object entity;
    private Map<String,Boolean> seenFailures = new HashMap<>();
    
    static {
        primaryConstraints.addAll(Arrays.asList(Default.class));
    }
    
    public EntityValidator(Validator validator, Object entity) {
        this.validator = validator;
        this.entity = entity;
    }
    
    public Set<ConstraintViolation<Object>> validateConstraints(Class... constraints) {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        for (Class constraint : constraints) {
            addViolations(violations,constraint);
        }
        return violations;
    }
    
    public Set<ConstraintViolation<Object>> validate() {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        for (Class constraint : primaryConstraints) {
            addViolations(violations,constraint);
        }
        return violations;
    }
    
    private void addViolations(Set<ConstraintViolation<Object>> violations, Class constraint) {
        Set<ConstraintViolation<Object>> inner = validator.validate(entity,constraint);
        for (ConstraintViolation<Object> failure : inner) {
            String property = failure.getPropertyPath().toString();
            if (seenFailures.containsKey(property)) {
                continue;
            }
            seenFailures.put(property,true);
            violations.add(failure);
        }
    }
    
}
