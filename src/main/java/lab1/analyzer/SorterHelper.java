package lab1.analyzer;

import lab1.sorters.ArraySorter;
import lab1.sorters.Sorter;
import lab1.sorters.SorterClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SorterHelper {
    private Class<?> c;
    private Method method;

    SorterHelper(Class<?> c){
        this.c = c;
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    public void invoke(int[] arr){
        try {
            Constructor<?> constructor = c.getDeclaredConstructor();
            ArraySorter sorter = (ArraySorter) constructor.newInstance();
            method.invoke(sorter, arr);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public String getName(){
        return c.getAnnotation(SorterClass.class).name();
    }
}
