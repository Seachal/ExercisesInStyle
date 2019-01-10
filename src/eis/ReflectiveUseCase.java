package eis;

import java.lang.reflect.Method;
import java.util.*;

public class ReflectiveUseCase {
    private final static List<Class<?>> versions = new ArrayList<>();

    static {
	try {
	    versions.add(Class.forName("eis.novice.Container"));
	    versions.add(Class.forName("eis.reference.Container"));
	    versions.add(Class.forName("eis.speed1.Container"));
	    versions.add(Class.forName("eis.speed2.Container"));
	    versions.add(Class.forName("eis.speed3.Container"));
	    versions.add(Class.forName("eis.memory1.Container"));
	    versions.add(Class.forName("eis.memory2.Container"));
	} catch (ClassNotFoundException e) {
	    e.printStackTrace();
	}
    }

    public static void main(String...args) throws Exception {
	final int N = 4;
	Object[] container = new Object[N];

	for (Class<?> c: versions) {
	    System.out.println("Now testing " + c.getName());

	    Method addWater  = c.getMethod("addWater", double.class),
		   connectTo = c.getMethod("connectTo", c),
		   getAmount = c.getMethod("getAmount");

	    for (int i=0; i<N; i++)
		container[i] = c.newInstance();

	    // Now the standard use case:
	    addWater.invoke(container[0], 12);
	    addWater.invoke(container[3], 8);
	    connectTo.invoke(container[0], container[1]);
	    System.out.println(getAmount.invoke(container[0]) + " " +
                               getAmount.invoke(container[1]) + " " +
			       getAmount.invoke(container[2]) + " " + 
			       getAmount.invoke(container[3]));
	    connectTo.invoke(container[1], container[2]);
	    System.out.println(getAmount.invoke(container[0]) + " " +
                               getAmount.invoke(container[1]) + " " +
			       getAmount.invoke(container[2]) + " " +
			       getAmount.invoke(container[3]));
	}
    }
}
