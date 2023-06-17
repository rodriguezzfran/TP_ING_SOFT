package UI;

import gamestates.Gamestate;
import utilz.LoadSave;
import static utilz.Constants.UI.Buttons.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MenuButton {
    private int xPos,yPos,rowIndex,index;
    private int xOffsetCenter = B_WIDTH/2;
    private Gamestate state;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;

    private BufferedImage[] imgs;
    public MenuButton(int xPos, int yPos, int rowIndex, Gamestate state){
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        this.state = state;
        loadImgs();
        initBounds();

    }

    private void initBounds() {
        bounds = new Rectangle(xPos - xOffsetCenter, yPos, B_WIDTH/2, B_HEIGHT/2);
    }

    private void loadImgs(){
        imgs = new BufferedImage[3];
        BufferedImage temp = LoadSave.GetSpriteAtlas(LoadSave.MENU_BUTTONS)[0]; //Play-Options-Quit buttons
        for(int i = 0; i <imgs.length;i++){
            imgs[i] = temp.getSubimage(i*B_WIDTH_DEFAULT,rowIndex* B_HEIGHT_DEFAULT, B_WIDTH_DEFAULT, B_HEIGHT_DEFAULT);
        }

    }

    public void draw(Graphics g){
        g.drawImage(imgs[index],xPos- xOffsetCenter,yPos,B_WIDTH/2,B_HEIGHT/2,null);
    }

    public void update(){
        index = 0;
        if(mouseOver){
            index = 1;
        }
        if(mousePressed){
            index = 2;
        }
    }

    public boolean getMouseOver() {
        return mouseOver;
    }

    public void setMouseOver(boolean mouseOver) {
        this.mouseOver = mouseOver;
    }

    public boolean getMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void applyGameState(){
        Gamestate.state = state;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
    public Rectangle getBounds(){
        return bounds;
    }
}
