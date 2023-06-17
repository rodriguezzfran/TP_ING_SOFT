package behaviors.damage;


public class Damage2 implements DamageBehavior{

    final static int  dmg = 10;
    @Override
    public int getDamage() {
        return dmg;
    }
}
