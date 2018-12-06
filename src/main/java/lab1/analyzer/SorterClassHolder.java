package lab1.analyzer;

import lab1.sorters.ArraySorter;
import lab1.sorters.help.Sorter;
import lab1.sorters.help.SorterClass;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SorterClassHolder {
    private Class<?> c;
    private SorterClassHolder cParam;
    private Method method;

    SorterClassHolder(Class<?> c){
        this.c = c;
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    SorterClassHolder(Class<?> c, Class<?> cParam){
        this.c = c;
        this.cParam = new SorterClassHolder(cParam);
        for (Method method : c.getMethods()) {
            if (method.isAnnotationPresent(Sorter.class)) {
                this.method = method;
            }
        }
    }

    public void invoke(int[] arr){
        try {
            method.invoke(newInstance(c), (Object) arr);
        } catch (NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    private ArraySorter newInstance(Class<?> c) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        if (cParam != null) {
            Constructor<?> constructor = c.getConstructor(ArraySorter.class);
            return  (ArraySorter) constructor.newInstance(cParam.newInstance(cParam.c));
        }
        Constructor<?> constructor = c.getDeclaredConstructor();
        return  (ArraySorter) constructor.newInstance();
    }

    public String getName(){
        var name = c.getAnnotation(SorterClass.class).name();
        return cParam == null ? name : name + "(" + cParam.getName() + ")";
    }
}
