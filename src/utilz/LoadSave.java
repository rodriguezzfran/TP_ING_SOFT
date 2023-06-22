package utilz;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class LoadSave {

    public static final String PLAYER_ATLAS = "/Sprites/01-King Human/";
    public static final String LEVEL_ATLAS = "/Sprites/14-TileSets/";
    public static final String LEVEL_ONE_DATA = "/Sprites/15-LevelData/";
    public static final String MENU_BUTTONS = "/Sprites/16-Menu/";
    public static final String KING_PIG_SPRITE = "/Sprites/02-King Pig/";
    public static final String CRABBY_SPRITE = "/Sprites/17-Crabby/";
    public static final String LIVE_BAR_GRAPH = "/Sprites/12-Live and Coins/";
    public static final String URM_BUTTONS = "/Sprites/18-UrmButtons/";
    public static final String COMPLETED_IMG = "/Sprites/19-Completed/";
    public static final String SOUND_BUTTONS = "/Sprites/20-SOUND_BUTTONS/";
    public static final String VOLUME_BUTTONS = "/Sprites/21-VOLUME_BUTTONS/";
    public static final String BACKGROUND = "/Sprites/22-BACKGROUND/";
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
            case MENU_BUTTONS: aux = 6;
                break;
            case KING_PIG_SPRITE: aux = 8;
                break;
            case LIVE_BAR_GRAPH: aux = 10;
                break;
            case CRABBY_SPRITE: aux = 5;
                break;
            case COMPLETED_IMG: aux = 1;
                break;
            case SOUND_BUTTONS: aux = 1;
                break;
            case VOLUME_BUTTONS: aux = 1;
                break;
            case BACKGROUND: aux = 1;
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
                playerAtlas[i]=img;
            }
        }
        return playerAtlas;
    }


    public static BufferedImage[] GetAllLevels() {
        BufferedImage[] imgs = null;
        try {
            String path = LoadSave.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            JarFile jarFile = new JarFile(path);
            Enumeration<JarEntry> entries = jarFile.entries();
            ArrayList<BufferedImage> imagesList = new ArrayList<>();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                if (entry.getName().startsWith("Sprites/15-LevelData/") && entry.getName().endsWith(".png")) {
                    InputStream inputStream = jarFile.getInputStream(entry);
                    BufferedImage image = ImageIO.read(inputStream);
                    imagesList.add(image);
                }
            }
            imgs = imagesList.toArray(new BufferedImage[0]);
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return imgs;
    }

}
