package sk.stuba.fei.uim.oop.players;

import javax.swing.*;
import java.awt.*;

public class Player extends JPanel {
    public Player(Graphics graphics, int xAxis, int yAxis, int width, int height, Color color) {
        graphics.setColor(Color.DARK_GRAY);
        graphics.fillOval(xAxis+width/20, yAxis+height/20, width-width/10, height-height/10);
        graphics.setColor(color);
        graphics.fillOval(xAxis+width/16, yAxis+height/16, width-width/8, height-height/8);
    }
}
