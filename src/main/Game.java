package main;

import java.awt.Graphics;
import java.util.logging.Level;

import entities.Player;
import levels.LevelManager;

public class Game implements Runnable {
    
    private Gamewindow gamewindow;
    private Gamepanel gamepanel;
    private Thread gamethread;
    private final int FPS = 120; // Frames per second
    private final int UPS = 200;
    
    private Player player; 
    private LevelManager levelManager;

    public final static int TILES_DEFAULT_SIZE = 32;
    public final static float SCALE = 4.0f;
    public final static int TILES_IN_WIDTH = 4;
    public final static int TILES_IN_HEIGHT = 4;
    public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
    public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
    public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;

    public Game() {
        initClasses();   
        gamepanel = new Gamepanel(this); 
        gamewindow = new Gamewindow(gamepanel);  
        gamepanel.requestFocus(); 
        startgameLoop();
    }

    private void initClasses() {
        player = new Player(0,0);
        levelManager = new LevelManager(this);
    }

    private void startgameLoop(){
        gamethread = new Thread(this); 
        gamethread.start(); 
    }


    public void update(){
        player.update();
        levelManager.update();
    }

    public void render(Graphics g){
        levelManager.draw(g);
        player.render(g);
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS; 
        double timeperUpdate = 1000000000.0 / UPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastchecked = System.currentTimeMillis();


        double deltaU = 0;
        double deltaF = 0;

        while (true){
            long currentTime = System.nanoTime();

            deltaU += (currentTime - previousTime) / timeperUpdate;
            deltaF += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if (deltaU >= 1){
                update();
                updates ++;
                deltaU--;
            }

            if (deltaF >= 1){
                gamepanel.repaint();
                frames++;
                deltaF--;
            }

            if (System.currentTimeMillis() - lastchecked >= 1000) {
                lastchecked = System.currentTimeMillis();
                System.out.println("FPS : " + frames + " UPS : " + updates);
                frames = 0;
                updates = 0;
            }
        }
    }

    public Player getPlayer(){
        return player;
    }

    public void WindowFocusLost(){
        player.resetDirBooleans(); 
    }
}
