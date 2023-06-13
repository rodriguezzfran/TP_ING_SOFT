package behaviors.damage;


public class Damage4 implements DamageBehavior{

    final static int dmg = 50;
    @Override
    public int getDamage() {
        return dmg;
    }
}
