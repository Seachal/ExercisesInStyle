package eis.chapter9.generic.plot;

import java.awt.event.*;
import java.util.*;

/**
   A parametric function that can be observed.

   @author Marco Faella
   @version 1.0
 */
public class ObservableFunction implements ParametricFunction {
	
    private ParametricFunction f;
    private List<ActionListener> listeners = new ArrayList<>();

    public ObservableFunction(ParametricFunction f) { this.f = f; }

    // The following methods passed through to the function
    public int getNParams()           { return f.getNParams();    }
    public double getParam(int i)     { return f.getParam(i);     }
    public String getParamName(int i) { return f.getParamName(i); }
    public double eval(double x)      { return f.eval(x);         }
    
    public void addActionListener(ActionListener listener) {
        listeners.add(listener);
    }

    /** Calls the corresponding method of the function
        and then notifies the observers
     */
    public void setParam(int i, double val) {
        f.setParam(i, val);
        for (ActionListener listener: listeners)
            listener.actionPerformed(null);
    }
}

