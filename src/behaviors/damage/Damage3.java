package behaviors.damage;


public class Damage3 implements DamageBehavior{

    final static int dmg = 40;
    @Override
    public int getDamage() {
        return dmg;
    }
}
