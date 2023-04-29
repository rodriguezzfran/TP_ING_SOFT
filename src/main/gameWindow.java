package main;

import javax.swing.*;

public class gameWindow {
    private JFrame jframe;

    public gameWindow(gameScreen gameScreen) {
        jframe = new JFrame();

        jframe.setSize(400, 400);
        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.add(gameScreen);
        jframe.setLocationRelativeTo(null);
        jframe.setVisible(true);
    }
}
