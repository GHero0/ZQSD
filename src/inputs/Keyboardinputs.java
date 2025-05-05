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
                
                break;
            case KeyEvent.VK_Q:
                gamepanel.getGame().getPlayer().setLeft(true);
                break;
            case KeyEvent.VK_S:
                gamepanel.getGame().getPlayer().setDown(true);
                break;
            case KeyEvent.VK_D:
                gamepanel.getGame().getPlayer().setRight(true);
                break;
            case KeyEvent.VK_SPACE:
                gamepanel.getGame().getPlayer().setJumping(true);;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Z:
                
                break;
            case KeyEvent.VK_Q:
                gamepanel.getGame().getPlayer().setLeft(false);
                break;
            case KeyEvent.VK_S:
                gamepanel.getGame().getPlayer().setDown(false);
                break;
            case KeyEvent.VK_D:
                gamepanel.getGame().getPlayer().setRight(false);
                break;
            case KeyEvent.VK_SPACE:
                gamepanel.getGame().getPlayer().setJumping(false);
                break;
        }
    }

    
}
