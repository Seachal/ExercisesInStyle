package eis.readable;

import java.util.*;

/* To create HTML documentation, from the folder src/:
 *   javadoc -d ../docs eis/readable/Container.java
 */

/** 
 *  A <code>Container</code> represents a water container
 *  with virtually unlimited capacity.
 *  <p>
 *  Water can be added or removed.
 *  Two containers can be connected with a permanent pipe.
 *  When two containers are connected, directly or indirectly,
 *  they become communicating vessels and water will distribute
 *  equally among all of them.
 *  <p>
 *  The set of all containers connected to this one is called the
 *  <i>group</i> of this container.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private Set<Container> group;
    private double amount;

    /** Creates an empty container. */
    public Container() { 
        group = new HashSet<Container>();
        group.add(this);
    }

    /** Returns the amount of water currently held in this container.
     *
     *  @return the amount of water currently held in this container */
    public double getAmount() { 
        return amount; 
    }

    /** Connects this container with another.
     *
     *  @param other The container that will be connected to this one
     */
    public void connectTo(Container other) {
        if (this.isConnectedTo(other))
            return;

        double newAmount = (groupAmount() + other.groupAmount()) / 
                           (groupSize() + other.groupSize());
        mergeGroupWith(other.group);
        setAllAmountsTo(newAmount);
    }
    
    private void mergeGroupWith(Set<Container> otherGroup) {
        group.addAll(otherGroup);
        for (Container x: otherGroup) {
            x.group = group;
        }
    }

    private void setAllAmountsTo(double amount) {
        for (Container x: group) {
            x.amount = amount;
        }
    }

    /** Checks whether this container is connected to anoher one.
     *
     *  @param other the container whose connection with this will be checked
     *  @return <code>true</code> if this container is connected 
     *                            to <code>other</code> 
     */
    public boolean isConnectedTo(Container other) {
        return group == other.group;
    }

    /** Returns the number of containers in the group of this container.
     *
     *  @return the size of the group 
     */
    public int groupSize() {
        return group.size();
    }

    /** Returns the total amount of water in the group of this container.
     *
     *  @return the amount of water in the group 
     */
    public double groupAmount() {
        return amount * group.size();
    }

    /** Adds water to this container.
     *  A negative <code>amount</code> indicates removal of water.
     *  In that case, there should be enough water in the group 
     *  to satisfy the request.
     *
     *  @param amount the amount of water to be added 
     */
    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        for (Container c: group) {
            c.amount += amountPerContainer;
        }
    }
}
