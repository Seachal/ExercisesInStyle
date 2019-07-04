package eis.chapter8.exercises;

import java.util.*;

/**
 * Exercise 3 (solution): a user in a social network.
 *
 * Being friends is symmetric but not transitive.
 *
 */
public class SocialUserNoDeadlock {
    private final String name;
    private final Set<SocialUserNoDeadlock> friends = new HashSet<>();
    private final int id;
    private static int instanceCounter;

    public SocialUserNoDeadlock(String name) {
        this.name = name;
        this.id = instanceCounter;
        instanceCounter++;
    }

    public void befriend(SocialUserNoDeadlock other) {
        Object firstMonitor, secondMonitor;
        if (id < other.id) {
            firstMonitor = this;
            secondMonitor = other;
        } else {
            firstMonitor = other;
            secondMonitor = this;
        }
        synchronized (firstMonitor) {
            synchronized (secondMonitor) {
                friends.add(other);
                other.friends.add(this);
            }
        }
    }

    public synchronized boolean isFriend(SocialUserNoDeadlock other) {
        return friends.contains(other);
    }

    public String getName() {
        return name;
    }
}
