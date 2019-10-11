package eis.chapter7.exercises;

import java.util.Arrays;
import java.util.Stack;

public class Exercise4Solved {
  
    // This belongs to the method breadthFirst, but enums cannot be local.
    private static enum Status { FRESH, ENQUEUED, PROCESSED };

   /** Visits the node in a directed graph in breadth first order,
     * printing the index of each visited node.
     *
     * @param adjacent     the adjacency matrix
     * @param vertexCount  the number of vertices
     * @param sourceVertex the source vertex
     */
    public static void breadthFirst(byte[][] adjacent, int vertexCount, int sourceVertex) {      
        Status[] status = new Status[vertexCount];
        Arrays.fill(status, Status.FRESH);

        Stack<Integer> stack = new Stack<>(); 
        stack.push(sourceVertex);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            System.out.println(currentVertex);
            status[currentVertex] = Status.PROCESSED; 

            for (int i=0; i<vertexCount; i++) {
                if (adjacent[currentVertex][i] != 0 && status[i] == Status.FRESH) {
                    stack.push(i);
                    status[i] = Status.ENQUEUED;
                }
            }
        }
    }

    public static void main(String ... args) {
        byte[][] graph = {{1,0,1,0}, {0,0,1,1}, {1,0,0,1}, {0,1,1,0} };
        System.out.println("New version:");
        breadthFirst(graph, graph.length, 0);
    }
}
