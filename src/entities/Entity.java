package entities;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected int width, height;
    protected float x,y;
    protected int speed;
    protected Rectangle2D.Float hitBox;
    public Entity(float x, float y,int speed, int width, int height){
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;

    }

    protected void drawHitbox(Graphics g){
        //para debuggear la hitbox
        g.setColor(Color.PINK);
        g.drawRect((int)hitBox.x, (int)hitBox.y,(int)hitBox.width,(int)hitBox.height);
    }
    protected void initHitbox(float x, float y, float width, float height) {
        hitBox = new Rectangle2D.Float(x, y, width,height);

    }
//    protected void updateHitbox(){
//        hitBox.x = (int)x;
//        hitBox.y = (int)y;
//    }
    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }

}
