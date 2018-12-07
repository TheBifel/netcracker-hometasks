package com.bifel.labs.output.result;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class ResultTable {

    private List<ResultRecord> results = new ArrayList<>(4);
    private String name;

    public ResultRecord get(int index) {
        return results.get(index);
    }

    public void add(ResultRecord result) {
        name = result.getFillerName();
        this.results.add(result);
    }

    public int size() {
        return results.size();
    }

    public Iterator iterator() {
        return results.iterator();
    }

    public String getName() {
        return name;
    }

    public void sort(Comparator<ResultRecord> comparator) {
        results.sort(comparator);
    }
}
