package behaviors.damage;

public class Damage1 implements DamageBehavior{

    final static int dmg = 5;

    @Override
    public int getDamage() {
        return dmg;
    }
}
