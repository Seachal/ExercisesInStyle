package eis.appendixb.ultimate;

import java.util.Objects;

/** 
 *  A <code>Container</code> represents a water container
 *  with virtually unlimited capacity.
 *  <p>
 *  Water can be added or removed.
 *  Two containers can be connected with a permanent pipe.
 *  When two containers are connected, directly or indirectly,
 *  they become communicating vessels and water will <b>distribute
 *  equally</b> among all of them.
 *  <p>
 *  The set of all containers connected to this one is called the
 *  <i>group</i> of this container.
 *
 *  <p>This implementation is based on union-find trees with link-by-size and path compression.
 *  All methods run in amortized almost-constant time.
 * 
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private double amount;
    private Container parent = this;
    private int size = 1;

    /** Creates an empty container. */
    public Container() { 
        // Included to provide a JavaDoc
    }
    
   /** Returns the amount of water currently held in this container.
    *
    *  @return the amount of water currently held in this container 
    */
    public double getAmount() {
        Container root = findRootAndCompress();
        return root.amount; 
    }
    
    /** Adds water to this container.
     *  A negative <code>amount</code> indicates removal of water.
     *  In that case, there should be enough water in the group 
     *  to satisfy the request.
     *
     *  @param amount the amount of water to be added 
     *  @throws IllegalArgumentException if <code>amount</code> is negative and there's not enough water to satisfy the request
     */
    public void addWater(double amount) {
        Container root = findRootAndCompress();
        
        // Pre-condition check
        double amountPerContainer = amount / root.size;
        if (root.amount + amountPerContainer < 0) {
            throw new IllegalArgumentException(
                      "Not enough water to match the addWater request.");
        }
            
        root.amount += amountPerContainer;
    }

   /** Connects this container with another.
    *
    *  @param other the container that will be connected to this one
    */
    public void connectTo(Container other) {
        // Pre-condition check
        Objects.requireNonNull(other, "Cannot connect to a null container.");
        
        Container root1 = findRootAndCompress(),
                  root2 = other.findRootAndCompress();
        if (root1==root2) return;
        
        // Link-by-size policy
        if (root1.size <= root2.size) {
            root1.linkTo(root2);
        } else {
            root2.linkTo(root1);
        }
    }
    
    private void linkTo(Container otherRoot) {
        parent = otherRoot;
        otherRoot.amount = combinedAmount(otherRoot);
        otherRoot.size += size;
    }

    private double combinedAmount(Container otherRoot) {
        return ((amount * size) + (otherRoot.amount * otherRoot.size)) / 
                (size + otherRoot.size);
     }
         
    private Container findRootAndCompress() {
        if (parent != this) {
            parent = parent.findRootAndCompress();
        }
        return parent;
    }
}

