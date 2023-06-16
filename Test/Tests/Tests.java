package Tests;

import behaviors.damage.*;
import behaviors.health.Health3;
import behaviors.health.Health4;
import behaviors.health.HealthBehavior;
import behaviors.rangeenemies.LongDistance;
import behaviors.rangeenemies.RangeEnemiesBehavior;
import entities.*;
import gamestates.*;
import junit.framework.TestCase;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

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

    private void createNewGame() {
        game = new Game();
        playing = new Playing(game);
    }

    private void createTestEnemys() {
        kingPig = new KingPig(2, 2,1);
        crabby = new Crabby(2, 2,1);
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
        createNewGame();
        Button a = new Button();
        KeyEvent e = new KeyEvent(a, 401, 1686669135712l, 0, 68, 'd');
        playing.keyPressed(e);
        assertTrue(playing.getPlayer().getRight());

    }

    public void testMoveLeft() {
        createNewGame();
        Button a = new Button();
        KeyEvent e = new KeyEvent(a, 401, 1686954305873l, 0, 65, 'a');
        playing.keyPressed(e);
        assertTrue(playing.getPlayer().getLeft());

    }

    public void testMoveJump() {
        createNewGame();
        Button a = new Button();
        KeyEvent e = new KeyEvent(a, 401, 1686954469610l, 0, 32, 's');
        playing.keyPressed(e);
        assertTrue(playing.getPlayer().getJump());

    }

    public void testBehaviors() {
        createTestEnemys();

        assertEquals(10, crabby.getDamageBehavior().getDamage());
        assertEquals(20, crabby.getHealthBehavior().getHealth());
        assertEquals(10, kingPig.getDamageBehavior().getDamage());
        assertEquals(20, kingPig.getHealthBehavior().getHealth());

        assertTrue(Game.TILES_SIZE * 2 == crabby.getRangeBehavior().getAttackDistance() && crabby.getRangeBehavior().getSightDistance() == Game.TILES_SIZE * 4.5f);
        assertTrue(kingPig.getRangeBehavior().getAttackDistance() == Game.TILES_SIZE && kingPig.getRangeBehavior().getSightDistance() == Game.TILES_SIZE * 3);


        DamageBehavior damageC, damageKP;
        HealthBehavior healthC, healthKP;
        damageC = new Damage4();
        healthC = new Health3();
        damageKP = new Damage4();
        healthKP = new Health4();
        crabby.setEntityDamage(damageC);
        crabby.setEntityMaxHealth(healthC);
        kingPig.setEntityDamage(damageKP);
        kingPig.setEntityMaxHealth(healthKP);

        assertEquals(damageC.getDamage(), crabby.getDamageBehavior().getDamage());
        assertEquals(healthC.getHealth(), crabby.getHealthBehavior().getHealth());
        assertEquals(damageKP.getDamage(), kingPig.getDamageBehavior().getDamage());
        assertEquals(healthKP.getHealth(), kingPig.getHealthBehavior().getHealth());
    }

    public void testEscalarVidaEnemigos(){
        createTestEnemys();
        createNewGame();
        Damage1 d1 = new Damage1();
        Damage2 d2 = new Damage2();
        Damage3 d3 = new Damage3();

        System.out.println(game.getPlaying().getLevelManager().getCurrentLevel().getEnemies().get(0).getDamageBehavior().getDamage());
        assertEquals(d1.getDamage(),game.getPlaying().getLevelManager().getCurrentLevel().getEnemies().get(0).getDamageBehavior().getDamage());
        game.getPlaying().loadNextLevel();
        assertEquals(d2.getDamage(),game.getPlaying().getLevelManager().getCurrentLevel().getEnemies().get(0).getDamageBehavior().getDamage());
        game.getPlaying().loadNextLevel();
        assertEquals(d3.getDamage(),game.getPlaying().getLevelManager().getCurrentLevel().getEnemies().get(0).getDamageBehavior().getDamage());

    }
}
