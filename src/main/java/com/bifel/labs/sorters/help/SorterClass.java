package com.bifel.labs.sorters.help;

import com.bifel.labs.sorters.ArraySorter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for marking {@link ArraySorter}
 *
 * @author Bohdan Ishchenko
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SorterClass {

    String name();

    SorterType type() default SorterType.DEFAULT;
}
