package levels;

import main.Game;
import utilz.LoadSave;

import java.awt.*;
import java.awt.image.BufferedImage;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private Level levelOne;
    public LevelManager(Game game){
        this.game = game;
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());

    }


    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS)[1];
        levelSprite = new BufferedImage[247];
        for(int j = 0; j < 13; j++){
            for( int i = 0; i < 19; i++){
                int index = j*19 + i;
                levelSprite[index] = img.getSubimage(i * 32, j*32, 32, 32);
            }
        }
    }

    public void draw(Graphics g){
        for(int j=0; j < Game.TILES_IN_HEIGHT; j++){
            for(int i = 0; i < Game.TILES_IN_WIDTH; i++){
                int index = levelOne.getSpriteIndex(j,i);
                g.drawImage(levelSprite[index], Game.TILES_SIZE*i ,Game.TILES_SIZE*j,Game.TILES_SIZE,Game.TILES_SIZE,null);
            }
        }
    }

    public void update(){

    }

    public Level getCurrentLevel() {
        return levelOne;
    }


}
