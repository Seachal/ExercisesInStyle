package eis.speed2;

import java.util.*;

/** A water container, optimized for speed of addWater and connect.
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
	Container cur = this;
	double totalAmount = 0;
	int groupSize = 0;
	// First pass: collect amounts and count
	do {
	    totalAmount += cur.amount;
	    groupSize++;
	    cur = cur.next;
	} while (cur != this);
	double newAmount = totalAmount / groupSize;
	cur = this;
	// Second pass: update amounts
	do {
	    cur.amount = newAmount;
	    cur = cur.next;
	} while (cur != this);
    }
}

