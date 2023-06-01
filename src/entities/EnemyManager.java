package entities;

import gameStates.Playing;

import static utilz.Constants.Directions.*;
import static utilz.Constants.EnemyConstants.*;
import utilz.LoadSave;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.nio.Buffer;
import java.util.ArrayList;



public class EnemyManager {

    private Playing playing;
    private BufferedImage[][] kingPigArr;
    private ArrayList<KingPig> kingPigs = new ArrayList<>();
    public EnemyManager(Playing playing){
        this.playing = playing;
        loadEnemyImgs();
        addEnemies();
    }

    private void addEnemies() {
        kingPigs = LoadSave.GetKingPigs();
        System.out.println("Amount of King Pigs: " + kingPigs.size());
    }

    public void update(int[][] lvlData, Player player){
        for(KingPig kp : kingPigs){
            kp.update(lvlData,player);
        }
    }
    public void draw(Graphics g){
        drawKingPigs(g);
    }

    private void drawKingPigs(Graphics g) {
        for(KingPig kp : kingPigs){
            g.drawImage(kingPigArr[kp.getEnemyState()][kp.getAniIndex()], (int)kp.getHitBox().x-KING_PIG_DRAWOFFSET_X, (int)kp.getHitBox().y-KING_PIG_DRAWOFFSET_Y, KING_PIG_WIDTH, KING_PIG_HEIGHT,null);
            kp.drawHitbox(g); //para debuggear hitbox
        }
    }



    private void loadEnemyImgs() {
        kingPigArr = new BufferedImage[8][12];
        BufferedImage temp[] = LoadSave.GetSpriteAtlas(LoadSave.KING_PIG_SPRITE);
        for(int i = 0;i<temp.length;i++){
            for(int j = 0; j < (temp[i].getWidth()/KING_PIG_WIDTH_DEFAULT);j++){
                kingPigArr[i][j] = temp[i].getSubimage(j*(KING_PIG_WIDTH_DEFAULT),0,KING_PIG_WIDTH_DEFAULT,KING_PIG_HEIGHT_DEFAULT);
            }
        }
    }
}
