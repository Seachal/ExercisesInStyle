package eis.chapter6.testable;

import java.util.*;

/* A testable water container.
 *
 * by Marco Faella
 */
public class Container {

    private Set<Container> group;
    private double amount;

    /* Creates an empty container using the specified set implementation
       to represent groups of connected containers. */
    public Container(Class<? extends Set<Container>> setType)
        throws ReflectiveOperationException {
        group = setType.getDeclaredConstructor()
                       .newInstance();
        group.add(this);
    }

    /* Returns the amount of water held in this container */
    public double getAmount() { return amount; }

    /* Connects this container with other. */
    public boolean connectTo(Container other) {

        // If they are already connected, do nothing
        if (group==other.group) return false;

        int size1 = group.size(),
            size2 = other.group.size();
        double tot1 = amount * size1,
               tot2 = other.amount * size2,
               newAmount = (tot1 + tot2) / (size1 + size2);
        
        // Merge the two groups
        group.addAll(other.group);
        // Update group of containers connected with other
        for (Container c: other.group) c.group = group;
        // Update amount of all newly connected containers
        for (Container c: group) c.amount = newAmount;
        return true;
    }
    
    /* Adds water to this container and returns the updated amount. */
    public double addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (Container c: group) c.amount += amountPerContainer;
        return this.amount;
    }

    /* Checks whether this container is connected with another. */
    public boolean isConnectedTo(Container other) {
        return group == other.group;
    }
}
