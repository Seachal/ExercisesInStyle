package eis.exercises;

public interface BoundedSet<T> {
    void add(T elem);
    boolean contains(T elem);
}
