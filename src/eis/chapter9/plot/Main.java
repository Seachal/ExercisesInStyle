package eis.chapter9.plot;

import java.awt.*;
import javax.swing.*;

/**
 * A parabola plotter.
 * 
 * @author Marco Faella
 * @version 1.0
 */
public class Main {
    
    public static final Font standardFont = new Font("Dialog", Font.PLAIN, 18);

    public static void main(String[] args) {
        JFrame frame = new JFrame("Parabola Plotter");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  

        double[] parameters = new double[3];
        TablePanel tablePanel = new TablePanel(parameters);
        PlotPanel  plotPanel  = new PlotPanel(parameters);
        ParamPanel paramPanel = new ParamPanel(parameters, tablePanel, plotPanel);
        
        Container inner = new Container();
        inner.setLayout(new BoxLayout(inner, BoxLayout.Y_AXIS));
        inner.add(tablePanel);
        inner.add(paramPanel);

        Container contentPane = frame.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.add(plotPanel, "Center");
        contentPane.add(inner, "South");

        frame.pack();
        frame.setVisible(true);
    }
}
