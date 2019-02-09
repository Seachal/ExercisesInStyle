package eis.contracts;

import java.util.*;

/** A water container that checks its pre- and post-conditions
 *  (the latter only if assertions are enabled with -ea).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private Set<Container> group;
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

        ConnectPostData postData = null;
        assert (postData = saveConnectPostData(other)) != null;

        int size1 = group.size(),
            size2 = other.group.size();
        double tot1 = amount * size1,
               tot2 = other.amount * size2,
               newAmount = (tot1 + tot2) / (size1 + size2);
        
        group.addAll(other.group);
        for (Container x: other.group) x.group = group;
        for (Container x: group) x.amount = newAmount;

        assert postConnect(postData) : "connect failed its post-condition!";
    }

    private static class ConnectPostData {
        Set<Container> group1, group2;
        double amount1, amount2; 
    }

    private ConnectPostData saveConnectPostData(Container other) {
        ConnectPostData data = new ConnectPostData();
        data.group1 = new HashSet<>(group);
        data.group2 = new HashSet<>(other.group);
        data.amount1 = amount;
        data.amount2 = other.amount;
        return data;
    }

    private boolean postConnect(ConnectPostData postData) {
        return areGroupMembersCorrect(postData) 
            && isGroupAmountCorrect(postData) 
            && isGroupBalanced() 
            && isGroupConsistent();
    }

    private boolean areGroupMembersCorrect(ConnectPostData postData) {
        return group.containsAll(postData.group1) 
            && group.containsAll(postData.group2)
            && group.size() == postData.group1.size() + postData.group2.size();
    }

    private boolean isGroupAmountCorrect(ConnectPostData postData) {
        int size1 = postData.group1.size(),
            size2 = postData.group2.size();
        double tot1 = postData.amount1 * size1,
               tot2 = postData.amount2 * size2,
               newAmount = (tot1 + tot2) / (size1 + size2);

        return almostEqual(amount, newAmount);
    }

    public void addWater(double amount) {
        double amountPerContainer = amount / group.size();
        if (this.amount + amountPerContainer < 0)
            throw new IllegalArgumentException(
                      "Not enough water to match the addWater request.");

        double oldTotal = 0; // for the post-condition
        assert (oldTotal = groupAmount()) >= 0;

        for (Container c: group) 
            c.amount += amountPerContainer;

        assert postAddWater(oldTotal, amount) : "addWater failed its post-condition!";
    }

    private boolean postAddWater(double oldTotal,
                                 double addedAmount) {
        return isGroupBalanced() &&
               almostEqual(groupAmount(), oldTotal + addedAmount);
    }

    private static boolean almostEqual(double x, double y) {
        final double EPSILON = 1E-4;
        return Math.abs(x-y) < EPSILON;
    }

    private boolean isGroupBalanced() {
        for (Container x: group)
            if (x.amount != amount) return false;
        return true;
    }

    private boolean isGroupConsistent() {
        for (Container x: group)
            if (x.group != group) return false;
        return true;
    }

    private double groupAmount() {
        double total = 0;
        for (Container c: group)
            total += c.amount;
        return total;
    }
}
