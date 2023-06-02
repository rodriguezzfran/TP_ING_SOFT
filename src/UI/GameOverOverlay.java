package UI;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import UI.UrmButton;


public class GameOverOverlay {

    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;
    private UrmButton menu,play;

    public GameOverOverlay(Playing playing){
        this.playing=playing;
        CreateImg();
        CreateButtons();

    }

    private void CreateButtons(){
        int menuX =(int)(335*Game.SCALE-100);
        int playX = (int)(440*Game.SCALE-160);
        int y =(int)(195*Game.SCALE-40);
        play = new UrmButton(playX,y,Constants.UI.URMButton.URM_SIZE,Constants.UI.URMButton.URM_SIZE,0);
        menu = new UrmButton(menuX,y,Constants.UI.URMButton.URM_SIZE,Constants.UI.URMButton.URM_SIZE,2);
    }

    private void CreateImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[2];
        imgW = (int) (img.getWidth() * Game.SCALE/1.5);
        imgH = (int) (img.getHeight() * Game.SCALE/1.5);
        imgX = Game.GAME_WIDTH/2 - imgW;
        imgY = (int) (100*Game.SCALE);
    }

    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0, Game.GAME_WIDTH,Game.GAME_HEIGHT);
        g.drawImage(img,imgX,imgY,imgW,imgH,null);
        menu.draw(g);
        play.draw(g);
        /**
        g.setColor(Color.white);
        g.drawString("Game Over", Game.GAME_WIDTH/2,150);
        g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH/2,300);
        **/
    }

    public void keyPressed(KeyEvent e){
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE){
            playing.resetAll();
            Gamestate.state = Gamestate.MENU;
        }
    }
    private boolean isIn(UrmButton b, MouseEvent e){
        return b.getBounds().contains(e.getX(),e.getY());
    }
    public void mouseMoved(MouseEvent e){
        play.setMouseOver(false);
        menu.setMouseOver(false);

        if(isIn(menu,e))
            menu.setMouseOver(true);
        else if (isIn(play,e)) {
            play.setMouseOver(true);
        }
    }

    public void mouseReleased(MouseEvent e){
        if(isIn(menu,e)){
            if(menu.isMousePressed()){
                playing.resetAll();
                Gamestate.state = Gamestate.MENU;

            }
        } else if (isIn(play,e)) {
            if(play.isMousePressed()){

            }
        }
        menu.resetBooleans();
        play.resetBooleans();
    }
    public void update(){
        menu.update();
        play.update();
    }
    public void mousePressed(MouseEvent e){
        if(isIn(menu,e)){
            menu.setMousePressed(true);
        }
        else if(isIn(play,e)){
            play.setMousePressed(true);
        }
    }

}
