package levels;

import entities.Enemy;
import entities.Player;
import gamestates.Gamestate;
import main.Game;
import utilz.LoadSave;
import static utilz.HelpMethods.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import java.awt.Graphics;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import gamestates.Playing;
import levels.Level;
import utilz.LoadSave;
import static utilz.Constants.EnemyConstants.*;

public class LevelManager {
    private Game game;
    private BufferedImage[] levelSprite;
    private ArrayList<Level> levels;
    private static int lvlIndex = 0;
    private int lvlIndexEn = 0;
    public LevelManager(Game game){
        this.game = game;
        importOutsideSprites();
        levels = new ArrayList<>();
        buildAllLevels();
    }

    public void buildAllLevels() {
        lvlIndexEn = lvlIndex;
        BufferedImage[] allLevels = LoadSave.GetAllLevels();
        for (BufferedImage img : allLevels){
            levels.add(new Level(img, lvlIndexEn));
            lvlIndexEn++;
         }
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

    public void draw(Graphics g) {
        for (int j = 0; j < Game.TILES_IN_HEIGHT; j++)
            for (int i = 0; i < levels.get(lvlIndex).getLvlData()[0].length; i++) {
                int index = levels.get(lvlIndex).getSpriteIndex(i, j);
                g.drawImage(levelSprite[index], Game.TILES_SIZE * i , Game.TILES_SIZE * j, Game.TILES_SIZE, Game.TILES_SIZE, null);
            }
    }

    public void loadNextLevel() {
        lvlIndex++;
        if (lvlIndex >= levels.size()) {
            lvlIndex = 0;
            System.out.println("No more levels! Game Completed!");
            Gamestate.state = Gamestate.MENU;
        }

        Level newLevel = levels.get(lvlIndex);
        game.getPlaying().getEnemyManager().loadEnemies(newLevel);
        game.getPlaying().getPlayer().loadLvlData(newLevel.getLvlData());
        game.getPlaying().getHealthObservable().setHealth(100);
    }

    public void update(){
    }


    public Level getCurrentLevel() {
            return levels.get(lvlIndex);
    }
    public int getAmountOfLevels(){
        return levels.size();
    }

    public void setLvlIndex(int index){
        this.lvlIndex =  index;
    }

    public int getLvlIndex(){
        return lvlIndex;
    }

}
