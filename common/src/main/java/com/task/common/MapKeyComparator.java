package com.task.common;

import java.util.Comparator;

public class MapKeyComparator implements Comparator<String> {

    @Override
    public int compare(String stra, String strb) {

        return stra.compareTo(strb);
    }
}