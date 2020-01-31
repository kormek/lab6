package bsu.rfe.java.group10.lab6.anufriev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Field extends JPanel {

    private boolean pause = false;
    private int stateErase = 0;

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
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

       // System.out.println("paintComponents");
        Graphics2D canvas = (Graphics2D) g;
        for(StreamBall ball: Balls) {
            ball.paint(canvas);
            ball.setStateErase(stateErase);
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


    public void stopBall() throws  InterruptedException{
      wait();

    }

    public void setStateErase(int erase){
        this.stateErase = erase;

    }

    public synchronized void canMove(StreamBall Ball) throws InterruptedException {
        if(pause) wait();
    }
}
