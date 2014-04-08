package me.nrubin29.typer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Typer extends JFrame {

    private final JLabel line, time;
    private final JTextField input;
    private final Timer timer;
    private Level currentLevel;
    private int strokes;

    private Typer() {
        super("Typer");

        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 36);

        line = new JLabel();
        line.setFont(f);

        time = new JLabel();
        time.setFont(f);

        input = new JTextField();
        input.setFont(f);
        input.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (!timer.isRunning()) timer.start();

                if (input.getText().equals(line.getText())) {
                    timer.stop();
                    JOptionPane.showMessageDialog(
                            Typer.this,
                            getRoundInformation(),
                            "Done",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                    startRound(currentLevel);
                } else strokes++;
            }
        });

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.add(line, BorderLayout.CENTER);
        topPanel.add(time, BorderLayout.LINE_END);

        add(topPanel);
        add(input);

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

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        setSize(640, 170);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(String.valueOf(Integer.parseInt(time.getText()) + 1));
            }
        });

        startRound(currentLevel = Level.ONE);
    }

    public static void main(String[] args) {
        new Typer();
    }

    private void startRound(Level l) {
        input.setText("");
        time.setText("0");
        line.setText(l.getRandomString());
        strokes = 0;
    }

    private String getRoundInformation() {
        double
                t = Integer.valueOf(time.getText()),
                acc = ((double) currentLevel.getLength() / strokes) * 100;

        if (acc > 100) acc = 100;

        double gradeNum = t / 10 + acc / 10;
        char grade;

        if (gradeNum > 8) grade = 'A';
        else if (gradeNum > 6) grade = 'B';
        else if (gradeNum > 4) grade = 'C';
        else if (gradeNum > 2) grade = 'D';
        else grade = 'F';

        return "Time: " + new Double(t).intValue() + " seconds. Accuracy: " + new Double(acc).intValue() + "%. Grade: " + grade + ".";
    }
}