package org.leviatanplatform.labyrinth.util;

import java.util.Collection;
import java.util.HashMap;

public class MapObjectToInt<T> extends HashMap<T, Integer> {

    public void add(T t){

        Integer i = getInt(t);
        put(t, i+1);
    }

    public Integer getInt(T t) {

        Integer i = super.get(t);

        if (i == null) {
            i = 0;
            put(t, i);
        }

        return i;
    }

    public T getMinArg(Collection<T> listT) {

        Integer minVal = Integer.MAX_VALUE;
        T minKey = null;

        for (T t : listT){
            int i = getInt(t);

            if (i<minVal){
                minVal = i;
                minKey = t;
            }
        }

        return minKey;
    }
}
