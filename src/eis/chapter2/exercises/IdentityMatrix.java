package eis.chapter2.exercises;

public class IdentityMatrix {
    public static int[][] identityMatrix(int n) {
        int[][] result = new int[n][n];
        for (int i=0; i<n; i++) {
            for (int j=0; j<n; j++) {
                if (i==j) {
                    result[i][j] = 1;
                }
            }
        }
        return result;
    }
}
