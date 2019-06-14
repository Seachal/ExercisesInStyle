package eis.chapter4.memory4;

/** A use case for the object-less water container API (memory3 and memory4).
 *
 *  @author Marco Faella
 *  @version 1.0
 */
public class UseCase {
    public static void main(String[] agrs) {
        int a = Container.newContainer(),
            b = Container.newContainer(),
            c = Container.newContainer(),
            d = Container.newContainer();

        Container.addWater(a, 12);
        Container.addWater(d, 8);
        Container.connect(a, b);
        System.out.println(Container.getAmount(a) + " " + 
                           Container.getAmount(b) + " " +
                           Container.getAmount(c) + " " + 
                           Container.getAmount(d));
        Container.debugDump();
        Container.connect(b, c);
        System.out.println(Container.getAmount(a) + " " + Container.getAmount(b) + " " +
                           Container.getAmount(c) + " " + Container.getAmount(d));
        Container.debugDump();
        Container.connect(c, d);
        System.out.println(Container.getAmount(a) + " " + Container.getAmount(b) + " " +
                           Container.getAmount(c) + " " + Container.getAmount(d));
        Container.debugDump();
        Container.addWater(b, 4);
        Container.debugDump();
    }
}
