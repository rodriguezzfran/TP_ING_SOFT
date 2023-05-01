package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    public LevelManager(Game game){
        this.game = game;
        //levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();

    }

    public void importOutsideSprites() {
        BufferedImage imgTerrain = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS)[1]; //solo toma el png de terrenos
        levelSprite = new BufferedImage[247];
        for(int i=0;i<13;i++){
            for(int j=0;j<19;j++){
                int index = i*19 + j;
                levelSprite[index] = imgTerrain.getSubimage(j*32,i*32,32,32);
            }
        }

    }

    public void draw(Graphics g){
        g.drawImage(levelSprite[20],0,0,null);
    }

    public void update(){

    }

}
