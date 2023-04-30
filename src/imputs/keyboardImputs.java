package imputs;

import main.GamePanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){

            case KeyEvent.VK_W:
                gamePanel.changeYDelta(-velocity);
                break;
            case KeyEvent.VK_A:
                gamePanel.changeXDelta(-velocity);
                break;
            case KeyEvent.VK_S:
                gamePanel.changeYDelta(velocity);
                break;
            case KeyEvent.VK_D:
                gamePanel.changeXDelta(velocity);
                break;
        }
    }
}
