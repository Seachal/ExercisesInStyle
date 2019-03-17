package eis.memory5;

import java.util.Arrays;

/** A system of water containers, identified by integer IDs (object-less API).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class Container {

    /* When positive, it's the index of the next container in this group,
       with a +1 bias.
       When zero or negative, it's the opposite of the water amount
       in each container of this group.
    */
    private static float nextOrAmount[] = new float[0];

    /** Returns the ID of a new empty container.
     */
    public static int newContainer() {
        int nContainers = nextOrAmount.length;
        // the new cell is automatically initialized with zero
        nextOrAmount = Arrays.copyOf(nextOrAmount, nContainers + 1);
        return nContainers;
    }

    /** Returns the amount of water in the specified container.
     */
    public static float getAmount(int containerID) {
        int[] lastAndSize  = findLastOfGroupAndCount(containerID);
        return -nextOrAmount[lastAndSize[0]];
    }

    /** Adds the specified amount of water to the specified container.
     */
    public static void addWater(int containerID, float amount) {
        int   firstOfGroup = findFirstOfGroup(containerID);
        int[] lastAndSize  = findLastOfGroupAndCount(firstOfGroup);
        nextOrAmount[lastAndSize[0]] -= amount/lastAndSize[1];
    }

    /** Connects the specified containers, so that water can flow from one to the other.
     */
    public static void connect(int containerID1, int containerID2) {
        int first1 = findFirstOfGroup(containerID1),
            first2 = findFirstOfGroup(containerID2);
        if (first1 == first2) return;

        int[] lastAndSize1 = findLastOfGroupAndCount(first1),
              lastAndSize2 = findLastOfGroupAndCount(first2);
        int last1 = lastAndSize1[0],
            last2 = lastAndSize2[0];
        float amount1 = -nextOrAmount[last1],
              amount2 = -nextOrAmount[last2],
            newAmount = ((amount1 * lastAndSize1[1]) + (amount2 * lastAndSize2[1]))
            / (lastAndSize1[1] + lastAndSize2[1]);

        // concatenate the groups
        nextOrAmount[last1] = encodeSuccessorIndex(first2);
        nextOrAmount[last2] = -newAmount;
    }

    static void debugDump() {
        System.out.print("[\t");
        for (float c: nextOrAmount) {
            System.out.print(c + ", \t");
        }
        System.out.println("]");
    }

    // PRIVATE METHODS //

    // Prevents instantiation
    private Container() {}

    private static float encodeSuccessorIndex(int next) {
        return Float.intBitsToFloat(next +1);
    }

    private static int decodeSuccessorIndex(float next) {
        assert next>0;
        return Float.floatToRawIntBits(next) -1;
    }

    /** Returns the index of the first container in the same group of containerID.
     *  Warning: quadratic time complexity.
     */
    private static int findFirstOfGroup(int containerID) {
        int current = containerID, i = 0;
        do {
            for (i=0; i<nextOrAmount.length; i++)
                if (nextOrAmount[i] == encodeSuccessorIndex(current)) {
                    current = i;
                    break;
                }
        } while (i<nextOrAmount.length);
        return current;
    }

    /** @return an array of two values:
     *  - the index of the last container in the same group of containerID
     *  - the number of steps from containerID to the last one (inclusive)
     */
    private static int[] findLastOfGroupAndCount(int containerID) {
        int[] result = new int[] { containerID, 1 };
        while (nextOrAmount[result[0]]>0) {
            result[0] = decodeSuccessorIndex(nextOrAmount[result[0]]);
            result[1]++;
        }
        return result;
    }
}
