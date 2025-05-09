import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.*;

public class App extends PApplet {
    ArrayList<Bubble> bubbles;
    double timer;
    int scene = 0;
    double gamestart;
    double highscore;

    public static void main(String[] args) {
        PApplet.main("App");
    }

    public void setup() {
        bubbles = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            bubbleMaker();
        }
        System.out.println("setup called");
        scene = 0;
        gamestart = millis();
    }

    public void settings() {
        size(1000, 1000);
    }

    public void draw() {
        background(0);
        if (scene == 0) {
            for (Bubble b : bubbles) {
                b.display();
                b.move();
            }
            fill(255);
            textSize(50);
            timer = millis() - gamestart;
            timer = timer / 1000.0;
            text("" + timer, width - 550, 50);
            if (bubbles.size() == 0) {
                scene = 1;
                if (highscore > timer || highscore == 0) {
                    highscore = timer;
                    savehighscore();
                }
            }
        } else {
            text("score: " + timer, width - 650, 400, 400);
            text("highscore: " + highscore, width - 650, 500, 500);
        }
    }

    public void bubbleMaker() {
        int x = (int) random(100, 900);
        int y = (int) random(100, 800);
        Bubble bubble = new Bubble(x, y, this);
        bubbles.add(bubble);
    }

    public void keyPressed() {
        if (key == 'm') {
            int x = (int) random(100, 900);
            int y = (int) random(100, 800);
            Bubble bubble = new Bubble(x, y, this);
            bubbles.add(bubble);
        }
        if (key == '.') {
            bubbles.clear();
        }
        if (key == ' ') {
            if (scene == 0) {
                bubbles.clear();
                timer = timer - timer;
            } else {

                setup();
            }
        }
    }

    public void mousePressed() {
        for (int i = 0; i < bubbles.size(); i++) {
            Bubble b = bubbles.get(i);
            if (b.checkTouch(mouseX, mouseY)) {
                b.loseHealth();
            }
            if (b.health() < 1) {
                bubbles.remove(i);
            }
        }
    }

    public void readhighscore() {
        try (Scanner scanner = new Scanner(Paths.get("highscore.txt"))) {
                highscore = Double.valueOf(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
    public void savehighscore() {
        try (PrintWriter writer = new PrintWriter("highscore.txt")) {
            writer.println(highscore);
            writer.close();
            System.out.println("integer saved to file");
        } catch (IOException e) {
            System.out.println("integer save failed");
            e.printStackTrace();
        }
    }
}
