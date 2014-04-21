package me.nrubin29.typer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class ScorePanel extends JPanel {

    private KeyListener keyListener;

    public ScorePanel(final Typer typer, int time, int acc, char grade) {
        add(new LabeledPanel("Time", String.valueOf(time)));
        add(Box.createHorizontalStrut(20));
        add(new LabeledPanel("Accuracy", acc + "%"));
        add(Box.createHorizontalStrut(20));
        add(new LabeledPanel("Grade", String.valueOf(grade)));
        add(Box.createHorizontalStrut(20));
        add(new LabeledPanel("Continue", "Enter"));

        typer.addKeyListener(keyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    typer.startRound();
                    typer.removeKeyListener(keyListener);
                }
            }
        });
    }
}

class LabeledPanel extends JPanel {

    public LabeledPanel(String title, String value) {
        JLabel t = new JLabel(title);
        t.setFont(Utils.SMALL_FONT);
        t.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel v = new JLabel(value);
        v.setFont(Utils.LARGE_FONT);
        v.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(t);
        add(v);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}