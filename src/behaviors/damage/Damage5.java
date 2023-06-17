package behaviors.damage;


public class Damage5 implements DamageBehavior{

    final static int dmg = 25;
    @Override
    public int getDamage() {
        return dmg;
    }
}
