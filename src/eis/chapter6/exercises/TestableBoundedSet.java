package eis.chapter6.exercises;

public interface TestableBoundedSet<T> extends Iterable<T> {
    T add(T elem);
    boolean contains(T elem);
}
