package com.jpcchaves.adotar.utils.colletions;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CollectionsUtils {
    public CollectionsUtils() {
    }

    public <T> List<T> convertSetToList(Set<T> objectsSet) {
        return new ArrayList<>(objectsSet);
    }

    public <T> Set<T> convertListToSet(List<T> objectsList) {
       return new HashSet<>(objectsList);
    }
}
