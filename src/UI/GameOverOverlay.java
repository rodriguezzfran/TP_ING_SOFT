package UI;

import gamestates.Gamestate;
import gamestates.Playing;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class GameOverOverlay {

    private Playing playing;
    private BufferedImage img;
    private int imgX, imgY, imgW, imgH;

    public GameOverOverlay(Playing playing){
        this.playing=playing;
        CreateImg();

    }

    private void CreateImg() {
        img = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[2];
        imgW = (int) (img.getWidth() * Game.SCALE);
        imgH = (int) (img.getHeight() * Game.SCALE);
        imgX = Game.GAME_WIDTH/2 - imgW/2;
        imgY = (int) (100*Game.SCALE);
    }

    public void draw(Graphics g){
        g.setColor(new Color(0,0,0,200));
        g.fillRect(0,0, Game.GAME_WIDTH,Game.GAME_HEIGHT);
        g.drawImage(img,imgX,imgY,imgW,imgH,null);
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

}