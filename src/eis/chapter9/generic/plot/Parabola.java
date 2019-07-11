package eis.chapter9.generic.plot;

/**
   @author Marco Faella
   @version 1.0
 */
public class Parabola implements PFunction {
    private final static int n = 3;
    private final static String[] name = {"c", "b", "a"};
    private double[] a = new double[n];

    public int getNParams() { return n; }

    public double getParam(int i) {
	if (i >= n || i<0)
	    throw new RuntimeException("Invalid parameter index.");
	return a[i];
    }

    public void setParam(int i, double val) {
	if (i >= n || i<0)
	    throw new RuntimeException("Invalid parameter index.");
	a[i] = val;
    }

    public String getParamName(int i) {
	return name[i];
    }
    
    public double eval(double x) {
	return a[0] + a[1]*x + a[2]*x*x;
    }
}
