package entities;

import behaviors.damage.*;
import behaviors.health.*;
import behaviors.rangeenemies.ShortDistance;
import gamestates.Playing;
import levels.LevelManager;
import main.Game;
import main.Main;
import utilz.LoadSave;
import static utilz.HelpMethods.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.EnemyConstants.*;


public class KingPig  extends  Enemy{

    public KingPig(float x, float y,int lvlIndex) {
        super(x, y, KING_PIG_WIDTH,KING_PIG_HEIGHT, KING_PIG,KING_PIG_DRAWOFFSET_X,KING_PIG_DRAWOFFSET_Y,new ShortDistance());
        initHitbox(x,y,(int)(18* Game.SCALE ),(int)(19*Game.SCALE));
        initAttackBox();
        spritePath = LoadSave.KING_PIG_SPRITE;
        enemyIndex=0;
        setEnemyMaxHealth(setHealthBehavior(lvlIndex));
        setEnemyDamage(setDamageBehavior(lvlIndex));
        currentHealth = healthBehavior.getHealth();
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y-(4*Game.SCALE),(hitBox.width + getRangeBehavior().getAttackDistance()),(int)(26*Game.SCALE));
        //attackBoxOffsetX = (int)(Game.SCALE * 30);
    }

    public void drawAttackBox(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }


}
