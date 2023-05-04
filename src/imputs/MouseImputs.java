package imputs;

import gameStates.GameState;
import main.GamePanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseImputs implements MouseListener, MouseMotionListener {

    private GamePanel gamePanel;
    public MouseImputs(GamePanel gamePanel){
        this.gamePanel=gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (GameState.state){

            case PLAYING:
                gamePanel.getGame().getPlaying().mouseClicked(e);
                break;
            case MENU:
                gamePanel.getGame().getMenu().mouseClicked(e);
                break;
            default:
                break;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}