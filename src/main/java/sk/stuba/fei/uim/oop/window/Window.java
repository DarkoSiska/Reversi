package sk.stuba.fei.uim.oop.window;

import javax.swing.*;
import java.awt.*;

public class Window {
    private JFrame window;

    public void setWindow(JFrame window) {
        this.window = window;
    }

    public Window() {
        setWindow(new JFrame());
        window.setSize(800, 800);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Game game = new Game(window);
        window.add(game, BorderLayout.NORTH);
        window.setVisible(true);
    }
}
