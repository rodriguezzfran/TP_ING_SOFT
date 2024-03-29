package gamestates;

import main.Game;
import UI.MenuButton;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Menu extends State implements StateMethods{

    private MenuButton[] buttons = new MenuButton[3];
    private BufferedImage backGroundImg,backgroundImgPink;
    private int menuX,menuY,menuWidth,menuHeight;

    public Menu(Game game) {

        super(game);
        loadButtons();
        loadBackground();
        backgroundImgPink = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[4]; //Fondo de la pantalla de menu
    }

    private void loadBackground() {
        backGroundImg = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[1]; //Cuadro del menu (no imagen de fondo)
        menuWidth = (int)(backGroundImg.getWidth()*Game.SCALE);
        menuHeight = (int)(backGroundImg.getHeight()*Game.SCALE);
        menuX = Game.GAME_WIDTH/2 - menuWidth/2;
        menuY = (int)(45*Game.SCALE);
    }

    private void loadButtons() {
        //desp cambiar esto
        buttons[0] = new MenuButton(Game.GAME_WIDTH/2-68, (int)(Game.SCALE*105+50),0, Gamestate.PLAYING);
        buttons[1] = new MenuButton(Game.GAME_WIDTH/2-68, (int)(Game.SCALE*135+50),1, Gamestate.OPTION);
        buttons[2] = new MenuButton(Game.GAME_WIDTH/2-68, (int)(Game.SCALE*165+50),2, Gamestate.QUIT);
    }

    @Override
    public void update() {
            for(MenuButton mb: buttons){
                mb.update();
            }
    }

    @Override
    public void draw(Graphics g) {
        //ver esto tambien
        g.drawImage(backgroundImgPink,0,0,Game.GAME_WIDTH-40,Game.GAME_HEIGHT-40,null);
        g.drawImage(backGroundImg,menuX,menuY+50,menuWidth/2,menuHeight/2,null);
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
            Gamestate.state = Gamestate.PLAYING;
        //System.out.println(Gamestate.state);

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
