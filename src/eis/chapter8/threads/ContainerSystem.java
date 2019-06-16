package eis.chapter8.threads;

import java.util.Arrays;


/** An immutable system of water containers, identified by integer IDs (object-less API).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class ContainerSystem {
    // from containerID to its group
    private final int group[];
    // from groupID to the amount in each container in the group
    private final double amount[];

    public ContainerSystem(int containerCount) {
        group = new int[containerCount];
        amount = new double[containerCount];
        for (int i=0; i<containerCount; i++)
            group[i] = i;
    }

    public int containerCount() {
        return group.length;
    }

    public ContainerSystem addContainer() {
        final int containerCount = group.length;
        ContainerSystem result = new ContainerSystem(this, containerCount + 1);
        result.group[containerCount] = containerCount;
        return result;
    }

    // Container methods

    public double getAmount(int containerID) {
        final int groupID = group[containerID];
        return amount[groupID];
    }

    public ContainerSystem connect(int containerID1, int containerID2)
    {
        int groupID1 = group[containerID1],
            groupID2 = group[containerID2];
        if (groupID1 == groupID2)
            return this;

        ContainerSystem result = new ContainerSystem(this, group.length);
        int size1 = groupSize(groupID1),
            size2 = groupSize(groupID2);
        double amount1 = amount[groupID1] * size1,
               amount2 = amount[groupID2] * size2;       
        result.amount[groupID1] = (amount1 + amount2) / (size1 + size2);

        for (int i=0; i<group.length; i++)
            if (group[i] == groupID2)
                result.group[i] = groupID1;

        return result;
    }

    private int groupSize(int groupID) {
        int size = 0;
        for (int otherGroupID: group)
            if (otherGroupID == groupID)
                size++;
        return size;
    }

    private ContainerSystem(ContainerSystem old, int length) {
        group = Arrays.copyOf(old.group, length);
        amount = Arrays.copyOf(old.amount, length);
    }

    public ContainerSystem addWater(int containerID, double amount) {
        if (amount == 0) 
            return this;
        ContainerSystem result = new ContainerSystem(this, group.length);
        int groupID = group[containerID],
            groupSize = groupSize(groupID);
        result.amount[groupID] += amount / groupSize;
        return result;
    }

    @Override
    public String toString() {
        return "group: \t" + Arrays.toString(group) +
               "\namount: \t" + Arrays.toString(amount);
    }
}
