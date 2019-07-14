package eis.chapter9.generic.plot;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;


/**
   The panel showing the function parameters and their sliders.

   @author Marco Faella
   @version 1.0
 */
@SuppressWarnings("serial")
public class ParamPanel extends JPanel {

    private final ParametricFunction f_scaled;
    private final ObservableFunction f_obs;
    private static final int minParam = -10, maxParam = 10;
    
    public ParamPanel(ObservableFunction f) {
        f_obs = f;
        f_scaled = PFunctionDecorators.scaleParams(f, minParam, maxParam);

        Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        TitledBorder tb = 
                BorderFactory.createTitledBorder(b, 
                        "Parameters",
                        TitledBorder.LEFT, TitledBorder.TOP);
        tb.setTitleColor(Color.black);
        tb.setTitleFont(Main.standardFont);
        setBorder(tb);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        int n = f_scaled.getNParams();
        for (int i=0; i<n ;i++) {
            add(mkSliderBox(i));
        }
    }

    /*
     * The slider just tells the model that the slider moved.
     */
    private JSlider mkSlider(final int i) {
        final JSlider slider = new JSlider();
        final int sliderMax = slider.getMaximum();
        final int sliderMin = slider.getMinimum();

        /* The following anonymous class is the "controller"
         * in the MVC pattern. 
         */
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (sliderMax == sliderMin) return;

                f_scaled.setParam(i, (slider.getValue() - sliderMin)
                        / (double) (sliderMax - sliderMin));
            }
        });
        slider.setValue((sliderMax + sliderMin) / 2);

        return slider;
    }

    /*
     * The box that holds the slider plus a textual label and a changing label
     * that shows the value of the slider.
     */
    private Box mkSliderBox(int i) {

        Box b = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel(f_scaled.getParamName(i));
        nameLabel.setFont(Main.standardFont);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        nameLabel.setForeground(java.awt.Color.black);
        b.add(nameLabel);

        JLabel valueLabel = new ParamLabel(i);
        valueLabel.setFont(Main.standardFont);
        valueLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        valueLabel.setForeground(java.awt.Color.black);
        b.add(valueLabel);

        JSlider slider = mkSlider(i);
        b.add(slider);

        return b;
    }

    class ParamLabel extends JLabel implements ActionListener {
        /**
         * Construct a label that will show the value of a parameter.
         * 
         * @param i
         *            the index of the parameter to observe
         */
        public ParamLabel(int i) {
            this.i = i;
            f_obs.addActionListener(this);
        }

        /**
         * Respond to a change in the observed parameter model
         * 
         * @param Observable
         *            the model we are observing
         * @param arg
         *            ignored
         */
        public void actionPerformed(ActionEvent _event) {
            setText(String.format("%5.2f", f_scaled.getParam(i)));
            repaint();
        }
        private int i;
    }
}
