package com.revature.aspects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom Annotation for ManagerOnly
 * @author Cyrus De Jesus
 */
@Target(ElementType.METHOD) // Can only be applied to methods
@Retention(RetentionPolicy.RUNTIME) // Annotation available at runtime
public @interface ManagerOnly {}
