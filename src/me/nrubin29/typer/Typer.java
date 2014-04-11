package me.nrubin29.typer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Typer extends JFrame {

    private JPanel currentComponent;

    private Level currentLevel;

    private Typer() {
        JMenu level = new JMenu("Level");
        ButtonGroup levelGroup = new ButtonGroup();

        for (final Level l : Level.values()) {
            JRadioButtonMenuItem levelItem = new JRadioButtonMenuItem(l.getName());

            levelItem.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    startRound(currentLevel = l);
                }
            });

            levelGroup.add(levelItem);
            level.add(levelItem);

            if (l == Level.ONE) levelItem.setSelected(true);
        }

        JMenuBar bar = new JMenuBar();
        bar.add(level);

        setJMenuBar(bar);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        startRound(currentLevel = Level.ONE);
    }

    public static void main(String[] args) {
        new Typer();
    }

    void startRound(Level l) {
        if (currentComponent != null) remove(currentComponent);
        add(currentComponent = new TypingPanel(this, l));
    }

    void endRound(int time, int acc, char grade) {
        if (currentComponent != null) remove(currentComponent);
        add(currentComponent = new ScorePanel(this, time, acc, grade));
        currentComponent.setAlignmentX(Component.CENTER_ALIGNMENT);
        dispose();
        setVisible(true);
    }
}