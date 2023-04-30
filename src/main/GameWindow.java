package main;
import javax.swing.JFrame;

/**
 * Esto es solo la ventana y sus dimensiones
 */

public class GameWindow {
    private JFrame jframe;

    public GameWindow(GamePanel gamePanel) {
        jframe = new JFrame();

        jframe.setDefaultCloseOperation(jframe.EXIT_ON_CLOSE);
        jframe.add(gamePanel);
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(true);
        jframe.pack();
        jframe.setVisible(true);
    }


}
