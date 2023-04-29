package imputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class keyboardImputs implements KeyListener {

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
            case KeyEvent.VK_S:
            case KeyEvent.VK_D:
            case KeyEvent.VK_A:
            case KeyEvent.VK_SPACE:

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
