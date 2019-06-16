package eis.chapter9.generic;

/**
   Water containers, implemented as a specialization of the generic
   UnionFindNode infrastructure.

   @version 1.0
   @author Marco Faella
 */
public class Container extends UnionFindNode<Double,ContainerSummary> {
    public Container() {
        super(ContainerSummary.ops);
    }
}


class ContainerSummary {
    private double amount;
    private int groupSize;

    public ContainerSummary(double amount, int groupSize) {
        this.amount = amount;
        this.groupSize = groupSize;
    }
    public ContainerSummary() { this(0, 1); }
    public void update(double amount) {
        this.amount += amount;
    }
    public ContainerSummary merge(ContainerSummary other) {
        return new ContainerSummary(amount + other.amount,
                                    groupSize + other.groupSize);
    }
    public double getAmount() {
        return amount / groupSize;
    }
    public static final Attribute<Double,ContainerSummary> ops =
        Attribute.of(ContainerSummary::new,
                     ContainerSummary::update,
                     ContainerSummary::merge,
                     ContainerSummary::getAmount);
}


