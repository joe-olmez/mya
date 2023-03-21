package com.olmez.mya.utility;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SharedUtility {

    /**
     * This allow to remove duplicate items
     * 
     * @param <T>  class type of the items in the list
     * @param list
     * @return a list removed duplicate items
     */
    public static <T> List<T> removeDuplicates(List<T> list) {
        return new ArrayList<>(new LinkedHashSet<>(list));
    }

    public static boolean fileExist(String filePath) {
        return new File(filePath).exists();
    }

}
