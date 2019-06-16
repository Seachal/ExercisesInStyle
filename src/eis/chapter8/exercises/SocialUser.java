package eis.chapter8.exercises;

import java.util.*;

/**
 * Exercise 3: a user in a social network.
 *
 * Being friends is symmetric but not transitive.
 *
 * WARNING: contains deadlock! See SocialUserNoDeadlock for solution.
 */
public class SocialUser {
    private final String name;
    private final Set<SocialUser> friends = new HashSet<>();

    public SocialUser(String name) {
        this.name = name;
    }

    public synchronized void befriend(SocialUser other) {
        friends.add(other);
        synchronized (other) {
            other.friends.add(this);
        }
    }

    public synchronized boolean isFriend(SocialUser other) {
        return friends.contains(other);
    }

    public String getName() {
        return name;
    }
}
