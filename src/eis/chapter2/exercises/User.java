package eis.chapter2.exercises;

import java.util.*;

public class User {
    private String name;
    private Set<User> directFriends = new HashSet<>();

    public User(String name) {
        this.name = name;
    }

    public void befriend(User other) {
        directFriends.add(other);
        other.directFriends.add(this);
    }

    public boolean isDirectFriendOf(User other) {
        return directFriends.contains(other);
    }

    public boolean isIndirectFriendOf(User other) {
        Set<User> visited = new HashSet<>();
        Deque<User> frontier = new LinkedList<>();

        frontier.addLast(this);
        while (!frontier.isEmpty()) {
            User user = frontier.removeFirst();
            if (user.equals(other)) {
                return true;
            }
            if (visited.add(user)) { // if not visited
                frontier.addAll(user.directFriends);
            }
        }
        return false;
    }

    public static void main(String...args) {
        User a = new User("Abel"), 
             b = new User("Bob"),
             c = new User("Carl"),
             d = new User("Danielle"),
             e = new User("Emily");

        a.befriend(b);
        a.befriend(c);
        d.befriend(c);
        e.befriend(a);

        System.out.println(b.isDirectFriendOf(c));
        System.out.println(b.isIndirectFriendOf(c));
        System.out.println(b.isIndirectFriendOf(d));
        System.out.println(b.isIndirectFriendOf(e));
    }
}
