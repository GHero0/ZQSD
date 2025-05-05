package entities;

import static main.Game.SCALE;
import static utilz.Constants.PlayerConstants.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class Player extends Entity {

  private BufferedImage[][] animations;
  private int aniTick, aniIndex, aniSpeed = 30;
  private int playerAction = IDLE;
  private boolean left, right, up, down;
  private boolean moving = false;
  private boolean oriented_left, oriented_right;

  private float playerSpeed = 0.4f * SCALE;



  public Player(float x, float y) {
    super(x, y);
    loadAnimation();
  }

  public void update() {
    updatePos(); // First apply input logic (including jump)
    updateAnimationTick(); // Then update animation
    setAnimation(); // Finally, set proper animation state
  }

  private void updatePos() {
    moving = false;

    float xSpeed = 0;
    float ySpeed = 0;

    if (left && !right) {
      xSpeed = -playerSpeed;
      oriented_right = false;
      oriented_left = true;
      moving = true;
    } else if (right && !left) {
      xSpeed = playerSpeed;
      oriented_right = true;
      oriented_left = false;
      moving = true;
    }
    if (up && !down) {
      ySpeed = -playerSpeed;
      moving = true;
    } else if (down && !up) {
      ySpeed = playerSpeed;
      moving = true;
    }

    // Normalize diagonal movement
    if (moving && xSpeed != 0 && ySpeed != 0) {
      float normalizationFactor = (float) (1 / Math.sqrt(2));
      xSpeed *= normalizationFactor;
      ySpeed *= normalizationFactor;
    }

    x += xSpeed;
    y += ySpeed;
  }


  private void updateAnimationTick() {
    aniTick++;
    if (aniTick >= aniSpeed) {
      aniTick = 0;
      aniIndex++;
      if (aniIndex >= getSpriteAmount(playerAction)) {
        aniIndex = 0;
      }
    }
  }

  private void setAnimation() {
    int previousAction = playerAction;

    if (moving) {
      playerAction = RUNNING;
    } else {
      playerAction = IDLE;
    }

    if (playerAction != previousAction) {
      aniIndex = 0;
      aniTick = 0;
    }
  }

  public void render(Graphics g) {
    int width = (int) (32 * SCALE);
    int height = (int) (32 * SCALE);

    // Render pixelated shadow
    int shadowWidth = (int) (17 * SCALE);
    if (moving) {
      shadowWidth = (int) (20 * SCALE);
    }
    int shadowHeight = (int) (3 * SCALE);
    int shadowX = (int) x + (width - shadowWidth) / 2;
    int shadowY = (int) y + height - (int) (6 * SCALE);

    // Create a pixelated shadow
    renderPixelatedShadow(g, shadowX, shadowY, shadowWidth, shadowHeight);

    // Render player
    if (oriented_right) {
      g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
    } else if (oriented_left) {
      g.drawImage(animations[playerAction][aniIndex], (int) x + width, (int) y, -width, height, null);
    } else {
      g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, width, height, null);
    }
  }

  /**
   * Renders a pixelated shadow using small rectangles to match pixel art style
   */
  private void renderPixelatedShadow(Graphics g, int x, int y, int width, int height) {
    // Define pixel size for the shadow
    int pixelSize = (int) (1 * SCALE); // Adjust this value for more/less pixelation

    // Semi-transparent black for shadow
    g.setColor(new java.awt.Color(0, 0, 0, 80));

    // Calculate the elliptical shape using a simple algorithm
    double a = width / 2.0; // horizontal radius
    double b = height / 2.0; // vertical radius
    int centerX = x + width / 2;
    int centerY = y + height / 2;

    // Draw pixelated oval by filling in squares
    for (int py = 0; py < height; py += pixelSize) {
      for (int px = 0; px < width; px += pixelSize) {
        // Calculate if this pixel should be part of the oval
        // Get actual position relative to center
        double actualX = x + px + pixelSize / 2.0 - centerX;
        double actualY = y + py + pixelSize / 2.0 - centerY;

        // Normalize to ellipse equation
        double normalizedX = actualX / a;
        double normalizedY = actualY / b;

        // If point is inside the ellipse (x²/a² + y²/b² ≤ 1)
        if (normalizedX * normalizedX + normalizedY * normalizedY <= 1.0) {
          g.fillRect(x + px, y + py, pixelSize, pixelSize);
        }
      }
    }
  }

  private void loadAnimation() {
    BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
    int spriteWidth = 32;
    int spriteHeight = 32;
    animations = new BufferedImage[4][10];

    for (int j = 0; j < animations.length; j++) {
      for (int i = 0; i < animations[j].length; i++) {
        animations[j][i] = img.getSubimage(i * spriteWidth, j * spriteHeight, spriteWidth, spriteHeight);
      }
    }
  }


 public void setUp(boolean up) {
    this.up = up;
  }

  public void setDown(boolean down) {
    this.down = down;
  }

  public void setLeft(boolean left) {
    this.left = left;
  }

  public void setRight(boolean right) {
    this.right = right;
  }

  public boolean getUp() {
    return up;
  }

  public boolean getDown() {
    return down;
  }

  public boolean getLeft() {
    return left;
  }

  public boolean getRight() {
    return right;
  }

  public void resetDirBooleans() {
    up = false;
    down = false;
    left = false;
    right = false;
  }
}
