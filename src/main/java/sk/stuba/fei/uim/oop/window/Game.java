package sk.stuba.fei.uim.oop.window;

import javax.swing.*;
import java.awt.event.*;


public class Game extends JPanel implements ActionListener {
    private int dimensions;
    private Board board;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public int getDimensions() {
        return this.dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public Game(JFrame window) {
        setDimensions(8);
        setBoard(new Board(getDimensions()));
        window.add(board);
        JButton button1 = new JButton("RESTART GAME");
        JButton button2 = new JButton("EXIT");
        JLabel boardDimensions = new JLabel("Board dimensions:");
        JComboBox<Object> comboBox = new JComboBox<>();
        int[] arrayDimensions = new int[] {6, 8, 10, 12};
        for (int value: arrayDimensions) {
            comboBox.addItem(value);
        }
        comboBox.setSelectedIndex(1);

        button1.addActionListener(e -> restartButton(window, getDimensions()));
        button2.addActionListener(this);
        comboBox.addActionListener(e -> {
            if ((int)comboBox.getSelectedItem() != board.getBoardDimensions()) {
                restartButton(window, (int)comboBox.getSelectedItem());
            }
        });
        this.add(boardDimensions);
        this.add(comboBox);
        this.add(button1);
        this.add(button2);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }

    public void restartButton(JFrame window, int dimensions) {
        window.remove(getBoard());
        setDimensions(dimensions);
        setBoard(new Board(getDimensions()));
        window.add(getBoard());
        window.validate();
    }
}