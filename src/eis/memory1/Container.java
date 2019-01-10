package eis.memory1;

import java.util.*;

/** A water container, moderately optimized for memory footprint (based on ArrayLists).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    private List<Container> group;
    private float amount;

    public float getAmount() {  return amount; }

    public void connectTo(Container other) {

        if (group==null) {
            group = new ArrayList<>();
            group.add(this);
        }
        if (other.group==null) {
            other.group = new ArrayList<>();
            other.group.add(other);
        }
        if (group==other.group) return;

        int size1 = group.size(),
            size2 = other.group.size();
        float tot1 = amount * size1,
              tot2 = other.amount * size2,
              newAmount = (tot1 + tot2) / (size1 + size2);
        
        group.addAll(other.group);
        for (Container x: other.group) x.group = group;
        for (Container x: group) x.amount = newAmount;
    }
    
    public void addWater(double amount) {
        if (group==null) {
            this.amount += amount;
        } else {
            double amountPerContainer = amount / group.size();
            for (Container c: group)
                c.amount += amountPerContainer;
        }
    }
}
