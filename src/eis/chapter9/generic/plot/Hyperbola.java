package eis.chapter9.generic.plot;

/**
   @author Marco Faella
   @version 1.0
 */
public class Hyperbola extends AbstractFunction {
    public Hyperbola() {
        super(1);
    }
    
    public double eval(double x) {
        return a[0] / x;
    }
}
