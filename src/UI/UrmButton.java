package UI;

import utilz.Constants;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import utilz.Constants.UI.URMButton;

public class UrmButton extends PauseButton{
    private BufferedImage[] imgs;
    private int rowIndex,index;
    private boolean mouseOver,mousePressed;
    public UrmButton(int x, int y, int width, int height,int rowIndex) {
        super(x, y, width, height);
        this.rowIndex = rowIndex;
        loadImages();
    }
    private void loadImages(){
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[3];
        imgs = new BufferedImage[3];
        for(int i = 0;i< imgs.length;i++){
            imgs[i] = temp.getSubimage(i*URMButton.URM_DEFAULT_SIZE,rowIndex* URMButton.URM_DEFAULT_SIZE,URMButton.URM_DEFAULT_SIZE,URMButton.URM_DEFAULT_SIZE);
        }

    }
    public void draw(Graphics g){
        g.drawImage(imgs[index],x,y,URMButton.URM_SIZE/2,URMButton.URM_SIZE/2,null);
    }
    public void update(){
        index = 0;
        if(mouseOver)
            index = 1;
        if(mousePressed)
            index = 2;

    }
    public void resetBooleans(){
        mousePressed = false;
        mouseOver = false;
    }

    public boolean isMouseOver() {
        return mouseOver;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }
}
