package imputs;

import gamestates.Gamestate;
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
        switch (Gamestate.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (Gamestate.state){
            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
        }

    }
}
