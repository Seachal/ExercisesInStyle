package eis.chapter9.generic.plot;

import java.awt.*;
import java.awt.event.*;
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
    public TablePanel(ObservablePFunction f) {
        f_obs  = f;
        f_eval = PFunctionDecorators.scaleRange(f, xMin, xMax);

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

        for (int i=0; i<nPoint ; i++) {
            double x = ((double) i) / (nPoint - 1);
            double scaledX = xMin + x*(xMax - xMin);
            JLabel xLabel = new JLabel(scaledX + "");
            xLabel.setFont(Main.standardFont);
            xLabel.setForeground(java.awt.Color.black);
            add(xLabel);
            JLabel yLabel = new ValueLabel(x);
            yLabel.setFont(Main.standardFont);
            yLabel.setForeground(java.awt.Color.black);
            add(yLabel);
        }
        setMaximumSize(getPreferredSize());
    }

    private ObservablePFunction f_obs;
    private PFunction f_eval;
    private int nPoint = 5;
    private final static double xMin = -5, xMax = 5;

    private class ValueLabel extends JLabel implements ActionListener {
        /**
         * Construct a label that will show the value of the function
         * for a given argument x.
         * 
         * @param x
         *            the x value of the point to observe
         */
        public ValueLabel(double x) {
            super( f_eval.eval(x) + "");
            this.x = x;
            f_obs.addActionListener(this);
        }

        /**
         * Respond to a change in the observed model
         * 
         */
        @Override
        public void actionPerformed(ActionEvent _event) {
            setText(String.format("%6.2f", f_eval.eval(x)));
            repaint();
        }
        private double x;
    }
}
