package com.example.demo.state;

import javax.swing.*;
import java.awt.*;

public class Ui {
    private Player player;
    private static JTextField textField = new JTextField();

    public Ui(Player player) {
        this.player = player;
    }

    public void init(){
        JFrame frame = new JFrame("Test Player");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel context = new JPanel();
        context.setLayout(new BoxLayout(context, BoxLayout.Y_AXIS));

        Container contentPane = frame.getContentPane();
        contentPane.add(context);

        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        context.add(textField);
        context.add(buttons);

        JButton play = new JButton("Play");
        play.addActionListener(e -> textField.setText(player.playing()));

        JButton stop = new JButton("Stop");
        stop.addActionListener(e -> textField.setText(player.stopping()));

        JButton next = new JButton("Next");
        next.addActionListener(e -> textField.setText(player.nextPlaying()));

        JButton prev = new JButton("Prev");
        prev.addActionListener(e -> textField.setText(player.prevPlaying()));

        frame.setVisible(true);
        frame.setSize(300, 100);

        buttons.add(play);
        buttons.add(stop);
        buttons.add(next);
        buttons.add(prev);

    }
}
