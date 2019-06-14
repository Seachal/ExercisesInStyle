package eis.chapter4.exercises;

/** A water container, with all methods in amortized almost-constant time.
 *
 *  Based on union-find trees with link-by-size and path compression.
 *
 *  In this space-optimized version, normal containers do not hold "amount" and "size"
 *  information. Only group representatives do, by pointing to a special "parent"
 *  object of type SpecialContainer.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    /* A special container satisfies "parent == this".
       A group representative (aka root) is recognized by:
         - "parent instanceof SpecialContainer", or equivalently
         - "parent.parent == parent"
    */
    private Container parent = this;

    private static class SpecialContainer extends Container { 
        SpecialContainer() {
            // Calls the private constructor to avoid an infinite recursion
            super(true); 
        }
        int size = 1;
        double amount;
    }

    /* This private constructor is used when creating a special container.
     * In contrast to the public constructor, it doesn't create *another* special container.
     * The dummy parameter is needed to distinguish its signature from the public constructor.
     */
    private Container(boolean dummy) {}

    public Container() {
        /* Initially, every container is a root, so it points to a special container. */
        parent = new SpecialContainer();
    }

    private boolean isRoot() {
        return parent.parent == parent;
    }

    private Container findRootAndCompress() {
        if (this.isRoot())
            return this;
        if (parent.isRoot())
            return parent;
        parent = parent.findRootAndCompress();
        return parent;
    }
    
    public double getAmount() {
        Container root = findRootAndCompress();
        SpecialContainer special = (SpecialContainer) root.parent;
        return special.amount; 
    }
    public void addWater(double amount) {
        Container root = findRootAndCompress();
        SpecialContainer special = (SpecialContainer) root.parent;
        special.amount += amount / special.size;
    }

    public void connectTo(Container other) {
        Container root1 = findRootAndCompress(),
                  root2 = other.findRootAndCompress();
        if (root1==root2) return;
        SpecialContainer special1 = (SpecialContainer) root1.parent,
                         special2 = (SpecialContainer) root2.parent;

        int size1 = special1.size, size2 = special2.size;
        double newAmount = ((special1.amount * size1) + 
                            (special2.amount * size2)) / (size1 + size2);

        if (size1 <= size2) {
            root1.parent = root2;
            special2.amount = newAmount;
            special2.size  += size1;
        } else {
            root2.parent = root1;
            special1.amount = newAmount;
            special1.size  += size2;
        }
    }
}

