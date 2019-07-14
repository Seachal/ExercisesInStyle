package eis.chapter9.plot;

import java.awt.*;
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

    private double[] params;
    private final JPanel[] views;
    private static final int minParam = -10, maxParam = 10;
    private boolean active;
    
    public ParamPanel(double[] params, JPanel ... views) {
        this.params = params;
        this.views = views;

        Border b = BorderFactory.createBevelBorder(BevelBorder.RAISED);
        TitledBorder tb = 
                BorderFactory.createTitledBorder(b, 
                        "Parameters",
                        TitledBorder.LEFT, TitledBorder.TOP);
        tb.setTitleColor(Color.black);
        tb.setTitleFont(Main.standardFont);
        setBorder(tb);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        for (int i=0; i<3 ;i++) {
            add(mkSliderBox(i));
        }
        updateLabels();
        active = true;
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
            public void stateChanged(ChangeEvent _e) {
                if (sliderMax == sliderMin) return;
                double relativeValue = (slider.getValue() - sliderMin) / (double) (sliderMax - sliderMin);
                double absoluteValue = minParam + relativeValue * (maxParam - minParam);
                params[i] = absoluteValue;
                ParamPanel.this.repaint();
                for (JPanel view: views)
                    view.repaint();
            }
        });
        slider.setValue((sliderMax + sliderMin) / 2);

        return slider;
    }

    private static final String[] paramName = { "a", "b", "c" };
    private final JLabel[] valueLabels = new JLabel[3];
    
    /*
     * The box that holds the slider plus a textual label and a changing label
     * that shows the value of the slider.
     */
    private Box mkSliderBox(int i) {

        Box b = Box.createHorizontalBox();
        JLabel nameLabel = new JLabel(paramName[i]);
        nameLabel.setFont(Main.standardFont);
        nameLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        nameLabel.setForeground(java.awt.Color.black);
        b.add(nameLabel);

        valueLabels[i] = new JLabel("");
        valueLabels[i].setFont(Main.standardFont);
        valueLabels[i].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        valueLabels[i].setForeground(java.awt.Color.black);
        b.add(valueLabels[i]);

        JSlider slider = mkSlider(i);
        b.add(slider);

        return b;
    }

    @Override
    public void repaint() {
        if (active)
            updateLabels();
        super.repaint();
    }

    private void updateLabels() {
        for (int i=0; i<3; i++) {
            if (valueLabels != null && valueLabels[i] != null)
                valueLabels[i].setText(String.format("%5.2f", params[i]));
        }
    }
}
