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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;

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
    public static final String URM_BUTTONS = "Sprites/18-UrmButtons";
    public static final String COMPLETED_IMG = "Sprites/";
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
            case URM_BUTTONS: aux = 1;
                break;
            case COMPLETED_IMG: aux = 1;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + fileName);
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


    public static BufferedImage[] GetAllLevels() {
        URL url = LoadSave.class.getResource("/Sprites/15-LevelData/");
        File file = null;

        try {
            file = new File(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        File[] files = file.listFiles();
        File[] filesSorted = new File[files.length];

        for (int i = 0; i < filesSorted.length; i++)
            for (int j = 0; j < files.length; j++) {
                if (files[j].getName().equals((i + 1) + ".png"))
                    filesSorted[i] = files[j];

            }

        BufferedImage[] imgs = new BufferedImage[filesSorted.length];

        for (int i = 0; i < imgs.length; i++)
            try {
                imgs[i] = ImageIO.read(filesSorted[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }

        return imgs;
    }

}
