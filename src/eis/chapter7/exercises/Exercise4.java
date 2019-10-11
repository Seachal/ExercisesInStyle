package eis.chapter7.exercises;

import java.util.Arrays;
import java.util.Stack;

public class Exercise4 {

    /**
     * The BFS implemented in code to use.
     *
     * @param a Structure to perform the search on a graph, adjacency matrix etc.
     * @param vertices The vertices to use
     * @param source The Source
     */
    public static void bfsImplement(byte [][] a,int vertices,int source){  //passing adjacency matrix and number of vertices
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

    
    public static void main(String ... args) {
        byte[][] graph = {{1,0,1,0}, {0,0,1,1}, {1,0,0,1}, {0,1,1,0} };
        System.out.println("Original version:");
        bfsImplement(graph, graph.length, 0);
    }
}
