package lab1.sorters.help;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used for marking {@link lab1.sorters.ArraySorter}
 *
 * @author Bohdan Ishchenko
 */
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SorterClass {

    String name();

    SorterType type() default SorterType.DEFAULT;
}
