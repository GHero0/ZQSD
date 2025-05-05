package inputs;

import main.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouseinputs implements MouseListener, MouseMotionListener {


    private final Gamepanel gamepanel;

    public Mouseinputs(Gamepanel gamepanel) {
        this.gamepanel = gamepanel;
    }
 
    @Override
    public void mouseClicked(java.awt.event.MouseEvent e) {
        
    }

    @Override
    public void mousePressed(java.awt.event.MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseReleased(java.awt.event.MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseDragged(java.awt.event.MouseEvent e) {
        // Not used
    }

    @Override
    public void mouseMoved(java.awt.event.MouseEvent e) {
        // System.out.println("Mouse moved to: " + e.getX() + ", " + e.getY());
        // gamepanel.setRectposition(e.getX(), e.getY());
    }
}