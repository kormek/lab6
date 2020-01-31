package bsu.rfe.java.group10.lab6.anufriev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

    private static final int Height = 500;
    private static final int Width = 700;

    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem eraseMenuItem;

    private int stateErase = 0;

    private Field field = new Field();

    public MainFrame() {
        super("Programming and stream synchronization");
        setSize(Height,Width);
        Toolkit kit = Toolkit.getDefaultToolkit();

        setLocation((kit.getScreenSize().width - Width)/2,(kit.getScreenSize().height - Height)/2);

        setExtendedState(MAXIMIZED_BOTH);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu ballMenu = new JMenu("Balls");
        Action addActionBall = new AbstractAction("Add ball") {
            @Override
            public void actionPerformed(ActionEvent e) {
                field.addBall();
                if(!pauseMenuItem.isEnabled() && !resumeMenuItem.isEnabled()) {
                    pauseMenuItem.setEnabled(true);
                }
            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addActionBall);

        JMenu controlMenu = new JMenu("Controls");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Pause") {
            @Override
            public void actionPerformed(ActionEvent e) {

                field.pause();
                pauseMenuItem.setEnabled(false);
                resumeMenuItem.setEnabled(true);
            }
        };
        pauseMenuItem = controlMenu.add(pauseAction);
        pauseMenuItem.setEnabled(false);

        Action resumeAction = new AbstractAction("Resume") {
            @Override
            public void actionPerformed(ActionEvent e) {

                field.resume();
                pauseMenuItem.setEnabled(true);
                resumeMenuItem.setEnabled(false);
            }
        };
        resumeMenuItem = controlMenu.add(resumeAction);
        resumeMenuItem.setEnabled(false);

        Action eraseAction = new AbstractAction("Erase") {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel p = new JPanel();
                JTextField inputField = new JTextField(10);
                p.add(inputField);
                JOptionPane.showConfirmDialog(null, p, "Type smth ", JOptionPane.OK_CANCEL_OPTION);
                field.setStateErase(Integer.parseInt(inputField.getText()));
            }
        };
        eraseMenuItem = controlMenu.add(eraseAction);
        eraseMenuItem.setEnabled(true);

        getContentPane().add(field,BorderLayout.CENTER);
    }

}
