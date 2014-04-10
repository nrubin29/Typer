package me.nrubin29.typer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class Typer extends JFrame {

    private final JLabel line, time;
    private final Timer timer;
    private String input, lineText;
    private Level currentLevel;
    private int strokes, index;

    private Typer() {
        Font f = new Font(Font.SANS_SERIF, Font.PLAIN, 54);

        line = new JLabel();
        line.setFont(f);

        time = new JLabel();
        time.setFont(f);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(0, 5, 0, 5));
        panel.add(line, BorderLayout.LINE_START);
        panel.add(time, BorderLayout.LINE_END);
        add(panel);

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

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(String.valueOf(Integer.parseInt(time.getText()) + 1));
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((input + e.getKeyChar()).equals(lineText)) {
                    timer.stop();

                    double
                            t = Integer.valueOf(time.getText()),
                            acc = Utils.fixNumber(((double) currentLevel.getLength() / strokes) * 100, 0, 100);

                    double gradeNum = t / 10 + acc / 10;
                    char grade;

                    if (gradeNum > 8) grade = 'A';
                    else if (gradeNum > 6) grade = 'B';
                    else if (gradeNum > 4) grade = 'C';
                    else if (gradeNum > 2) grade = 'D';
                    else grade = 'F';

                    JOptionPane.showMessageDialog(
                            Typer.this,
                            "Time: " + new Double(t).intValue() + " seconds. Accuracy: " + new Double(acc).intValue() + "%. Grade: " + grade + ".",
                            "Done",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    startRound(currentLevel);

                    e.consume();
                    return;
                }

                strokes++;

                if (
                        e.getKeyChar() != lineText.charAt(index) ||
                                (!Character.isLetter(e.getKeyChar()) &&
                                        !Character.isDigit(e.getKeyChar()) &&
                                        e.getKeyChar() != ' ')
                        ) {
                    e.consume();
                    Toolkit.getDefaultToolkit().beep();
                    return;
                } else {
                    input += e.getKeyChar();
                    line.setText("<html><font color='gray'>" + lineText.substring(0, ++index) + "<font color='black'>|" + lineText.substring(index) + "<html");
                }

                if (!timer.isRunning()) timer.start();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                e.consume();
            }
        });

        startRound(currentLevel = Level.ONE);
    }

    public static void main(String[] args) {
        new Typer();
    }

    private void startRound(Level l) {
        index = 0;
        strokes = 0;
        line.setText("|" + (lineText = l.getRandomString()));
        input = "";
        time.setText("0");

        setTitle("Level " + l.getName());
        setSize(line.getFontMetrics(line.getFont()).stringWidth(line.getText()) + 80, 150);
        setLocationRelativeTo(null);
    }
}