package eis.threadsafe;

import static java.lang.System.out;

public class SystemMain {
    public static void main(String ... args) {
        ContainerSystem s = new ContainerSystem(10);
        out.println(s);
        int a = 1, b = 4;
        s = s.addWater(a, 7);
        out.println(s);
        s = s.connect(a, b);
        out.println(s);
    }
}
