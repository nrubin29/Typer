package me.nrubin29.typer;

import javax.swing.*;
import java.awt.*;

class ScorePanel extends JPanel {

    public ScorePanel(Typer typer, int time, int acc, char grade) {
        add(new LabeledPanel("Time", String.valueOf(time)));
        add(Box.createHorizontalStrut(20));
        add(new LabeledPanel("Accuracy", acc + "%"));
        add(Box.createHorizontalStrut(20));
        add(new LabeledPanel("Grade", String.valueOf(grade)));

        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
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