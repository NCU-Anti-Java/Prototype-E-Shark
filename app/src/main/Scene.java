package main;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;
import Block.Block;

/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class Scene {

    private int width;
    private int height;

    private final int BLOCK_SIZE = 100;
    private int nrow;
    private int ncol;

    private Observer observer;

    private Block[][] map;

    public Scene(int width, int height){
        this.width = width;
        this.height = height;
        observer = new Observer(width, height);
        nrow = height / BLOCK_SIZE;
        ncol = width / BLOCK_SIZE;
        map = new Block[nrow][ncol];
        for(int i = 0;i<nrow;i++)
            for(int j = 0;j<ncol;j++)
                map[i][j] = new Block((new Random()).nextInt(5));
    }

    public void deal(){
        observer.deal();
    }

    public void draw(Graphics2D g){
        float windowX, windowY;
        float windowEndX, windowEndY;
        float offsetX, offsetY;
        int mapX, mapY;
        if(observer.getX() < GamePanel.WINDOW_WIDTH / 2.0f) {
            windowX = 0;
            windowEndX = windowX + GamePanel.WINDOW_WIDTH;
        }
        else if(observer.getX() > width - GamePanel.WINDOW_WIDTH / 2.0f){
            windowEndX = width;
            windowX = windowEndX - GamePanel.WINDOW_WIDTH;
        }
        else{
            windowX = observer.getX() - GamePanel.WINDOW_WIDTH / 2.0f;
            windowEndX = windowX + GamePanel.WINDOW_WIDTH;
        }
        if(observer.getY() < GamePanel.WINDOW_HEIGHT / 2.0f) {
            windowY = 0;
            windowEndY = windowY + GamePanel.WINDOW_HEIGHT;
        }
        else if(observer.getY() > height - GamePanel.WINDOW_HEIGHT / 2.0f){
            windowEndY = height;
            windowY = windowEndY - GamePanel.WINDOW_HEIGHT;
        }
        else{
            windowY = observer.getY() - GamePanel.WINDOW_HEIGHT / 2.0f;
            windowEndY = windowY + GamePanel.WINDOW_HEIGHT;
        }
        offsetX = windowX - (float)Math.floor(windowX / BLOCK_SIZE) * BLOCK_SIZE;
        offsetY = windowY - (float)Math.floor(windowY / BLOCK_SIZE) * BLOCK_SIZE;
        mapX = (int)Math.floor(windowX / BLOCK_SIZE);
        mapY = (int)Math.floor(windowY / BLOCK_SIZE);
        for(int row = (int)Math.floor(windowY / BLOCK_SIZE); row <= (int)Math.floor(windowEndY / BLOCK_SIZE); row++){
            for(int col = (int)Math.floor(windowX / BLOCK_SIZE); col <= (int)Math.floor(windowEndX / BLOCK_SIZE); col++){
                if(row >= nrow) continue;
                if(col >= ncol) continue;
                map[row][col].draw(g, (int)((col - mapX) * BLOCK_SIZE - offsetX), (int)((row - mapY) * BLOCK_SIZE - offsetY), BLOCK_SIZE, BLOCK_SIZE);
            }
        }
        observer.draw(g);
    }

    public void keyPressed(int k){
        switch(k){
            case KeyEvent.VK_LEFT:
                observer.setIsLeft(true);
                break;
            case KeyEvent.VK_RIGHT:
                observer.setIsRight(true);
                break;
            case KeyEvent.VK_UP:
                observer.setIsUp(true);
                break;
            case KeyEvent.VK_DOWN:
                observer.setIsDown(true);
                break;
        }
    }

    public void keyReleased(int k){
        switch(k){
            case KeyEvent.VK_LEFT:
                observer.setIsLeft(false);
                break;
            case KeyEvent.VK_RIGHT:
                observer.setIsRight(false);
                break;
            case KeyEvent.VK_UP:
                observer.setIsUp(false);
                break;
            case KeyEvent.VK_DOWN:
                observer.setIsDown(false);
                break;
        }
    }

}
