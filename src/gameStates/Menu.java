package gameStates;

import main.Game;
import UI.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods{

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backGroundImg;
    private int menuX,menuY,menuWidth,menuHeight;

    public Menu(Game game) {

        super(game);
        loadButtons();
        loadBackground();
    }

    private void loadBackground() {
        backGroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[1];
        menuWidth = (int)(backGroundImg.getWidth()*Game.SCALE);
        menuHeight = (int)(backGroundImg.getHeight()*Game.SCALE);
        menuX = Game.GAME_WIDTH/2 - menuWidth/2;
        menuY = (int)(45*Game.SCALE);
    }

    private void loadButtons() {
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2, (int)(Game.SCALE*150),0,GameState.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2, (int)(Game.SCALE*220),1,GameState.OPTION);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2, (int)(Game.SCALE*290),2,GameState.QUIT);
    }

    @Override
    public void update() {
            for(MenuButton mb: buttons){
                mb.update();
            }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(backGroundImg,menuX,menuY,menuWidth,menuHeight,null);
        for(MenuButton mb: buttons){
            mb.draw(g);
        }



    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        for(MenuButton mb: buttons){
            if(isIn(e,mb)){
                mb.setMousePressed(true);
                break;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(MenuButton mb: buttons){
            if(isIn(e,mb)) {
                if (mb.getMousePressed()) {
                    mb.applyGameState();
                    break;
                }
            }
        }
        resetButtons();
    }

    private void resetButtons(){
        for(MenuButton mb : buttons){
            mb.resetBools();
        }
    }
    @Override
    public void mouseMoved(MouseEvent e) {
        for(MenuButton mb : buttons){
            mb.setMouseOver(false);
        }
        
        for(MenuButton mb: buttons){
            if(isIn(e,mb)){
                mb.setMouseOver(true);
                break;
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER)
            GameState.state = GameState.PLAYING;
        System.out.println(GameState.state);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
