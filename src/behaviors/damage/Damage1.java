package behaviors.damage;

public class Damage1 implements DamageBehavior{

    final static int dmg = 15;

    @Override
    public int getDamage() {
        return dmg;
    }
}
