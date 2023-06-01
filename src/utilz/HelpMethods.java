package utilz;

import main.Game;

import java.awt.*;
import java.awt.geom.Rectangle2D;

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

    public static float getEntityXPosNextToWall(Rectangle2D.Float hitbox, float xSpeed){
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

   public static float getEntityYPosUnderRoofOrAboveFloor(Rectangle2D.Float hitbox, float airSpeed){
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

    private static boolean IsSolid(float x, float y, int[][] lvlData){

        if(x<0 || x>= Game.GAME_WIDTH){
            return true;
        }
        if(y<0 || y>= Game.GAME_HEIGHT){
            return true;
        }

        float xIndex = x/Game.TILES_SIZE;
        float yIndex = y/Game.TILES_SIZE;
        int value = lvlData[(int)yIndex][(int)xIndex];

        if(value >= (19*13) || value < 0 || value != 0) {
            return true;
        }

        return false;
    }

    public static boolean isEntityOnFloor(Rectangle2D.Float hitBox,int[][] lvlData){
        // Check the pixel below bottomleft and bottomright
        if(!IsSolid(hitBox.x, hitBox.y+ hitBox.height+1,lvlData)){
            if(!IsSolid(hitBox.x+ hitBox.width, hitBox.y+ hitBox.height+1,lvlData))
                return false;
        }
        return true;
    }

}
