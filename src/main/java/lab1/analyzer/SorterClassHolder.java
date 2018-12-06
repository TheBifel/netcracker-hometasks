package lab1.analyzer;

import lab1.sorters.ArraySorter;
import lab1.sorters.help.Sorter;
import lab1.sorters.help.SorterClass;
import lab1.sorters.help.SorterType;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Class provides easy access to class marked {@link ArraySorter}
 *
 * @author Bohdan Ishchenko
 */
public class SorterClassHolder {

    /**
     * Class which implements {@link ArraySorter} and holder are created for it
     */
    private Class<?> c;

    /**
     * Another holder for class marked as {@link SorterType#DEFAULT}
     * becomes available when {@link SorterClassHolder} holds class
     * marked as {@link SorterType#WITH_PARAM}
     */
    private SorterClassHolder cParam;

    /**
     * Method which use as {@link ArraySorter#sort(int[])}
     */
    private Method method;


    /**
     * @param c is a class which implements {@link ArraySorter}
     *          and marked as {@link SorterType#DEFAULT}
     */
    SorterClassHolder(Class<?> c) {
        this.c = c;
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    /**
     * @param c      is a class which implements {@link ArraySorter}
     *               and marked as {@link SorterType#WITH_PARAM}
     * @param cParam is another holder for class which marked as {@link SorterType#DEFAULT}
     */
    SorterClassHolder(Class<?> c, Class<?> cParam) {
        this.c = c;
        this.cParam = new SorterClassHolder(cParam);
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    /**
     * Method which invokes {@link ArraySorter#sort(int[])}
     */
    public void invoke(int[] arr) {
        try {
            method.invoke(newInstance(c), (Object) arr);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method created for simplify reflection constructor
     *
     * @param c class which instance will be created
     * @return new {@link ArraySorter}
     */
    private ArraySorter newInstance(Class<?> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (cParam != null) {
            Constructor<?> constructor = c.getConstructor(ArraySorter.class);
            return (ArraySorter) constructor.newInstance(cParam.newInstance(cParam.c));
        }
        Constructor<?> constructor = c.getDeclaredConstructor();
        return (ArraySorter) constructor.newInstance();
    }

    /**
     * @return pretty name of {@link ArraySorter} depends from {@link SorterType}
     */
    public String getName() {
        var name = c.getAnnotation(SorterClass.class).name();
        return cParam == null ? name : name + "(" + cParam.getName() + ")";
    }
}
