package eis.chapter9.generic.plot;

import java.awt.*;
import javax.swing.*;

/**
 * A parametric function plotter.
 * 
 * @author Marco Faella
 * @version 1.0
 */
public class Main {
    
    public static final Font standardFont = new Font("Dialog", Font.PLAIN, 18);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Parametric Function Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        ObservablePFunction f = new ObservablePFunction(new Parabola());

        Container inner = new Container();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.add(new TablePanel(f));
        inner.add(new ParamPanel(f));

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(new PlotPanel(f), "Center");
        contentPane.add(inner, "South");

        frame.pack();
        frame.setVisible(true);
    }
}
