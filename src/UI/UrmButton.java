package UI;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.Constants.UI.URMButtons;

import utilz.LoadSave;
import static utilz.Constants.UI.URMButtons.*;

public class UrmButton extends PauseButton {
    private BufferedImage[] imgs;
    private int rowIndex, index;
    private boolean mouseOver, mousePressed;

//    ///
//    private BufferedImage[] imgs;
//    private int rowIndex,index;
//    private boolean mouseOver,mousePressed;
//    ///

    public UrmButton(int x, int y, int width, int height, int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImgs();
    }

//    private void loadImgs() {
//        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.URM_BUTTONS)[0];
//        imgs = new BufferedImage[3];
//        for (int i = 0; i < imgs.length; i++) {
//            imgs[i] = temp.getSubimage(i * URM_DEFAULT_SIZE, rowIndex * URM_DEFAULT_SIZE, URM_DEFAULT_SIZE, URM_DEFAULT_SIZE);
//        }
//    }
    private void loadImgs(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[3]; //URM Buttons (3)
        imgs = new BufferedImage[3];
        for(int i = 0;i< imgs.length;i++){
            imgs[i] = temp.getSubimage(i*URMButtons.URM_DEFAULT_SIZE,rowIndex* URMButtons.URM_DEFAULT_SIZE,URMButtons.URM_DEFAULT_SIZE,URMButtons.URM_DEFAULT_SIZE);
        }

    }

    public void update() {
        index = 0;
        if (mouseOver)
            index = 1;
        if (mousePressed)
            index = 2;

    }



    public void resetBools() {
        mouseOver = false;
        mousePressed = false;
    }



//    public void draw(Graphics g) {
//        g.drawImage(imgs[index], x, y, URM_SIZE, URM_SIZE, null);
//    }
    public void draw(Graphics g){
        g.drawImage(imgs[index],x,y,URMButtons.URM_SIZE/2,URMButtons.URM_SIZE/2,null);
    }

    public void resetBooleans() {
        mousePressed = false;
        mouseOver = false;

    }

    public boolean isMouseOver() {
        return mouseOver;
    }
    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
