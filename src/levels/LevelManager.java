package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import main.Game;
import static main.Game.SCALE;
import utilz.LoadSave;

public class LevelManager {
    
    private Game game;
    private BufferedImage[] levelSprite;
    

    public LevelManager(Game game){
        this.game = game;
        importOutsideSprites();
    }

    private void importOutsideSprites() {
        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.LEVEL_SPRITE);
        levelSprite = new BufferedImage[112];
        for (int i = 0; i < 14 ; i++){
            for (int j = 0; j < 8; j++){
                int index = i * 8 + j;
                levelSprite[index] = img.getSubimage(j * 16, i * 16, 16, 16);
            }
        }
    }

    public void draw(Graphics g){
        int width = (int)(16 * SCALE);
        int height = (int)(16 * SCALE);
        g.drawImage(levelSprite[2], 200, 200, width , height, null);
    }

    public void update(){
        
    }
}
