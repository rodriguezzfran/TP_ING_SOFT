package behaviors.damage;

public class DamageP2 implements DamageBehavior{

    final static int dmg = 20;

    @Override
    public int getDamage() {
        return dmg;
    }
}
