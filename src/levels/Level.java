package levels;
import entities.Enemy;
import static utilz.HelpMethods.*;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Level {

    private Point playerSpawn;

    private int[][] lvData;

    private ArrayList<Enemy> enemies;
    private BufferedImage img;

    public Level(BufferedImage img,int LvlIndex) {
        this.img = img;
        createLevelData();
        createEnemies(LvlIndex);
        calcPlayerSpawn();
    }

    public int getSpriteIndex(int x, int y){
        return lvData[y][x];
    }

    public int[][] getLvlData() {
        return lvData;
    }


    private void calcPlayerSpawn() {
        playerSpawn = GetPlayerSpawn(img);
    }

    private void createEnemies(int lvlIndex) {
        enemies = GetEnemies(img,lvlIndex);
    }

    private void createLevelData() {
        lvData = GetLevelData(img);
    }

    public Point getPlayerSpawn() {
        return playerSpawn;
    }

    public ArrayList<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<Enemy> enemies) {
        this.enemies = enemies;
    }
}
