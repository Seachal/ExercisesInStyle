package eis.chapter9.plot;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A panel to contain the plot.
 *
 * @author Marco Faella
 * @version 1.0
 */
@SuppressWarnings("serial")
public class TablePanel extends JPanel {

    /**
     * Create a panel that can display the provided function.
     * 
     * @param f
     *            the function to plot
     */
    public TablePanel(double[] params) {

        this.params = params;
        
        Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        TitledBorder tb = 
                BorderFactory.createTitledBorder(b, 
                        "Table of values",
                        TitledBorder.LEFT, TitledBorder.TOP);
        tb.setTitleColor(Color.black);
        tb.setTitleFont(Main.standardFont);
        setBorder(tb);
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(nPoint + 1, 2));

        JLabel xName = new JLabel("x"),
                yName = new JLabel("y");
        xName.setFont(Main.standardFont);
        yName.setFont(Main.standardFont);
        add(xName);
        add(yName);

        valueLabels = new JLabel[nPoint];
        for (int i=0; i<nPoint ; i++) {
            double x = ((double) i) / (nPoint - 1);
            double scaledX = xMin + x*(xMax - xMin);
            JLabel xLabel = new JLabel(scaledX + "");
            xLabel.setFont(Main.standardFont);
            xLabel.setForeground(java.awt.Color.black);
            add(xLabel);
            valueLabels[i] = new JLabel("");
            valueLabels[i].setFont(Main.standardFont);
            add(valueLabels[i]);
        }
        setMaximumSize(getPreferredSize());
        updateLabels();
        active = true;
    }

    private JLabel[] valueLabels;
    private double[] params;
    private boolean active;
    
    private final static int nPoint = 5;
    private final static double xMin = -5, xMax = 5;

    @Override
    public void repaint() {
        if (active)
            updateLabels();
        super.repaint();
    }

    private void updateLabels() {
        for (int i=0; i<nPoint ; i++) {
            double x = ((double) i) / (nPoint - 1);
            double scaledX = xMin + x*(xMax - xMin);
            valueLabels[i].setText(String.format("%6.2f", params[0] * scaledX * scaledX + params[1] * scaledX + params[2]));
        }
    }
 }
