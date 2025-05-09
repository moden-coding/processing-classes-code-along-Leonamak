import processing.core.*;
public class Bubble {
    private int x;
    private int y;
    private int size;
    private PApplet canvas;
    private int speed;
    private int color;
    private int health;

    public Bubble(int xPos, int yPos, PApplet c) {
        x = xPos;
        y = yPos;
        size = 200;
        canvas = c;
        speed = 5;
        health = 3;
        
    }

    public int health(){
        return health;
    }
    public void display() {
        if (health == 3) {
            color = canvas.color(0,255,0);
            } else if (health == 2) {
                color = canvas.color(255,255,0);
            } else {
                color = canvas.color(255,0,0);
            }
        canvas.fill(color);
        canvas.circle(x, y, size);
    }
    public void move() {
        x += speed;
        if (x + size/2 >= canvas.width || x - size/2< 0) {
            speed = -speed;
        }
    }
    public int randomColor() {
        return canvas.color(canvas.random(255),canvas.random(255),canvas.random(255));
    }
    public void loseHealth() {
        health = health-1;
    }
    public boolean checkTouch(int checkx,int checky) {
        if (checkx < x + size/2 && checkx > x - size/2 && checky < y + size/2 && checky > y-size/2) {
                return true;
         } else {
                return false;
            }
    }
}
