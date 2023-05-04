package imputs;

import gameStates.GameState;
import main.Game;
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
        switch (GameState.state){

            case PLAYING:
                System.out.println("Tecla soltada");
                gamePanel.getGame().getPlaying().keyReleased(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyReleased(e);
                break;
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (GameState.state){

            case PLAYING:
                gamePanel.getGame().getPlaying().keyPressed(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().keyPressed(e);
                break;
        }

    }
}