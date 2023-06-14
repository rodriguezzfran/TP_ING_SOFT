package Tests;

import behaviors.damage.Damage4;
import behaviors.damage.DamageBehavior;
import behaviors.health.Health3;
import behaviors.health.Health4;
import behaviors.health.HealthBehavior;
import entities.*;
import gamestates.*;
import junit.framework.TestCase;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;
import java.awt.Robot;
import static utilz.HelpMethods.*;


public class Tests extends TestCase {
    int xTest = 259;
    int yTest = 435;
    Rectangle2D.Float hitBox;
    Game game;
    Playing playing;
    Enemy kingPig, crabby;

    private void jibos() {
        hitBox = new Rectangle2D.Float(xTest, yTest, 20 * Game.SCALE, 27 * Game.SCALE);
    }

    private void createNewPlaying() {
        playing = new Playing(game);
    }

    private void createTestEnemys() {
        kingPig = new KingPig(2, 2);
        crabby = new Crabby(2, 2);
    }

    public void testGetEntityXPosNextToWallRight() {
        jibos();
        assertEquals(279, (int) GetEntityXPosNextToWall(hitBox, 3)); //87 es lo que debería volver
    }

    public void testGetEntityXPosNextToWallLeft() {
        jibos();
        assertEquals(256, (int) GetEntityXPosNextToWall(hitBox, -3)); //87 es lo que debería volver
    }

    public void testGetEntityYPosUnderRoofOrAboveFloorDown() {
        jibos();
        assertEquals(393, (int) GetEntityYPosUnderRoofOrAboveFloor(hitBox, 0.04f)); //87 es lo que debería volver
    }

    public void testGetEntityYPosUnderRoofOrAboveFloorUp() {
        jibos();
        assertEquals(384, (int) GetEntityYPosUnderRoofOrAboveFloor(hitBox, -2.25f)); //87 es lo que debería volver
    }

    public void testMoveRight() {
        createNewPlaying();
        try {
            Robot robot = new Robot();
            robot.keyPress(KeyEvent.VK_D);
            robot.keyRelease(KeyEvent.VK_D);
        } catch (AWTException e) {
            e.printStackTrace();
        }

        Button a = new Button();
        KeyEvent e = new KeyEvent(a, 401, 1686669135712l, 0, 68, 'd');
        playing.keyPressed(e);
        assertTrue(playing.getPlayer().getRight());

    }

    public void testBehaviors() {
        createTestEnemys();

        assertEquals(30, crabby.getDamageBehavior().getDamage());
        assertEquals(20, crabby.getHealthBehavior().getHealth());
        assertEquals(1, kingPig.getDamageBehavior().getDamage());
        assertEquals(1, kingPig.getHealthBehavior().getHealth());

        assertTrue(Game.TILES_SIZE * 2 == crabby.getRangeBehavior().getAttackDistance() && crabby.getRangeBehavior().getSightDistance() == Game.TILES_SIZE * 4.5f);
        assertTrue(kingPig.getRangeBehavior().getAttackDistance() == Game.TILES_SIZE && kingPig.getRangeBehavior().getSightDistance() == Game.TILES_SIZE * 3);


        DamageBehavior damageC, damageKP;
        HealthBehavior healthC, healthKP;
        damageC = new Damage4();
        healthC = new Health3();
        damageKP = new Damage4();
        healthKP = new Health4();
        crabby.setEnemyDamage(damageC);
        crabby.setEnemyMaxHealth(healthC);
        kingPig.setEnemyDamage(damageKP);
        kingPig.setEnemyMaxHealth(healthKP);

        assertEquals(damageC.getDamage(), crabby.getDamageBehavior().getDamage());
        assertEquals(healthC.getHealth(), crabby.getHealthBehavior().getHealth());
        assertEquals(damageKP.getDamage(), kingPig.getDamageBehavior().getDamage());
        assertEquals(healthKP.getHealth(), kingPig.getHealthBehavior().getHealth());
    }

}
