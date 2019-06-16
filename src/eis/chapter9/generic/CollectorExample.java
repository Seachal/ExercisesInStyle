package eis.chapter9.generic;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CollectorExample {
    public static final Collector<String,StringBuilder,String> concatenator = 
        new Collector<>() {
        public Supplier<StringBuilder> supplier() { return StringBuilder::new; } 
        public BiConsumer<StringBuilder,String> accumulator() { return StringBuilder::append; }
        public BinaryOperator<StringBuilder> combiner() { return StringBuilder::append; }
        public Function<StringBuilder,String> finisher() { return StringBuilder::toString;}
        public Set<Collector.Characteristics> characteristics() { return Set.of();}
    };

    public static final Collector<String,StringBuilder,String> concatenator2 = 
        Collector.of(StringBuilder::new,
                     StringBuilder::append,
                     StringBuilder::append,
                     StringBuilder::toString);

    public static final Collector<CharSequence,?,String> concatenator3 =
        Collectors.joining();

    public static void main(String...args) {
        System.out.println(concatenator.characteristics());
        System.out.println(concatenator2.characteristics());
        System.out.println(concatenator3.characteristics());
    }
}
