package com.jpcchaves.adotar.application.utils.colletions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CollectionsUtils {
    public CollectionsUtils() {
    }

    public static <T> List<T> convertSetToList(Set<T> objectsSet) {
        return new ArrayList<>(objectsSet);
    }

    public static <T> Set<T> convertListToSet(List<T> objectsList) {
        return new HashSet<>(objectsList);
    }
}
