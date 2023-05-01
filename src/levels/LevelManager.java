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
        levelSprite = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_ATLAS);
        importOutsideSprites();
        levelOne = new Level(LoadSave.GetLevelData());

    }

    //debe haber algo mal acá con el index que no permite que se dibuje bien
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
                int index = levelOne.getSpriteIndex(i,j);
                //Seguro haya un error acá porque si en vez de index le pone a mano un valor si lo dibuja
                //Probablemente me haya equivocado con las dimensiones del archivo.
                g.drawImage(levelSprite[59], Game.TILES_SIZE*i ,Game.TILES_SIZE*j,Game.TILES_SIZE,Game.TILES_SIZE,null);

            }
        }
    }

    public void update(){

    }

}
