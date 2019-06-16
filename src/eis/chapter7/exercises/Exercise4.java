package eis.chapter7.exercises;

import java.util.Arrays;

public class Exercise4 {

    public static void f(double[][] a) {
        int i = 0, j = 0;
        while (i<a.length) {
            if (a[i].length != a.length)
                throw new IllegalArgumentException();
            i++;
        }
        i = 0;
        while (i<a.length) {
            j = 0;
            while (j<i) {
                double temp = a[i][j];
                a[i][j] = a[j][i];
                a[j][i] = temp;
                j++;
            }
            i++;
        }
    }

   /** Checks whether a given matrix is square-shaped
     *
     * @param matrix  a matrix
     * @return {@code true} if the given matrix is square
     */
    private static boolean isSquare(double[][] matrix) {
        for (double[] row: matrix) {
            if (row.length != matrix.length) {
                return false;
            }
        }
        return true;
    }

   /** Transposes a square matrix
     *
     * @param matrix  a matrix
     * @throws IllegalArgumentException if the given matrix is not square
     */
    public static void transpose(double[][] matrix) {
        if (!isSquare(matrix)) {
            throw new IllegalArgumentException(
                      "Cannot transpose a non-square matrix.");
        }

        for (int i=0; i<matrix.length; i++) {
            for (int j=0; j<i; j++) {
                double temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    public static void main(String ... args) {
        double m[][] = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        f(m);
        System.out.println(Arrays.deepToString(m));
    }
}
