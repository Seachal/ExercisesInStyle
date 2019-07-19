package eis.chapter9.exercises;

import java.util.function.BiPredicate;

import eis.chapter9.generic.*;

import java.util.*;

public class Exercise4 {
    public static <T> Set<Set<T>> partition(Collection<? extends T> c,
                                            BiPredicate<? super T, ? super T> equivalent) {
        class Group implements Attribute<Set<T>,Set<T>> {
            @Override
            public Set<T> seed() {
                return new HashSet<>();
            }
            @Override
            public void update(Set<T> summary, Set<T> value) {
                summary.addAll(value);
            }           
            @Override
            public Set<T> merge(Set<T> s1, Set<T> s2) {
                Set<T> result = new HashSet<>(s1);
                result.addAll(s2);
                return result;
            }
            @Override
            public Set<T> report(Set<T> s) { return s; }
        }
        Attribute<Set<T>,Set<T>> groupProperty = new Group();
       
        Map<T,UnionFindNode<Set<T>,Set<T>>> nodeMap = new HashMap<>();
        for (T item: c) {
            UnionFindNode<Set<T>,Set<T>> node = 
                new UnionFindNode<Set<T>,Set<T>>(groupProperty);
            node.update(Set.of(item));
            nodeMap.put(item, node);
        }
        // Merge groups
        for (T item1: c)
            for (T item2: c)
                if (equivalent.test(item1, item2))
                    nodeMap.get(item1).connectTo(nodeMap.get(item2));
        // Collect results
        Set<Set<T>> result = new HashSet<>();
        for (T item: c)
            result.add(nodeMap.get(item).get());
        return result;
    }
    
    public static void main(String...args) {
        Set<String> names = Set.of("Walter", "Skyler", "Hank", "Mike", "Saul");
        BiPredicate<String,String> sameLength = (a, b) -> a.length() == b.length();
        Set<Set<String>> groups = partition(names, sameLength);
        System.out.println(groups);
    }
}
