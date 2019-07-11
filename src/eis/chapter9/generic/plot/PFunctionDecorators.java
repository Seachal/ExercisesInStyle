package eis.chapter9.generic.plot;

/**
   @author Marco Faella
   @version 1.0
 */
public abstract class PFunctionDecorators { 

    /** Returns a decorated PFunction, whose eval method is modified as follows:
          eval(x) expects x to be a real number in [0,1] and scales it
                  in the range [xMin,xMax], i.e., it returns
		  f.eval(xMin + x*(xMax - xMin)).
    */
    public static PFunction scaleRange(final PFunction f,
				       final double xMin, final double xMax) {
	return new PFunction() {

		public int getNParams()           { return f.getNParams();    }
		public double getParam(int i)     { return f.getParam(i);     }
		public String getParamName(int i) { return f.getParamName(i); }
		public void setParam(int i, double val) { f.setParam(i, val); }

		/* x is supposed to be a value in [0,1] */
		public double eval(double x)  { 
		    return f.eval(xMin + x*(xMax - xMin));      
		}
	    };	
    }

    /** Returns a decorated PFunction, whose setParam method is modified as follows:
          setParam(i,p) expects p to be a real number in [0,1] and scales it
                  in the range [pMin,pMax], i.e., it sets
		          param[i] to pMin + p*(pMax - pMin).
    */
    public static PFunction scaleParams(final PFunction f,
					final double pMin, final double pMax) {
	return new PFunction() {

		public int getNParams()           { return f.getNParams();    }
		public double getParam(int i)     { return f.getParam(i);     }
		public String getParamName(int i) { return f.getParamName(i); }
		public double eval(double x)      { return f.eval(x);         }
		
		/* val is supposed to be a value in [0,1] */
		public void setParam(int i, double val) {
		    f.setParam(i, pMin + val*(pMax - pMin));
		}
	    };	
    }
}
