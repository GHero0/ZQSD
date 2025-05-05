package main;

import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferedImage;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import utilz.LoadSave;

public class Gamewindow {

    private JFrame jframe;

    public Gamewindow(Gamepanel gamepanel) {
        jframe = new JFrame();
        jframe.setTitle("ZQSD");
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setResizable(false);
        jframe.add(gamepanel);
        jframe.pack();
        jframe.setLocationRelativeTo(null);

        BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.ICON_ATLAS);
        ImageIcon icon = new ImageIcon(img.getSubimage(98, 18, 30, 14));
        jframe.setIconImage(icon.getImage());
    
        jframe.setVisible(true);
        jframe.addWindowFocusListener(new WindowFocusListener() {
            @Override
            public void windowGainedFocus(WindowEvent e) {

            }

            @Override
            public void windowLostFocus(WindowEvent e) {
                gamepanel.getGame().WindowFocusLost();
            }
        });
    }
}