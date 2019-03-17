package eis.exercises;

import java.util.Iterable;

public interface TestableBoundedSet<T> extends Iterable<T> {
    T add(T elem);
    boolean contains(T elem);
}
