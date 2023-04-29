package imputs;

/**
 * Es una clase que implementa el keylistener para saber que estoy tocando
 */

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
                System.out.println("W");
                break;
            case KeyEvent.VK_S:
                System.out.println("S");
                break;
            case KeyEvent.VK_D:
                System.out.println("D");
                break;
            case KeyEvent.VK_A:
                System.out.println("A");
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Espacio");
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
