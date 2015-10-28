package main;

import java.awt.*;

/**
 * Created by Zheng-Yuan on 10/24/2015.
 */
public class Observer {

    private float x;
    private float y;

    private float speed = 25.0f;

    private float sceneWidth;
    private float sceneHeight;

    private boolean isLeft;
    private boolean isRight;
    private boolean isUp;
    private boolean isDown;

    public Observer(float sceneWidth, float sceneHeight){
        x = 2500;
        y = 1000;
        this.sceneHeight = sceneHeight;
        this.sceneWidth = sceneWidth;
    }

    private float checkLimit(float value, float lowerBound, float upperBound){
        if(value < lowerBound)  return lowerBound;
        if(value >= upperBound)  return upperBound - 0.01f;
        return value;
    }

    public void deal(){
        if(isLeft)  x -= speed;
        if(isRight) x += speed;
        if(isUp)    y -= speed;
        if(isDown)  y += speed;
        x = checkLimit(x, 0, sceneWidth);
        y = checkLimit(y, 0, sceneHeight);
        System.out.println(x + " " + y);
    }

    public void draw(Graphics2D g){
        float px = GamePanel.WINDOW_WIDTH / 2.0f;
        float py = GamePanel.WINDOW_HEIGHT / 2.0f;
        if(x < GamePanel.WINDOW_WIDTH / 2.0f)
            px -= GamePanel.WINDOW_WIDTH / 2.0f - x;
        if(x > sceneWidth - GamePanel.WINDOW_WIDTH / 2.0f)
            px += x - (sceneWidth - GamePanel.WINDOW_WIDTH / 2.0f);
        if(y < GamePanel.WINDOW_HEIGHT / 2.0f)
            py -= GamePanel.WINDOW_HEIGHT / 2.0f - y;
        if(y > sceneHeight - GamePanel.WINDOW_HEIGHT / 2.0f)
            py += y - (sceneHeight - GamePanel.WINDOW_HEIGHT / 2.0f);
        g.drawOval((int)px, (int)py, 10, 10);
    }

    public float getX(){
        return x;
    }

    public float getY(){
        return y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setIsLeft(boolean isLeft) {
        this.isLeft = isLeft;
    }

    public void setIsRight(boolean isRight) {
        this.isRight = isRight;
    }

    public void setIsUp(boolean isUp) {
        this.isUp = isUp;
    }

    public void setIsDown(boolean isDown) {
        this.isDown = isDown;
    }
}
