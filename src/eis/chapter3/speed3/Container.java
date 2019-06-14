package eis.chapter3.speed3;

/** A water container, with all methods in amortized almost-constant time.
 *
 *  Based on union-find trees with link-by-size and path compression.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private double amount;
    private Container parent = this;
    private int size = 1;

    private Container findRootAndCompress() {
        if (parent != this) 
            parent = parent.findRootAndCompress();
        return parent;
    }
    
    public double getAmount() {
        Container root = findRootAndCompress();
        return root.amount; 
    }
    public void addWater(double amount) {
        Container root = findRootAndCompress();
        root.amount += amount / root.size;
    }

    public void connectTo(Container other) {
        Container root1 = findRootAndCompress(),
                  root2 = other.findRootAndCompress();
        if (root1==root2) return;
        int size1 = root1.size, size2 = root2.size;
        double newAmount = ((root1.amount * size1) + 
                            (root2.amount * size2)) / (size1 + size2);

        if (size1 <= size2) {
            root1.parent = root2;
            root2.amount = newAmount;
            root2.size  += size1;
        } else {
            root2.parent = root1;
            root1.amount = newAmount;
            root1.size  += size2;
        }
    }
}

