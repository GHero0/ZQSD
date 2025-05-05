package main;

import inputs.Keyboardinputs;
import inputs.Mouseinputs;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
import java.awt.Dimension;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

public class Gamepanel extends JPanel {

    private Mouseinputs mouseinputs;
    private Game game;

    public Gamepanel(Game game) {
        mouseinputs = new Mouseinputs(this);
        this.game = game;

        setPanelSize();
        setBackground(Color.GRAY); // Set the background color to gray
        addKeyListener(new Keyboardinputs(this));
        addMouseListener(mouseinputs);
        addMouseMotionListener(mouseinputs);
    }

    private void setPanelSize() {
        Dimension size = new Dimension(GAME_WIDTH, GAME_HEIGHT);
        setPreferredSize(size);
        System.out.println("Size : " + GAME_WIDTH + " : " + GAME_HEIGHT);
    }

    public void updateGame() {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.render(g);
    }

    public Game getGame() {
        return game;
    }
}
