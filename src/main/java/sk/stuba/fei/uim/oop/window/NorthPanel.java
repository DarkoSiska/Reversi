package sk.stuba.fei.uim.oop.window;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NorthPanel extends JPanel implements ActionListener {
    public NorthPanel() {
        JButton button1 = new JButton("RESET GAME");
        JButton button2 = new JButton("EXIT");
        JSlider slider = new JSlider(SwingConstants.HORIZONTAL, 6, 12, 6);
        slider.setPreferredSize(new Dimension(200, 100));
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(2);
        slider.setPaintLabels(true);

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                System.out.println(slider.getValue());
            }
        });

        button1.addActionListener(this);
        button2.addActionListener(e -> System.exit(0));
        this.add(slider);
        this.add(button1);
        this.add(button2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}