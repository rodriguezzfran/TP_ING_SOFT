package testing;

import gamestates.Playing;
import junit.framework.TestCase;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

import static utilz.HelpMethods.*;


public class Tests extends TestCase {
    int xTest=259;
    int yTest=435;
    Rectangle2D.Float hitBox;
    Game game;
    Playing playing;
    public void jibos(){
        hitBox = new Rectangle2D.Float(xTest,yTest,20* Game.SCALE, 27*Game.SCALE);
    }

    public void createNewGame(){
        game  = new Game();
        playing = new Playing(game);
    }
    public void testGetEntityXPosNextToWallRight(){
        jibos();
        assertEquals(278,(int) GetEntityXPosNextToWall(hitBox,3)); //87 es lo que debería volver
    }

    public void testGetEntityXPosNextToWallLeft(){
        jibos();
        assertEquals(256,(int) GetEntityXPosNextToWall(hitBox,-3)); //87 es lo que debería volver
    }

    public void testGetEntityYPosUnderRoofOrAboveFloorDown(){
        jibos();
        assertEquals(393,(int) GetEntityYPosUnderRoofOrAboveFloor(hitBox,0.04f)); //87 es lo que debería volver
    }

    public void testGetEntityYPosUnderRoofOrAboveFloorUp(){
        jibos();
        assertEquals(384,(int) GetEntityYPosUnderRoofOrAboveFloor(hitBox,-2.25f)); //87 es lo que debería volver
    }

    public void testMoveRight(){
        createNewGame();
        Button a = new Button();
        KeyEvent e = new KeyEvent(a, 401, 1686669135712l, 0, 68, 'd');
        assertEquals(true,playing.getPlayer().getRight());

    }

}
