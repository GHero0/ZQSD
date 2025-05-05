package utilz;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {

    public static final String PLAYER_ATLAS = "Cat_character_sheet.png";
    public static final String ICON_ATLAS = "Keyboard_Extras.png";
    public static final String LEVEL_SPRITE = "Keyboard_Letters.png";

    
    public static BufferedImage GetSpriteAtlas(String filename){

        BufferedImage img = null;
        InputStream is = LoadSave.class.getClassLoader().getResourceAsStream(filename);
        if (is == null) {
            System.err.println("Asset not found!");
        } else {
        }
        try {

            img = ImageIO.read(is);
            
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
        return img;
    }
}
