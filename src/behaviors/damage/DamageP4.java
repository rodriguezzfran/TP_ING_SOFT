package behaviors.damage;

public class DamageP4 implements DamageBehavior{

    final static int dmg = 40;

    @Override
    public int getDamage() {
        return dmg;
    }
}
