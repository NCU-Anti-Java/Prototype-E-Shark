package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class GamePanel extends JPanel implements Runnable, KeyListener {

    public static final int WINDOW_WIDTH = 500;
    public static final int WINDOW_HEIGHT = 300;

    private final int SCENE_WIDTH = 5000;
    private final int SCENE_HEIGHT = 2000;

    private Thread thread;
    private boolean running;
    private final int FPS = 30;
    private long targetTime = 1000 / FPS;

    private BufferedImage image;
    private Graphics2D g;

    private Scene scene;

    GamePanel() {
        setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        setLayout(null);
        setFocusable(true);
        requestFocus();
    }

    private void initialize(){
        image = new BufferedImage(WINDOW_WIDTH, WINDOW_HEIGHT, BufferedImage.TYPE_INT_RGB);
        scene = new Scene(SCENE_WIDTH, SCENE_HEIGHT);
        g = (Graphics2D)image.getGraphics();
        System.out.print("Hello");
        running = true;
    }

    private void deal(){
        scene.deal();
    }

    private void draw(){
        scene.draw(g);
    }

    private void drawToScreen(){
        Graphics g2 = getGraphics();
        g2.drawImage(image, 0, 0, WINDOW_WIDTH, WINDOW_HEIGHT, null);
        g2.dispose();
    }

    @Override
    public void addNotify(){
        super.addNotify();
        if(thread == null){
            thread = new Thread(this);
            addKeyListener(this);
            thread.start();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        scene.keyPressed(e.getKeyCode());
    }

    @Override
    public void keyReleased(KeyEvent e) {
        scene.keyReleased(e.getKeyCode());
    }

    @Override
    public void run() {
        initialize();
        long start;
        long end;
        long wait;
        while(running){
            start = System.nanoTime();
            deal();
            draw();
            drawToScreen();
            end = System.nanoTime();
            wait = targetTime - (end - start) / 10000000;
            if(wait < 0)    wait = 5;
            try{
                Thread.sleep(wait);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
