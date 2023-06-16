package utilz;

import behaviors.damage.*;
import behaviors.health.*;
import entities.Crabby;
import entities.Enemy;
import entities.KingPig;
import main.Game;

import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.Color;
import java.awt.Point;

import static utilz.Constants.EnemyConstants.CRABBY;
import static utilz.Constants.EnemyConstants.KING_PIG;

public class HelpMethods {

    public static boolean CanMoveHere(float x, float y, float width, float height, int[][] lvlData){
        if(!IsSolid(x,y,lvlData)) {                                   //checks top left
            if (!IsSolid(x + width, y + height, lvlData)) {     //checks bottom right
                if (!IsSolid(x + width, y, lvlData)) {             //checks top right
                    if (!IsSolid(x, y + height, lvlData)) {        //checks bottom left
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static float GetEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
        int currentTile = (int)(hitbox.x/Game.TILES_SIZE);
        if(xSpeed > 0){
            //A la derecha
            int tileXPosr = currentTile * Game.TILES_SIZE;
            int xOffset = (int)(Game.TILES_SIZE - hitbox.width);
            return tileXPosr + xOffset - 1;
        }else{
            //a la izquierda
            return currentTile*Game.TILES_SIZE;
        }
    }

   public static float GetEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed){
       int currentTile = (int)(hitbox.y/Game.TILES_SIZE);
       if(airSpeed > 0){
           //cayendo 384
            int tileYPos = currentTile * Game.TILES_SIZE;
            int yOffset = (int)(Game.TILES_SIZE - hitbox.height);
            return tileYPos + yOffset-1;
       }else{
           //saltando
           return currentTile*Game.TILES_SIZE;
       }


   }

    public static int[][] GetLevelData(BufferedImage img) {
        int[][] lvData = new int[img.getHeight()][img.getWidth()];
        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getRed();
                if (value >= 247){
                    value = 0;
                }
                lvData[j][i] = value;
            }
        }
        return lvData;
    }


    public static ArrayList<Enemy> GetEnemies(BufferedImage img, int lvlIndex){
        ArrayList<Enemy> list = new ArrayList<>();

        for (int j = 0; j < img.getHeight(); j++) {
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == KING_PIG) {
                    list.add(new KingPig(i * Game.TILES_SIZE, j * Game.TILES_SIZE,lvlIndex));
                }
                if (value == CRABBY){
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE,lvlIndex));
                }
            }
        }
        return list;
    }
    private static boolean IsSolid(float x, float y, int[][] lvlData){
        int maxWidth = lvlData[0].length * Game.TILES_SIZE;
        if(x<0 || x>= maxWidth){
            return true;
        }
        if(y<0 || y>= Game.GAME_HEIGHT){
            return true;
        }
        float xIndex = x/Game.TILES_SIZE;
        float yIndex = y/Game.TILES_SIZE;

        return IsTileSolid((int)xIndex,(int)yIndex,lvlData);
    }

    public static boolean IsTileSolid(int xTile, int yTile, int[][] lvlData){
        int value = lvlData[yTile][xTile];

        if(value >= (19*13) || value < 0 || value != 0) {
            return true;
        }
        return false;
    }

    public static boolean IsEntityOnFloor(Rectangle2D.Float hitBox, int[][] lvlData){
        // Check the pixel below bottomleft and bottomright
        if(!IsSolid(hitBox.x, hitBox.y+ hitBox.height+1,lvlData)){
            if(!IsSolid(hitBox.x+ hitBox.width, hitBox.y+ hitBox.height+1,lvlData))
                return false;
        }
        return true;
    }

    public static boolean IsFloor(Rectangle2D.Float hitbox,float xSpeed, int[][] lvlData){
        if(xSpeed>0){
            return IsSolid(hitbox.x + xSpeed + hitbox.width, hitbox.y + hitbox.height +1,lvlData);
        }
        return IsSolid(hitbox.x + xSpeed, hitbox.y + hitbox.height + 1, lvlData);
    }

    public static boolean IsAllTilesWalkable(int xStart, int xEnd, int y, int[][] lvlData){
        for(int i = 0; i<xEnd-xStart;i++){
            if(IsTileSolid(xStart + i,y,lvlData)){
                return false;
            }
            if(!IsTileSolid(xStart + i,y+1,lvlData)){
                return false;
            }

        }
        return true;
    }

    public static boolean IsSightClear(int[][] lvlData,Rectangle2D.Float firstHitbox,
                                       Rectangle2D.Float secondHitbox, int yTile){
        int firstHBXTile = (int)(firstHitbox.x / Game.TILES_SIZE);
        int secondHBXTile = (int)(secondHitbox.x / Game.TILES_SIZE);

        if(firstHBXTile > secondHBXTile){
            return IsAllTilesWalkable(secondHBXTile,firstHBXTile,yTile,lvlData);
        }
        else{
            return IsAllTilesWalkable(firstHBXTile,secondHBXTile,yTile,lvlData);
        }
    }

    public static Point GetPlayerSpawn(BufferedImage img) {
        for (int j = 0; j < img.getHeight(); j++)
            for (int i = 0; i < img.getWidth(); i++) {
                Color color = new Color(img.getRGB(i, j));
                int value = color.getGreen();
                if (value == 100)
                    return new Point(i * Game.TILES_SIZE, j * Game.TILES_SIZE);
            }
        return new Point(1 * Game.TILES_SIZE, 1 * Game.TILES_SIZE);
    }

    public static DamageBehavior setDamageBehavior(int lvlIndex){
        switch (lvlIndex){
            case 0:
                return new Damage1();
            case 1:
                return new Damage2();
            case 2:
                return new Damage3();
            case 3:
                return new Damage4();
            default:
                throw new IllegalStateException("Unexpected value: " + lvlIndex);
        }
    }
    public static DamageBehavior setPlayerDamageBehavior(int lvlIndex){
        switch (lvlIndex){
            case 0:
                return new DamageP1();
            case 2:
                return new DamageP2();
            default:
                throw new IllegalStateException("Unexpected value: " + lvlIndex);
        }
    }
    public static HealthBehavior setPlayerHealthBehavior(int lvlIndex){
        switch (lvlIndex){
            case 0:
                return new HealthP1();
            case 2:
                return new HealthP2();
            default:
                throw new IllegalStateException("Unexpected value: " + lvlIndex);
        }
    }
    public static HealthBehavior setHealthBehavior(int lvlIndex){
        switch (lvlIndex){
            case 0:
                return new Health1();
            case 1:
                return new Health2();
            case 2:
                return new Health3();
            case 3:
                return new Health4();
            default:
                throw new IllegalStateException("Unexpected value: " + lvlIndex);
        }
    }


}
