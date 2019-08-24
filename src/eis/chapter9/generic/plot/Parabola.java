package eis.chapter9.generic.plot;

/**
   @author Marco Faella
   @version 1.0
 */
public class Parabola implements ParametricFunction {
    private final static int N = 3;
    private final static String[] name = {"a", "b", "c"};
    private double[] a = new double[N];

    public int getNParams() { return N; }

    public String getParamName(int i) {
        if (i >= N || i<0)
            throw new RuntimeException("Invalid parameter index.");
        return name[i];
    }

    public double getParam(int i) {
        if (i >= N || i<0)
            throw new RuntimeException("Invalid parameter index.");
        return a[i];
    }

    public void setParam(int i, double val) {
        if (i >= N || i<0)
            throw new RuntimeException("Invalid parameter index.");
        a[i] = val;
    }

    public double eval(double x) {
        return a[0]*x*x + a[1]*x + a[2];
    }
}
