package eis.readable;

import java.util.Arrays;
import java.util.Stack;

public class Exercise5 {

/**
 * The BFS implemented in code to use.
 *
 * @param a Structure to perform the search on a graph, adjacency matrix etc.
 * @param vertices The vertices to use
 * @param source The Source
 */
public static void bfsImplement(byte [][] a,int vertices,int source){  //passing adjacency matrix and no of vertices
   byte []b=new byte[vertices];    //flag container containing status of each vertices
   Arrays.fill(b,(byte)-1);   //status initialization
   /*       code   status
            -1  =  ready
             0  =  waiting
             1  =  processed       */

   Stack<Integer> st = new Stack<>();     //operational stack
   st.push(source);                                     //assigning source
   while(!st.isEmpty()){
      b[st.peek()]=(byte)0;                      //assigning waiting status
      System.out.println(st.peek());
      int pop=st.peek();
      b[pop]=(byte)1;               //assigning processed status
      st.pop();                  //removing head of the queue
      for(int i=0;i<vertices;i++){
         if(a[pop][i]!=0 && b[i]!=(byte)0 && b[i]!=(byte)1 ){
            st.push(i);
            b[i]=(byte)0;                        //assigning waiting status
         }}}
}

    // This belongs to breadthFirst. Enums cannot be local.
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
        System.out.println("Old version:");
        bfsImplement(graph, graph.length, 0);
        System.out.println("New version:");
        breadthFirst(graph, graph.length, 0);
    }
}
