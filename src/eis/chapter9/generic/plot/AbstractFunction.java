package eis.chapter9.generic.plot;

/**
   @author Marco Faella
   @version 1.0
 */
public abstract class AbstractFunction implements ParametricFunction {
    private final int n;
    protected final double[] a;
    
    public AbstractFunction(int n) {
        this.n = n;
        this.a = new double[n];
    }
    
    public int getNParams() { return n; }

    public String getParamName(int i) {
        final int firstLetter = 97; // a
        if (i >= n || i<0)
            throw new RuntimeException("Invalid parameter index.");
        return Character.toString(firstLetter + i);
    }

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
}
