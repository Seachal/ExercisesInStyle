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

    private static enum Status { FRESH, ENQUEUED, PROCESSED };

    /**
     * The BFS.
     *
     * @param a The adjacency matrix
     * @param vertices The number of vertices
     * @param source The source vertex
     */
    public static void breadthFirst(byte [][] a, int vertexCount, int source) {
        
        Status[] status = new Status[vertexCount];
        Arrays.fill(status, Status.FRESH);

        Stack<Integer> stack = new Stack<>(); 
        stack.push(source);

        while (!stack.isEmpty()) {
            int currentVertex = stack.pop();
            System.out.println(currentVertex);
            status[currentVertex] = Status.PROCESSED; 

            for (int i=0; i<vertexCount; i++) {
                if (a[currentVertex][i] != 0 && status[i] == Status.FRESH) {
                    stack.push(i);
                    status[i] = Status.ENQUEUED;
                }
            }
        }
    }

}
