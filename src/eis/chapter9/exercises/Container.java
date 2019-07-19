package eis.chapter9.exercises;

import eis.chapter9.generic.*;

/**
   Water containers with arbitrary precision rational water amounts,
   implemented as a specialization of the generic UnionFindNode infrastructure.

   @version 1.0
   @author Marco Faella
 */
public class Container extends UnionFindNode<BigRational,RationalSummary> {
    public Container() {
        super(RationalSummary.ops);
    }
}

class RationalSummary {
    private BigRational amount;
    private int groupSize;

    public RationalSummary(BigRational amount, int groupSize) {
        this.amount = amount;
        this.groupSize = groupSize;
    }
    public RationalSummary() { this(BigRational.ZERO, 1); }
    
    public void update(BigRational increment) {
        amount = amount.plus(increment);
    }
    public RationalSummary merge(RationalSummary other) {
        return new RationalSummary(amount.plus(other.amount),
                                    groupSize + other.groupSize);
    }
    public BigRational getAmount() {
        return amount.divides(new BigRational(groupSize));
    }
    public static final Attribute<BigRational,RationalSummary> ops =
        Attribute.of(RationalSummary::new,
                     RationalSummary::update,
                     RationalSummary::merge,
                     RationalSummary::getAmount);
}


