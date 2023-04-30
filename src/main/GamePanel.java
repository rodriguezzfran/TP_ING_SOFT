package main;

import Imputs.KeyBoardImputs;
import Imputs.MouseImputs;

import javax.swing.JPanel;
import java.awt.*;

/**
 * Esto es el lienzo, el juego en si mismo
 */

public class GamePanel extends JPanel{

    private MouseImputs mouseImputs;
    private Game game;


    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        this.mouseImputs=new MouseImputs(this);
        addKeyListener(new KeyBoardImputs(this));
        addMouseListener(mouseImputs);
        addMouseMotionListener(mouseImputs);
    }


    /**
     * Importación de los sprites.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(1280,800);
        setMinimumSize(size);
        setPreferredSize(size);
        setMaximumSize(size);
    }


    /**
     * Update the logics of the game.
     */
    public void updateGame() {

    }

    /**
     * Makes the render.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame(){
        return game;
    }

    
}
