package eis.generic;

import java.util.function.*;

/** 
 * A mutable attribute that can be initialized, updated, merged, and unwrapped.
 * The property offers a local view of type V and a global summary of type S.
 *
 * Example:
 * <ul>
 *   <li>the local view is a number, updated with "update"</li>
 *   <li>the summary is the history of all numbers ever passed to "update"</li> 
 * </ul>
 *
 * Very similar to the standard interface <code>java.util.stream.Collector</code>
 *
 * @author Marco Faella
 * @version 1.0
 */
public interface Attribute<V,S> {
    S seed();                         // a.k.a. a  /supplier/
    void update(S summary, V value);  // a.k.a. an /accumulator/
    S merge(S summary1, S summary2);  // a.k.a. a  /combiner/
    V report(S summary);              // a.k.a. a  /finisher/

    public static <V,S> Attribute<V,S> of(Supplier<S> supplier,
                                          BiConsumer<S,V> updater,
                                          BinaryOperator<S> combiner, 
                                          Function<S,V> finisher) {
        return new Attribute<>() {
            @Override 
            public S seed() { return supplier.get(); }
            @Override
            public void update(S summary, V value) { updater.accept(summary, value); }
            @Override
            public S merge(S summary1, S summary2) { return combiner.apply(summary1, summary2); }
            @Override
            public V report(S summary) { return finisher.apply(summary); }
         };
    }
}

