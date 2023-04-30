package Imputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static utils.Constants.Direction.*;

public class KeyBoardImputs implements KeyListener {

    private GamePanel gamePanel;
    private final int velocity=5;
    public KeyBoardImputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_A:
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_S:
                gamePanel.setMoving(false);
                break;
            case KeyEvent.VK_D:
                gamePanel.setMoving(false);
                break;
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                gamePanel.setDirection(UP);
                break;
            case KeyEvent.VK_A:
                gamePanel.setDirection(LEFT);
                break;
            case KeyEvent.VK_S:
                gamePanel.setDirection(DOWN);
                break;
            case KeyEvent.VK_D:
                gamePanel.setDirection(RIGHT);
                break;
        }
    }
}
