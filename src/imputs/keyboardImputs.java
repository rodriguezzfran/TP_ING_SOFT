package imputs;

/**
 * Es una clase que implementa el keylistener para saber que estoy tocando
 */

import main.gameScreen;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

public class keyboardImputs implements KeyListener {
   private boolean pressedW = false;
   private boolean pressedA = false;
   private boolean pressedS = false;
   private boolean pressedD = false;
   private  boolean pressedSpace = false;
   private gameScreen gameScreen;
   public keyboardImputs(gameScreen gameScreen){
        this.gameScreen = gameScreen;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    /**
     * Este método lee el codigo de la tecla presionada
     * segun lo que venga se ejecuta el caso de la tecla correspondiente
     * el while sirve para que la acción se repita mientras la tecla esté presionada
     * @param e Es el evento de precionar una tecla
     */

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                gameScreen.cambiarDeltaY(-5);
                break;

            case KeyEvent.VK_S:
                gameScreen.cambiarDeltaY(5);
                break;

            case KeyEvent.VK_D:
                gameScreen.cambiarDeltaX(5);
                break;

            case KeyEvent.VK_A:
                pressedA = true;
                gameScreen.cambiarDeltaX(-5);

            case KeyEvent.VK_SPACE:
                if(pressedSpace == false) {
                    gameScreen.cambiarDeltaY(-50);
                    pressedSpace = true;
                    break;
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
           if(e.getKeyCode() == KeyEvent.VK_SPACE){
               try {
                   TimeUnit.MILLISECONDS.sleep(100);
               } catch (InterruptedException ex) {
                   throw new RuntimeException(ex);
               }
               gameScreen.cambiarDeltaY(50);
               pressedSpace = false;
           }
        }

}
