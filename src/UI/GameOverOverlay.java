package UI;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import static utilz.Constants.UI.URMButtons.URM_SIZE;

public class GameOverOverlay {

    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW,imgH;
    private UrmButton menu,play;

    public GameOverOverlay(Playing playing) {
        this.playing = playing;
        createImg();
        createButtons();
    }

<<<<<<< Updated upstream
    private void createButtons() {
        int menuX = (int)(356*Game.SCALE);
        int playX = (int)(420*Game.SCALE);
        int y =(int)(195*Game.SCALE-40);
        play = new UrmButton(playX,y,URM_SIZE, URM_SIZE,0);
        menu = new UrmButton(menuX,y, URM_SIZE, URM_SIZE,2);
    }

=======
>>>>>>> Stashed changes
    private void createImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[2];//Cuadro del DeathMenu
        imgW = (int) (img.getWidth() * Game.SCALE/1.5);
        imgH = (int) (img.getHeight() * Game.SCALE/1.5);
        imgX = Game.GAME_WIDTH/2 - imgW/2;
        imgY = (int) (100*Game.SCALE);
    }

    private void createButtons() {
        int menuX = imgX +  (int)(25*Game.SCALE);
        int playX = imgX + imgW - (int)(30*Game.SCALE) - URM_SIZE;
        int y =(int)(192*Game.SCALE-40);
        play = new UrmButton(playX,y,URM_SIZE, URM_SIZE,0);
        menu = new UrmButton(menuX,y, URM_SIZE, URM_SIZE,2);
    }


    public void draw(Graphics g) {
        g.setColor(new Color(0, 0, 0, 200));
        g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

        g.drawImage(img,imgX,imgY,imgW,imgH,null);

        menu.draw(g);
        play.draw(g);
    }

    public void update(){
        menu.update();
        play.update();
    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
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
                playing.resetAll();
            }
        }
        menu.resetBools();
        play.resetBools();
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