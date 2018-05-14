package oldClasses;

import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 30.04.2017.
 */
public class Bullet {

    //Fields
    private double x;
    private double y;
    private int r;
    private int speed;
    private Color color;

    //Constructor
    public Bullet() {
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 2;
        color = Color.WHITE;
        speed = 10;
    }

    //Methods

    public void update() {
        y -= speed;
    }


    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, 2 * r);

    }

    public boolean remove() {
        if (y < 0) {
            return true;
        }
        return false;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }
}
