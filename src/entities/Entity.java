package entities;

public abstract class Entity {
    protected float x,y;
    protected int speed;
    public Entity(float x, float y,int speed){
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

}
