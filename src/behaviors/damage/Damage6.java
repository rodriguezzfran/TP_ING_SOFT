package behaviors.damage;


public class Damage6 implements DamageBehavior{

    final static int dmg = 30;
    @Override
    public int getDamage() {
        return dmg;
    }
}
