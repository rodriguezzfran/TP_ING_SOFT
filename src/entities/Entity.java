package entities;

import behaviors.damage.DamageBehavior;
import behaviors.health.HealthBehavior;
import observables.HealthObservable;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public abstract class Entity {
    protected int width, height;
    protected float x,y;
    protected int speed;
    protected Rectangle2D.Float hitBox;


    protected DamageBehavior damageBehavior;
    protected HealthBehavior healthBehavior;

    protected HealthObservable healthObservable;

    public Entity(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
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

    public Rectangle2D.Float getHitBox(){
        return hitBox;
    }


    public void setEntityDamage(DamageBehavior damageBehavior){
        this.damageBehavior = damageBehavior;
    }
    public void setEntityMaxHealth(HealthBehavior healthBehavior){
        this.healthBehavior = healthBehavior;
    }
    public void setHealthObservable(HealthObservable healthObservable) {
        this.healthObservable = healthObservable;
    }

    public HealthBehavior getHealthBehavior(){return this.healthBehavior;}
    public DamageBehavior getDamageBehavior(){return this.damageBehavior;}
    public HealthObservable getHealthObservable(){return this.healthObservable;}
}
