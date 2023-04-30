package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/Sprites/01-King Human/";
    public static final String LEVEL_ATLAS = "/Sprites/14-TileSets/";
    /**
     * Devuelve una lista con los png de cada animacion
     * @return
     */
    public static BufferedImage[] GetSpriteAtlas(String fileName){
        BufferedImage img=null;
        BufferedImage playerAtlas[] = new BufferedImage[10];
        int aux=-1;

        switch (fileName){
            case PLAYER_ATLAS: aux = 10;
                break;
            case LEVEL_ATLAS: aux = 2;
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
}
