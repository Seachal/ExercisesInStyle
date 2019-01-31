package eis.exercises;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class FastUniqueList<E> {
    private final ArrayList<E> dataByIndex;
    private final Set<E> dataSet;

    public FastUniqueList(int capacity) {
        dataByIndex = new ArrayList<>(capacity);
        for (int i=0; i<capacity; i++)
            dataByIndex.add(null);
        assert dataByIndex.size() == capacity;
        dataSet = new HashSet<>();
    }

    public boolean set(int index, E element) {
        if (index<0 || index>=dataByIndex.size() || dataSet.contains(element))
            return false;
        E old = dataByIndex.set(index, element);
        dataSet.remove(old);
        dataSet.add(element);
        return true;
    }

    public E get(int index) {
        if (index<0 || index>=dataByIndex.size())
            return null;
        return dataByIndex.get(index);
    }
}
