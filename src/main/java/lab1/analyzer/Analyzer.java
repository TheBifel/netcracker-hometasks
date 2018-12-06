package lab1.analyzer;

import lab1.fillers.Filler;
import lab1.fillers.FillerClass;
import lab1.output.ResultHolder;
import lab1.sorters.help.SortType;
import lab1.sorters.help.SorterClass;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.nanoTime;

public class Analyzer {
    private static final String PATH = "lab1";

    private Set<Method> fillers = new HashSet<>();
    private Set<SorterClassHolder> sorters = new HashSet<>();

    public Analyzer() {
        var sortReflection = new Reflections(PATH).getTypesAnnotatedWith(SorterClass.class);
        for (Class<?> c : sortReflection) {
            if (c.getAnnotation(SorterClass.class).type() == SortType.WITH_PARAM) {
                for (Class<?> cParam : sortReflection) {
                    if (cParam.getAnnotation(SorterClass.class).type() == SortType.DEFAULT) {
                        sorters.add(new SorterClassHolder(c, cParam));
                    }
                }
                continue;
            }
            sorters.add(new SorterClassHolder(c));
        }

        for (Class<?> c : new Reflections(PATH).getTypesAnnotatedWith(FillerClass.class)) {
            for (Method method : c.getMethods()) {
                if (method.isAnnotationPresent(Filler.class)) {
                    fillers.add(method);
                }
            }
        }
    }

    private void test(SorterClassHolder sorter, Method filler, int[] arr) {
        try {
            filler.invoke(null, arr, 0, arr.length);
            sorter.invoke(arr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public List<ResultHolder> analyze(int elementCount, boolean withColdStart, int numberOfTests) {
        var result = new ArrayList<ResultHolder>();
        var arr = new int[elementCount];

        for (var sorter : sorters) {
            for (var filler : fillers) {

                if (!withColdStart) {
                    prepareSorter(sorter, filler);
                }

                var testStart = nanoTime();
                for (int repeat = 0; repeat < numberOfTests; repeat++) {
                    test(sorter, filler, arr);
                }
                result.add(new ResultHolder((int) ((nanoTime() - testStart) / numberOfTests),
                        sorter.getName(),
                        filler.getAnnotation(Filler.class).name())
                );
            }
        }
        return result;
    }

    public List<ResultHolder> analyze(int elementCount) {
        return analyze(elementCount, false, 10);
    }

    private void prepareSorter(SorterClassHolder sorter, Method filler) {
        var arr = new int[100];
        for (int i = 0; i < 5; i++) {
            test(sorter, filler, arr);
        }
    }
}

