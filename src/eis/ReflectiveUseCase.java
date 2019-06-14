package eis;

import java.lang.reflect.Method;
import java.util.*;

public class ReflectiveUseCase {

    private static List<String> classNames = List.of("eis.chapter1.novice.Container",
                                                     "eis.chapter2.reference.Container",
                                                     "eis.chapter3.speed1.Container",
                                                     "eis.chapter3.speed2.Container",
                                                     "eis.chapter3.speed3.Container",
                                                     "eis.chapter4.exercises.Container",
                                                     "eis.chapter4.memory1.Container",
                                                     "eis.chapter4.memory2.Container",
                                                     "eis.chapter5.contracts.Container",
                                                     "eis.chapter5.invariants.Container",
                                                     "eis.chapter7.readable.Container",
                                                     "eis.chapter8.threads.Container");
    private final static List<Class<?>> versions = new ArrayList<>();

    private static void initVersions() {
        for (String className: classNames)
            try {
                versions.add(Class.forName(className));
            } catch (ClassNotFoundException e) {
                System.out.println("Cannot find class " + className + " (perhaps you did not compile it). Skipping it.");
            }
    }

    public static void main(String...args) throws ReflectiveOperationException {

        initVersions();

        final int NUM_CONTAINERS = 4;
        Object[] container = new Object[NUM_CONTAINERS];

        for (Class<?> c: versions) {
            System.out.println("Now testing " + c.getName());

            Method addWater, connectTo, getAmount;
            try {
                // For addWater, let's accommodate memory2 and accept 
                // both double and float parameters
                try { 
                    addWater  = c.getMethod("addWater", double.class);
                } catch (ReflectiveOperationException e) {
                    addWater  = c.getMethod("addWater", float.class);
                }
                connectTo = c.getMethod("connectTo", c);
                getAmount = c.getMethod("getAmount");

                for (int i=0; i<NUM_CONTAINERS; i++)
                    container[i] = c.getDeclaredConstructor().newInstance();
            } catch (ReflectiveOperationException e) {
                System.out.println("Skipping this class due to a reflective error:" 
                                   + e + " (" + e.getCause() + ")");
                continue;
            }
                
            // Now the standard use case (same as eis.UseCase):
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

            connectTo.invoke(container[1], container[3]);
            System.out.println(getAmount.invoke(container[0]) + " " +
                               getAmount.invoke(container[1]) + " " +
                               getAmount.invoke(container[2]) + " " +
                               getAmount.invoke(container[3]));
        }
    }
}
