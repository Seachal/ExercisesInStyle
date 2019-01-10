package eis.invariants;

import java.util.*;


/** A water container that checks its pre-conditions and invariants
 *  (the latter only if assertions are enabled with -ea).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    // Invariant: This field is non-null and contains this
    // Invariant: Groups of different containers are either the same or disjoint
    private Set<Container> group;
    
    // Invariant: This field is non-negative
    private double amount; 

    public Container() { 
        group = new HashSet<Container>();
        group.add(this);
    }

    public double getAmount() { 
        return amount; 
    }

    public void connectTo(Container other) {
        // Pre-condition
        Objects.requireNonNull(other, "Cannot connect to a null container.");

        if (group==other.group) return;

        int size1 = group.size(),
            size2 = other.group.size();
        double tot1 = amount * size1,
               tot2 = other.amount * size2,
               newAmount = (tot1 + tot2) / (size1 + size2);
        
        group.addAll(other.group);
        for (Container x: other.group) x.group = group;
        for (Container x: group) x.amount = newAmount;

        assert invariantsArePreservedByConnectTo(other) : "connect broke an invariant!";
    }

    private boolean invariantsArePreservedByConnectTo(Container other) {
        return group == other.group &&
            isGroupNonNegative() &&
            isGroupBalanced() && 
            isGroupConsistent();
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();

        // Pre-condition
        if (this.amount + amountPerContainer < 0)
            throw new IllegalArgumentException(
                      "Not enough water to match the addWater request.");

        for (Container c: group) 
            c.amount += amountPerContainer;

        assert invariantsArePreservedByAddWater() : "addWater broke an invariant!";
    }

    private boolean invariantsArePreservedByAddWater() {
        return isGroupNonNegative() && isGroupBalanced();    
    }

    private boolean isGroupNonNegative() { // Invariant I1
        for (Container x: group)
            if (x.amount < 0) return false;
        return true;
    }

    private boolean isGroupConsistent() { // Invariants I2, I3
        for (Container x: group)
            if (x.group != group) return false;
        return true;
    }

    private boolean isGroupBalanced() { // Invariant I4
        for (Container x: group)
            if (x.amount != amount) return false;
        return true;
    }
}
