package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.Gamepanel;

public class Keyboardinputs implements KeyListener {

    private final Gamepanel gamepanel;

    public Keyboardinputs(Gamepanel gamepanel) {
        this.gamepanel = gamepanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
            case KeyEvent.VK_UP:
                gamepanel.getGame().getPlayer().setUp(true);
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_LEFT:
                gamepanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                gamepanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                gamepanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gamepanel.getGame().getPlayer().setUp(true); // Using the same method for jump
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
            case KeyEvent.VK_UP:
                gamepanel.getGame().getPlayer().setUp(false);
                break;
            case KeyEvent.VK_Q:
            case KeyEvent.VK_LEFT:
                gamepanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
            case KeyEvent.VK_DOWN:
                gamepanel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
            case KeyEvent.VK_RIGHT:
                gamepanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamepanel.getGame().getPlayer().setUp(false);
                break;
        }
    }
}