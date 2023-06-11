package entities;

import behaviors.ShortDistance;
import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.Rectangle2D;

import static utilz.Constants.Directions.LEFT;
import static utilz.Constants.Directions.RIGHT;
import static utilz.Constants.EnemyConstants.*;

public class KingPig  extends  Enemy{

    //private int attackBoxOffsetX;

    public KingPig(float x, float y) {
        super(x, y, KING_PIG_WIDTH,KING_PIG_HEIGHT, KING_PIG,KING_PIG_DRAWOFFSET_X,KING_PIG_DRAWOFFSET_Y);
        initHitbox(x,y,(int)(18* Game.SCALE ),(int)(19*Game.SCALE));
        initAttackBox();
        rangeEnemiesBehavior = new ShortDistance(this);
        spritePath = LoadSave.KING_PIG_SPRITE;
        enemyIndex=0;
    }

    private void initAttackBox() {
        attackBox = new Rectangle2D.Float(x,y-(4*Game.SCALE),(hitBox.width + attackDistance),(int)(26*Game.SCALE));
        //attackBoxOffsetX = (int)(Game.SCALE * 30);
    }

    public void drawAttackBox(Graphics g){
        g.setColor(Color.red);
        g.drawRect((int) attackBox.x, (int) attackBox.y, (int) attackBox.width, (int) attackBox.height);
    }


}
