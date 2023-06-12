package behaviors.damage;

public class Damage1 implements DamageBehavior{

    final static int dmg = 1;

    @Override
    public int getDamage() {
        return dmg;
    }
}
