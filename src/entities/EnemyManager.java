package entities;

import gamestates.Playing;

import static utilz.Constants.EnemyConstants.*;
import utilz.LoadSave;

import javax.swing.plaf.ButtonUI;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;



public class EnemyManager {

    private Playing playing;
    private BufferedImage[][][] enemyArr;
  //  private BufferedImage[][] crabbyArr;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {
        enemies = LoadSave.GetEnemies();
        System.out.println("Amount of King Pigs: " + enemies.size());
    }

    public void update(int[][] lvlData, Player player){
        for(Enemy e : enemies){
            if(e.isActive()){
                e.update(lvlData,player);
            }
        }
    }
    public void draw(Graphics g){
        drawEnemies(g);
    }

    private void drawEnemies(Graphics g) {

        for(Enemy e : enemies){
            if(e.isActive()) {
                g.drawImage(enemyArr[e.getEnemyIndex()][e.getEnemyState()][e.getAniIndex()],
                        (int) e.getHitBox().x - e.getXDrawOffset() + e.flipX(),
                        (int) e.getHitBox().y - e.getYDrawOffset(),
                        e.getEnemyWidth() * e.flipW(), e.getEnemyHeight(), null);
                //kp.drawHitbox(g); //para debuggear hitbox
                //kp.drawAttackBox(g);
            }
        }
    }


    public void checkEnemyHit(Rectangle2D.Float attackBox){
        for(Enemy e : enemies){
            if(e.isActive()){
                if (attackBox.intersects(e.getHitBox())) {
                    e.hurt(10);
                    return;
                }
            }
        }
    }


    private void loadEnemyImgs() {
        enemyArr = new BufferedImage[2][8][12];
        BufferedImage temp[] = LoadSave.GetSpriteAtlas(LoadSave.KING_PIG_SPRITE);
        for(int k=0;k<2;k++) {
            for (int i = 0; i < temp.length; i++) {
                for (int j = 0; j < (temp[i].getWidth() / KING_PIG_WIDTH_DEFAULT); j++) {
                    enemyArr[0][i][j] = temp[i].getSubimage(j * (KING_PIG_WIDTH_DEFAULT), 0, KING_PIG_WIDTH_DEFAULT, KING_PIG_HEIGHT_DEFAULT);
                }
            }
        }
        temp = LoadSave.GetSpriteAtlas(LoadSave.CRABBY_SPRITE);
        for (int i = 0; i < temp.length; i++) {
            for (int j = 0; j < (temp[i].getWidth() / CRABBY_WIDTH_DEFAULT); j++) {
                enemyArr[1][i][j] = temp[i].getSubimage(j * (CRABBY_WIDTH_DEFAULT), 0, CRABBY_WIDTH_DEFAULT, CRABBY_HEIGHT_DEFAULT);
            }
        }
    }

    public void resetAllEnemies() {
        for(Enemy e : enemies){
            e.resetEnemy();
        }
    }
}
