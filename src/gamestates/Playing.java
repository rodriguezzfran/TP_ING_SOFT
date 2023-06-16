package gamestates;

import UI.*;
import entities.EnemyManager;
import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.awt.Color;
import java.awt.Graphics;

import UI.GameOverOverlay;
import UI.LevelCompletedOverlay;
import observables.HealthObservable;

import static utilz.HelpMethods.*;



public class Playing extends State implements StateMethods {

    private Player player;
    private LevelManager levelManager;
    private EnemyManager enemyManager;
    private GameOverOverlay gameOverOverlay;
    private PauseOverlay pauseOverlay;
    private LevelCompletedOverlay levelCompletedOverlay;
    private boolean gameOver;
    private boolean lvlCompleted;

    private boolean paused = false;


    public Playing(Game game) {
        super(game);
        initClases();
        loadStartLevel();
    }
    private void initClases() {
        levelManager = new LevelManager(game);
        enemyManager = new EnemyManager(this);

        player = new Player(270,200,(int)(78*Game.SCALE),(int)(58*Game.SCALE),this); //78 y 58
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());

        gameOverOverlay= new GameOverOverlay(this);
        pauseOverlay = new PauseOverlay(this);
        levelCompletedOverlay = new LevelCompletedOverlay(this);
    }

    private void loadStartLevel() {
        enemyManager.loadEnemies(levelManager.getCurrentLevel());
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }


    @Override
    public void update() {
        if (paused) {
            pauseOverlay.update();
        } else if (lvlCompleted) {
            levelCompletedOverlay.update();
        }else if (gameOver) {
            gameOverOverlay.update();
        }
        else{
            levelManager.update();
            player.update();
            enemyManager.update(levelManager.getCurrentLevel().getLvlData(), player);
        }
   }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
        enemyManager.draw(g); //desp ver lo de ofset para level estirado
        if (paused) {
            g.setColor(new Color(0, 0, 0, 150));
            g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
            pauseOverlay.draw(g);
        } else if (gameOver)
            gameOverOverlay.draw(g);
        else if (lvlCompleted)
            levelCompletedOverlay.draw(g);
    }

    public void resetAll(){
        gameOver=false;
        paused=false;
        player.resetAll();
        enemyManager.resetAllEnemies();
        lvlCompleted = false;
    }

    public void loadNextLevel() {
        levelManager.loadNextLevel();
        player.setSpawn(levelManager.getCurrentLevel().getPlayerSpawn());
        getPlayer().setEntityMaxHealth(GetPlayerHealthBehaviorByLvl(levelManager.getLvlIndex()));
        getPlayer().setEntityDamage(GetPlayerDamageBehaviorByLvl(levelManager.getLvlIndex()));
        resetAll();
    }

    public void setGameOver(boolean gameOver){
        this.gameOver = gameOver;
    }
    public void checkEnemyHit(Rectangle2D.Float attackBox){
        enemyManager.checkEnemyHit(attackBox);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(!gameOver) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                player.setAttacking(true);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!gameOver) {
            if (paused)
                pauseOverlay.mousePressed(e);
            else if (lvlCompleted)
                levelCompletedOverlay.mousePressed(e);
        }else{
            gameOverOverlay.mousePressed(e);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (!gameOver) {
            if (paused) {
                pauseOverlay.mouseReleased(e);
            }
            else if (lvlCompleted) {
                levelCompletedOverlay.mouseReleased(e);
            }
        }else{
            gameOverOverlay.mouseReleased(e);
        }
    }


    @Override
    public void mouseMoved(MouseEvent e) {
        if (!gameOver) {
            if (paused)
                pauseOverlay.mouseMoved(e);
            else if (lvlCompleted)
                levelCompletedOverlay.mouseMoved(e);
        }else {
            gameOverOverlay.mouseMoved(e);
        }
    }
    public void mouseDragged(MouseEvent e) {
        if (!gameOver)
            if (paused)
                pauseOverlay.mouseDragged(e);
    }



    @Override
    public void keyPressed(KeyEvent e) {
        if(gameOver){
            gameOverOverlay.keyPressed(e);
        }
        else {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    player.setLeft(true);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(true);
                    break;
                case KeyEvent.VK_SPACE:
                    System.out.println(e.getComponent() + "  " + e.getID() + "  " + e.getWhen() + "  " + e.getModifiers() + " " + e.getKeyCode() + "  "+ e.getKeyChar());
                    player.setJump(true);
                    break;
                case KeyEvent.VK_ESCAPE:
                    paused = !paused;
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(!gameOver) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_A:
                    player.setLeft(false);
                    break;
                case KeyEvent.VK_D:
                    player.setRight(false);
                    break;
                case KeyEvent.VK_SPACE:
                    player.setJump(false);
                    break;
            }
        }

    }

    public void unpauseGame() {
        paused = false;
    }
    public Player getPlayer(){
        return player;
    }
    public EnemyManager getEnemyManager() {
        return enemyManager;
    }
    public LevelManager getLevelManager() {
        return levelManager;
    }
    public void setLevelCompleted(boolean levelCompleted) {
        this.lvlCompleted = levelCompleted;
    }

}
