package entities;

import static utilz.Constants.EnemyConstants.*;
import static utilz.HelpMethods.*;

import behaviors.rangeenemies.LongDistance;
import main.Game;
import utilz.LoadSave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.geom.Rectangle2D;

public class Crabby extends Enemy {

        // AttackBox
        private int attackBoxOffsetX;


        public Crabby(float x, float y,int lvlIndex) {
            super(x, y, CRABBY_WIDTH, CRABBY_HEIGHT, CRABBY,CRABBY_DRAWOFFSET_X,CRABBY_DRAWOFFSET_Y,new LongDistance());
            initHitbox(x, y, (int) (22 * Game.SCALE), (int) (19 * Game.SCALE));
            initAttackBox();
            spritePath = LoadSave.CRABBY_SPRITE;
            enemyIndex=1;
            setEntityMaxHealth(GetHealthBehaviorByLvl(lvlIndex));
            setEntityDamage(GetDamageBehaviorByLvl(lvlIndex));
            currentHealth = healthBehavior.getHealth();
        }

        private void initAttackBox() {
            attackBox = new Rectangle2D.Float(x, y, (int) (82 * Game.SCALE), (int) (19 * Game.SCALE));
            attackBoxOffsetX = (int) (Game.SCALE * 30);
        }

        public void drawAttackBox(Graphics g, int xLvlOffset) {
            g.setColor(Color.red);
            g.drawRect((int) (attackBox.x - xLvlOffset), (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
        }

    }

