package utilz;

import main.Game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/Sprites/01-King Human/";
    public static final String LEVEL_ATLAS = "/Sprites/14-TileSets/";
    public static final String LEVEL_ONE_DATA = "/Sprites/15-LevelData/";
    public static final String MENU_BUTTONS = "/Sprites/16-Menu/";
    /**
    /**
     * Devuelve una lista con los png de cada animacion
     * @return
     */
    public static BufferedImage[] GetSpriteAtlas(String fileName){
        BufferedImage img=null;
        BufferedImage playerAtlas[] = new BufferedImage[10];
        int aux=-1;

        switch (fileName){ //para saber la cantidad de imágenes a buscar
            case PLAYER_ATLAS: aux = 10;
                break;
            case LEVEL_ATLAS: aux = 2;
                break;
            case LEVEL_ONE_DATA: aux = 1;
                break;
            case MENU_BUTTONS: aux = 2;
                break;
        }

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
