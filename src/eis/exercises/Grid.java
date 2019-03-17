package eis.exercises;

public class Grid {
    private final int maxPower;
    private int residualPower;
    
    public Grid(int maxPower) {
        this.residualPower = this.maxPower = maxPower;
    }
    public int residualPower() {
        return residualPower;
    }
    void addPower(int power) {
        if (residualPower + power < 0)
            throw new IllegalArgumentException("Not enough power.");
        residualPower += power;
    }
}
