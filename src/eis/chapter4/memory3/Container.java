package eis.chapter4.memory3;

import java.util.Arrays;


/** A system of water containers, identified by integer IDs (object-less API).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {
    // from containerID to its group
    private static int group[] = new int[0];
    // from groupID to the amount in each container in the group
    private static float amount[] = new float[0];

    public static float getAmount(int containerID) {
        int groupID = group[containerID];
        return amount[groupID];
    }

    // Prevents instantiation
    private Container() {}

    public static int newContainer() {
        int nContainers = group.length,
            nGroups = amount.length;
        amount = Arrays.copyOf(amount, nGroups + 1);
        group = Arrays.copyOf(group, nContainers + 1);
        group[nContainers] = nGroups;
        return nContainers;
    }
    
    public static void connect(int containerID1, int containerID2)
    {
        int groupID1 = group[containerID1],
            groupID2 = group[containerID2],
            size1 = groupSize(groupID1),
            size2 = groupSize(groupID2);
        if (groupID1 == groupID2) return;

        float amount1 = amount[groupID1] * size1,
              amount2 = amount[groupID2] * size2;       
        amount[groupID1] = (amount1 + amount2) / (size1 + size2);

        for (int i=0; i<group.length; i++)
            if (group[i] == groupID2)
                group[i] = groupID1;

        removeGroupAndDefrag(groupID2);
    }

    private static int groupSize(int groupID) {
        int size = 0;
        for (int otherGroupID: group)
            if (otherGroupID == groupID)
                size++;
        return size;
    }

    private static void removeGroupAndDefrag(int groupID) {
        for (int containerID=0; containerID<group.length; containerID++)
            if (group[containerID] == amount.length-1)
                group[containerID] = groupID;

        amount[groupID] = amount[amount.length-1];
        amount = Arrays.copyOf(amount, amount.length-1);
    }
        
    public static void addWater(int containerID, float amount) {
        int groupID = group[containerID],
            groupSize = groupSize(groupID);
        Container.amount[groupID] += amount / groupSize;
    }

    public static void debugDump() {
        System.out.print("group: [\t");
        for (int c: group) {
            System.out.print(c + ", \t");
        }
        System.out.println("]");
        System.out.print("amount: [\t");
        for (float a: amount) {
            System.out.print(a + ", \t");
        }
        System.out.println("]");
    }
}
