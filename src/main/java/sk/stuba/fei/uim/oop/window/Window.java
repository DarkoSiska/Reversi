package sk.stuba.fei.uim.oop.window;

import javax.swing.*;
import java.awt.*;

public class Window {
    public Window() {
        JFrame okno = new JFrame();
        okno.setSize(800, 600);
        okno.setVisible(true);
        //okno.setResizable(false);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel dolnyPanel = new NorthPanel();

        okno.add(dolnyPanel, BorderLayout.NORTH);
        //okno.pack();
    }
}
