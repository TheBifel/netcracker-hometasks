package com.bifel.labs.analyzer;

import com.bifel.labs.fillers.ArrayFiller;
import com.bifel.labs.fillers.Filler;
import com.bifel.labs.output.result.ResultRecord;
import com.bifel.labs.output.result.ResultTable;
import com.bifel.labs.sorters.help.SorterClass;
import com.bifel.labs.sorters.help.SorterType;
import com.bifel.labs.fillers.FillerClass;
import com.bifel.labs.sorters.ArraySorter;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.lang.System.nanoTime;

/**
 * Class for analysing {@link ArrayFiller} and child of {@link ArraySorter}
 *
 * @author Bohdan Ishchenko
 */
public class Analyzer {

    public static final int[] DEFAULT_ELEMENT_COUNT_FOR_TESTS = {10, 100, 500, 1000, 2000, 5000, 10000};

    /**
     * Field storing {@link Set} of methods marked as {@link Filler}
     */
    private Set<Method> fillers = new HashSet<>();

    /**
     * Field storing {@link Set} of {@link SorterClassHolder}
     */
    private Set<SorterClassHolder> sorters = new HashSet<>();

    /**
     * Constructor searching for classes marked as {@link FillerClass} or {@link SorterClass} and storing them into
     * {@link #fillers} and {@link #sorters}
     *
     * @param pathToClasses path where searching classes marked as {@link FillerClass} or {@link SorterClass}
     */
    public Analyzer(String pathToClasses) {
        var sortReflection = new Reflections(pathToClasses).getTypesAnnotatedWith(SorterClass.class);
        for (Class<?> c : sortReflection) {
            if (c.getAnnotation(SorterClass.class).type() == SorterType.WITH_PARAM) {
                for (Class<?> cParam : sortReflection) {
                    if (cParam.getAnnotation(SorterClass.class).type() == SorterType.DEFAULT) {
                        sorters.add(new SorterClassHolder(c, cParam));
                    }
                }
                continue;
            }
            sorters.add(new SorterClassHolder(c));
        }

        for (Class<?> c : new Reflections(pathToClasses).getTypesAnnotatedWith(FillerClass.class)) {
            for (Method method : c.getMethods()) {
                if (method.isAnnotationPresent(Filler.class)) {
                    fillers.add(method);
                }
            }
        }
    }

    /**
     * Method use {@param sorter} and {@param filler} to test them using {@param arr}
     */
    private void test(SorterClassHolder sorter, Method filler, int[] arr) {
        try {
            filler.invoke(null, arr, 0, arr.length);
            sorter.invoke(arr);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }


    /**
     * Method makes magic and creating {@return List<ResultTable>}
     */
    public List<ResultTable> analyze(int[] elementCounts, boolean withColdStart, int numberOfTests) {
        var result = new ArrayList<ResultTable>();

        for (var filler : fillers) {
            var sheet = new ResultTable();
            for (int elementCount : elementCounts) {
                var arr = new int[elementCount];
                for (var sorter : sorters) {
                    if (!withColdStart) {
                        prepareSorter(sorter, filler);
                    }

                    var testStart = nanoTime();
                    for (int repeat = 0; repeat < numberOfTests; repeat++) {
                        test(sorter, filler, arr);
                    }
                    sheet.add(new ResultRecord(
                            (int) ((nanoTime() - testStart) / numberOfTests),
                            elementCount,
                            sorter.getName(),
                            filler.getAnnotation(Filler.class).name())
                    );
                }
            }
            result.add(sheet);
        }

        return result;
    }

    public List<ResultTable> analyze(int[] elementCounts) {
        return analyze(elementCounts, false, 10);
    }


    /**
     * Warming the JVM
     */
    private void prepareSorter(SorterClassHolder sorter, Method filler) {
        var arr = new int[100];
        for (int i = 0; i < 5; i++) {
            test(sorter, filler, arr);
        }
    }
}

