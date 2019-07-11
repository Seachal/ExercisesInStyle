package eis.chapter9.generic;

/**
 *  A class of objects that:
 *
 *    1) hold a value of type V, and
 *    2) can be connected to each other.
 */
public interface ContainerLike<V,
			       T extends ContainerLike<V,T>> {
    public V get();
    public void update(V val);
    public void connectTo(T other);
}
