package main;

import Imputs.KeyBoardImputs;
import Imputs.MouseInputs;
import javax.swing.JPanel;
import java.awt.*;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

/**
 * Esto es el lienzo, el juego en si mismo
 */

public class GamePanel extends JPanel{

    private MouseInputs mouseImputs;
    private Game game;


    public GamePanel(Game game){
        this.game = game;
        setPanelSize();
        this.mouseImputs=new MouseInputs(this);
        addKeyListener(new KeyBoardImputs(this));
        addMouseListener(mouseImputs);
        addMouseMotionListener(mouseImputs);
    }


    /**
     * Importación de los sprites.
     */
    private void setPanelSize() {
        Dimension size = new Dimension(game.GAME_WIDTH,game.GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size: "+GAME_WIDTH + " : "+GAME_HEIGHT);
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
