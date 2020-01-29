package bsu.rfe.java.group10.lab6.anufriev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {

    private boolean pause = false;

    private ArrayList<StreamBall> Balls = new ArrayList<StreamBall>(10);

    private Timer repaintTimer = new Timer(10, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    });

    public Field(){
        setBackground(Color.white);

        repaintTimer.start();
    }

    @Override
    public void paintComponents(Graphics g) {
        super.paintComponents(g);

        Graphics2D canvas = (Graphics2D) g;
        for(StreamBall ball: Balls) {
            ball.paint(canvas);
        }
    }

    public void addBall(){
        Balls.add(new StreamBall(this));
    }

    public synchronized void pause(){
        pause = true;
    }

    public synchronized void resume() {
        pause = false;

        notifyAll();
    }

    public synchronized void canMove(StreamBall Ball) throws InterruptedException {
        if(pause) wait();
    }
}
