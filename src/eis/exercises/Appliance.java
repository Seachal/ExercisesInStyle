package eis.exercises;

public class Appliance {
    private final int powerAbsorbed;
    private Grid grid;
    private boolean isOn;

    public Appliance(int powerAbsorbed) {
        this.powerAbsorbed = powerAbsorbed;
    }

    public void plugInto(Grid otherGrid) {
        if (grid!=null && isOn)
            grid.addPower(powerAbsorbed);
        grid = otherGrid;
        if (isOn)
            otherGrid.addPower(-powerAbsorbed);
    }

    public void on() {
        if (grid==null)
            throw new IllegalStateException("Cannot turn on when unconnected to any grid.");
        if (!isOn) {
            grid.addPower(-powerAbsorbed);
            isOn = true;
        }
    }

    public void off() {
        if (grid!=null && isOn) {
            grid.addPower(powerAbsorbed);
            isOn = false;
        }
    }

    public static void main(String...args) {
        Appliance tv = new Appliance(150), radio = new Appliance(30);
        Grid grid = new Grid(3000);

        tv.plugInto(grid);
        radio.plugInto(grid);
        System.out.println(grid.residualPower());
        tv.on();
        System.out.println(grid.residualPower());
        radio.on();
        System.out.println(grid.residualPower());
    }
}
