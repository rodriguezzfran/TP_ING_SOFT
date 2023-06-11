package utilz;

import entities.Crabby;
import entities.Enemy;
import entities.KingPig;
import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import static utilz.Constants.EnemyConstants.CRABBY;
import static utilz.Constants.EnemyConstants.KING_PIG;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/Sprites/01-King Human/";
    public static final String LEVEL_ATLAS = "/Sprites/14-TileSets/";
    public static final String LEVEL_ONE_DATA = "/Sprites/15-LevelData/";
    public static final String MENU_BUTTONS = "/Sprites/16-Menu/";
    public static final String KING_PIG_SPRITE = "/Sprites/02-King Pig/";
    public static final String CRABBY_SPRITE = "/Sprites/17-Crabby/";
    public static final String LIVE_BAR_GRAPH = "/Sprites/12-Live and Coins/";
    /**
     * Devuelve una lista con los png de cada animacion
     * @return
     */
    public static BufferedImage[] GetSpriteAtlas(String fileName){
        BufferedImage img=null;
        int aux=-1;

        switch (fileName){ //para saber la cantidad de imágenes a buscar
            case PLAYER_ATLAS: aux = 10;
                break;
            case LEVEL_ATLAS: aux = 2;
                break;
            case LEVEL_ONE_DATA: aux = 1;
                break;
            case MENU_BUTTONS: aux = 3;
                break;
            case KING_PIG_SPRITE: aux = 8;
                break;
            case LIVE_BAR_GRAPH: aux = 10;
                break;
            case CRABBY_SPRITE: aux = 5;
                break;
        }
        BufferedImage playerAtlas[] = new BufferedImage[aux];

        for(int i=0;i<aux;i++) {
            InputStream is = LoadSave.class.getResourceAsStream(fileName+i+".png");
            try {
                img = ImageIO.read(is);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    is.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                playerAtlas[i]=img;
            }
        }
        return playerAtlas;
    }

    public static ArrayList<Enemy> GetEnemies(){
        BufferedImage[] img = GetSpriteAtlas(LEVEL_ONE_DATA);
        ArrayList<Enemy> list = new ArrayList<>();

        for (int j = 0; j < img[0].getHeight(); j++) {
            for (int i = 0; i < img[0].getWidth(); i++) {
                Color color = new Color(img[0].getRGB(i, j));
                int value = color.getGreen();
                if (value == KING_PIG) {
                    list.add(new KingPig(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
                }
                if (value == CRABBY){
                    list.add(new Crabby(i * Game.TILES_SIZE, j * Game.TILES_SIZE));
                }
            }
        }
        return list;
    }

    public static int[][] GetLevelData() {
        int[][] lvData = new int[Game.TILES_IN_HEIGHT][Game.TILES_IN_WIDTH];
        BufferedImage[] img = GetSpriteAtlas(LEVEL_ONE_DATA);

        for (int j = 0; j < img[0].getHeight(); j++) {
            for (int i = 0; i < img[0].getWidth(); i++) {
                Color color = new Color(img[0].getRGB(i, j));
                int value = color.getRed();
                if (value >= 247){
                    value = 0;
                }
                lvData[j][i] = value;
            }
        }
        return lvData;
    }
}
