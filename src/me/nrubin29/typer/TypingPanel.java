package me.nrubin29.typer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

class TypingPanel extends JPanel {

    private final JLabel line, time;
    private final Timer timer;
    private final String lineText;
    private String input;
    private int strokes, index;

    public TypingPanel(final Typer typer, final Level l) {
        line = new JLabel("|" + (lineText = l.getRandomString()));
        line.setFont(Utils.LARGE_FONT);

        time = new JLabel("0");
        time.setFont(Utils.LARGE_FONT);

        input = "";

        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(0, 5, 0, 5));

        add(line, BorderLayout.LINE_START);
        add(time, BorderLayout.LINE_END);

        timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time.setText(String.valueOf(Integer.parseInt(time.getText()) + 1));
            }
        });

        typer.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((input + e.getKeyChar()).equals(lineText)) {
                    timer.stop();

                    final double
                            t = Integer.valueOf(time.getText()),
                            accu = Utils.fixNumber(((double) l.getLength() / strokes) * 100, 0, 100);

                    double gradeNum = Math.abs(t - 100) / 10 + accu / 50;
                    final char grade;

                    if (gradeNum > 8) grade = 'A';
                    else if (gradeNum > 6) grade = 'B';
                    else if (gradeNum > 4) grade = 'C';
                    else if (gradeNum > 2) grade = 'D';
                    else grade = 'F';

                    typer.endRound(new Double(t).intValue(), new Double(accu).intValue(), grade);

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

        typer.setTitle("Level " + l.getName());
        typer.setSize(line.getFontMetrics(line.getFont()).stringWidth(line.getText()) + 80, 150);
        typer.setLocationRelativeTo(null);
    }
}