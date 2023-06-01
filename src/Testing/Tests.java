package Testing;

import junit.framework.TestCase;
import main.Game;

import java.awt.geom.Rectangle2D;

import static utilz.HelpMethods.*;


public class Tests extends TestCase {
    int xTest=259;
    int yTest=435;
    Rectangle2D.Float hitBox;
    public void jibos(){
        hitBox = new Rectangle2D.Float(xTest,yTest,20* Game.SCALE, 27*Game.SCALE);
    }
    public void testGetEntityXPosNextToWallRight(){
        jibos();
        assertEquals(279,(int)getEntityXPosNextToWall(hitBox,3)); //87 es lo que debería volver
    }

    public void testGetEntityXPosNextToWallLeft(){
        jibos();
        assertEquals(256,(int)getEntityXPosNextToWall(hitBox,-3)); //87 es lo que debería volver
    }

    public void testGetEntityYPosUnderRoofOrAboveFloorDown(){
        jibos();
        assertEquals(393,(int)getEntityYPosUnderRoofOrAboveFloor(hitBox,0.04f)); //87 es lo que debería volver
    }

    public void testGetEntityYPosUnderRoofOrAboveFloorUp(){
        jibos();
        assertEquals(384,(int)getEntityYPosUnderRoofOrAboveFloor(hitBox,-2.25f)); //87 es lo que debería volver
    }
}
