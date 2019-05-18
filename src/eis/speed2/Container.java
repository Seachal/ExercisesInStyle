package eis.speed2;

/** A water container, optimized for speed of addWater and connectTo.
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private double amount;
    private Container next = this;
    
    public void connectTo(Container other) {
        Container oldNext = next;
        next = other.next;
        other.next = oldNext;
    }
    
    public void addWater(double amount) {
        this.amount += amount;
    }

    public double getAmount() {
        updateGroup();
        return amount; 
    }
    private void updateGroup() {
        Container current = this;
        double totalAmount = 0;
        int groupSize = 0;
        // First pass: collect amounts and count
        do {
            totalAmount += current.amount;
            groupSize++;
            current = current.next;
        } while (current != this);
        double newAmount = totalAmount / groupSize;
        current = this;
        // Second pass: update amounts
        do {
            current.amount = newAmount;
            current = current.next;
        } while (current != this);
    }
}

