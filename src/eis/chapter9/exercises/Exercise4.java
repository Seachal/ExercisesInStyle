package eis.chapter9.exercises;

import java.util.function.BiPredicate;

import eis.chapter9.generic.*;

import java.util.*;

/**
 *  Exercise 4 from chapter 9.
 *   
 *  @version 1.0
 *  @author Marco Faella
 */
public class Exercise4 {
   
    /** 
     * Partitions a collection according to an equivalence predicate.
     * Given a collection and an equivalence, it collects the elements
     * of the collection into disjoint sets of mutually equivalent elements.
     * It doesn't modify the original collection.
     * 
     * @param <T> The type of the collection being partitioned
     * @param collection The collection being partitioned
     * @param equivalent The predicate establishing equivalence
     * @return The set of equivalence classes of the given collection with respect to the given equivalence
     */
    public static <T> Set<Set<T>> partition(Collection<? extends T> collection,
                                            BiPredicate<? super T, ? super T> equivalent) {
        
        Attribute<Set<T>,Set<T>> groupProperty = Attribute.of(
                HashSet::new,
                Set::addAll,
                (set1, set2) -> {
                    Set<T> union = new HashSet<>(set1);
                    union.addAll(set2);
                    return union;
                },
                set -> set);
       
        // Assign a new node to each element
        Map<T,UnionFindNode<Set<T>,Set<T>>> nodeMap = new HashMap<>();
        for (T item: collection) {
            UnionFindNode<Set<T>,Set<T>> node = new UnionFindNode<>(groupProperty);
            node.update(Set.of(item));
            nodeMap.put(item, node);
        }
        // Turn equivalence into connections
        for (T item1: collection)
            for (T item2: collection)
                if (equivalent.test(item1, item2))
                    nodeMap.get(item1).connectTo(nodeMap.get(item2));
        // Collect groups of connected nodes
        Set<Set<T>> result = new HashSet<>();
        for (T item: collection) {
            result.add(nodeMap.get(item).get());
        }
        return result;
    }
    
    public static void main(String ... args) {
        Set<String> names = Set.of("Walter", "Skyler", "Hank", "Mike", "Saul");
        BiPredicate<String,String> sameLength = (a, b) -> a.length() == b.length();
        Set<Set<String>> groups = partition(names, sameLength);
        System.out.println(groups);
    }
}
