package eis.exercises;

import java.util.ArrayList;

public class CompactUniqueList<E> {
    private final ArrayList<E> data;

    public CompactUniqueList(int capacity) {
        data = new ArrayList<>(capacity);
        for (int i=0; i<capacity; i++)
            data.add(null);
        assert data.size() == capacity;
    }

    public boolean set(int index, E element) {
        if (index<0 || index>=data.size() || data.contains(element))
            return false;
        data.set(index, element);
        return true;
    }

    public E get(int index) {
        if (index<0 || index>=data.size())
            return null;
        return data.get(index);
    }
}
