package eis.chapter9.generic.plot;

/** A parametric function

   @author Marco Faella
   @version 1.0
 */
public interface ParametricFunction {

    // Returns the number of parameters
    int getNParams();
    
    // Sets the value of the i-th parameter to val
    void setParam(int i, double val);
    
    // Returns the current value of the i-th parameter
    double getParam(int i);
    
    // Returns the name of the i-th parameter 
    String getParamName(int i);

    // Returns the value of this function for the value x,
    // according to the current value of the parameters
    double eval(double x);
}
