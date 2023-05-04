package gameStates;

import entities.Player;
import levels.LevelManager;
import main.Game;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements StateMethods {

    private Player player;
    private LevelManager levelManager;

    public Playing(Game game) {
        super(game);
        initClases();
    }
    private void initClases() {
        levelManager = new LevelManager(game);
        player = new Player(270,200,(int)(78*Game.SCALE),(int)(58*Game.SCALE)); //78 y 58
        player.loadLvlData(levelManager.getCurrentLevel().getLvlData());
    }

    public void windowFocusLost() {
        player.resetDirBooleans();
    }


    @Override
    public void update() {
        levelManager.update();
        player.update();
    }

    @Override
    public void draw(Graphics g) {
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            player.setAttacking(true);
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W:
                System.out.println("Tecla W apretada");
                player.setUp(true);
                break;
            case KeyEvent.VK_A:
                System.out.println("Tecla A apretada");
                player.setLeft(true);
                break;
            case KeyEvent.VK_S:
                System.out.println("Tecla S apretada");
                player.setDown(true);
                break;
            case KeyEvent.VK_D:
                System.out.println("Tecla D apretada");
                player.setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                System.out.println("Tecla Espacio apretada");
                player.setJump(true);
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_W:
                player.setUp(false);
                break;
            case KeyEvent.VK_A:
                player.setLeft(false);
                break;
            case KeyEvent.VK_S:
                player.setDown(false);
                break;
            case KeyEvent.VK_D:
                player.setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                player.setJump(false);
                break;

        }

    }

    public Player getPlayer(){
        return player;
    }
}
