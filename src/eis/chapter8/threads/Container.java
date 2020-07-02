package eis.chapter8.threads;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/** A water container, thread-safe.
 *  Uses per-group implicit monitors.
 *  Partially wait-free version.
 *
 *  @author Marco Faella
 *  @version 1.1
 */
public class Container {

    private Group group = new Group(this);

    private static class Group {
        static final AtomicInteger nGroups = new AtomicInteger();
        double amount;
        Set<Container> elems = new HashSet<>();
        // group ids ensure consistent ordering and avoid deadlocks
        final int id = nGroups.incrementAndGet();

        Group(Container c) {
            elems.add(c);
        }
    }

    public void connectTo(Container other) {

        while (true) {
            Object firstMonitor  = group.id<other.group.id ? group : other.group,
                   secondMonitor = group.id<other.group.id ? other.group : group;

            // Check if they are already connected
            if (firstMonitor == secondMonitor) return;

            synchronized (firstMonitor) {
                synchronized (secondMonitor) {
                    if ((firstMonitor  == group && secondMonitor == other.group) ||
                        (secondMonitor == group && firstMonitor  == other.group)) {
                        // Groups are stable: perform the connection and exit
                        int size1 =       group.elems.size(),
                            size2 = other.group.elems.size();
                        double tot1 =       group.amount * size1,
                               tot2 = other.group.amount * size2,
                               newAmount = (tot1 + tot2) / (size1 + size2);
                        group.elems.addAll(other.group.elems);
                        group.amount = newAmount;
                        for (Container x: other.group.elems) x.group = group;
                        return;
                     }
                }
            }
            // At least one of the two group references is stale, retry
        }
    }

    public void addWater(double amount) {
        while (true) {
            Object monitor = group;
            synchronized (monitor) {
                if (monitor == group) {
                    double amountPerContainer = amount / group.elems.size();
                    group.amount += amountPerContainer;
                    return;
                }
            }
        }
    }

    public double getAmount() { 
        synchronized (group) { 
            return group.amount;
        } 
    }    
}

