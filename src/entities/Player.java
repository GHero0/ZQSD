package entities;

import static utilz.Constants.PlayerConstants.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import utilz.LoadSave;

public class Player extends Entity {

  private BufferedImage[][] animations;
  private int aniTick, aniIndex, aniSpeed = 30;
  private int playerAction = IDLE;
  private boolean left, right, up, down;
  private boolean moving = false;
  private boolean jumping = false;
  private boolean falling = false;
  private boolean oriented_left, oriented_right;

  private float playerSpeed = 4.0f;

  private float yVelocity = 0f;
  private final float GRAVITY = 0.3f;
  private final float MAX_FALL_SPEED = 8.0f;
  private final float JUMP_FORCE = -12.5f;
  private final float GROUND_LEVEL = 200f;// 465f;

  public Player(float x, float y) {
    super(x, y);
    loadAnimation();
  }

  public void update() {
    updatePos(); // First apply input logic (including jump)
    applyGravity(); // Then apply physics (gravity, falling)
    updateAnimationTick(); // Then update animation
    setAnimation(); // Finally, set proper animation state
  }

  private void updatePos() {
    moving = false;

    if (left && !right) {
      x -= playerSpeed;
      oriented_right = false;
      oriented_left = true;
      moving = true;
    } else if (right && !left) {
      x += playerSpeed;
      oriented_right = true;
      oriented_left = false;
      moving = true;
    }

    if (jumping && y == GROUND_LEVEL) {
      yVelocity = JUMP_FORCE;
    }

  }

  private void applyGravity() {
    yVelocity += GRAVITY;

    if (yVelocity > MAX_FALL_SPEED)
      yVelocity = MAX_FALL_SPEED;

    y += yVelocity;

    if (y >= GROUND_LEVEL) {
      y = GROUND_LEVEL;
      yVelocity = 0;
      jumping = false;
      falling = false;
    } else {
      falling = yVelocity > 0;
    }
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

    if (y < GROUND_LEVEL) {
      if (yVelocity < 0) {
        playerAction = JUMPING; // Going up
      } else {
        playerAction = FALLING; // Going down
      }
    } else if (moving) {
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
    if (oriented_right) {
      g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 200, 200, null);
    } else if (oriented_left) {
      g.drawImage(animations[playerAction][aniIndex], (int) x + 200, (int) y, -200, 200, null);
    } else {
      g.drawImage(animations[playerAction][aniIndex], (int) x, (int) y, 200, 200, null);
    }
  }

  private void loadAnimation() {
    BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
    animations = new BufferedImage[4][10];

    for (int j = 0; j < animations.length; j++) {
      for (int i = 0; i < animations[j].length; i++) {
        animations[j][i] = img.getSubimage(i * 32, j * 32, 32, 32);
      }
    }
  }

  public void setJumping(boolean jumping) {
    this.jumping = jumping;
  }

  public void setFalling(boolean falling) {
    this.falling = falling;
  }

  // Input handling
  public void setUp(boolean up) {
    this.up = up;
    if (up && y == GROUND_LEVEL) {
      jumping = true;
    }
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
