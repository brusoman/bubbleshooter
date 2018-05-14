package oldClasses;

import edu.brusoman.mipt.elements.AbstractElement;
import edu.brusoman.mipt.elements.Bullet2ext;
import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 01.05.2017.
 */
public class Player2ext extends AbstractElement {
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;

    public static boolean isFiring;
    public static boolean isCasting;

    public Player2ext() {

        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        r = 5;
        color = Color.WHITE;

        up = false;
        down = false;
        left = false;
        right = false;
        speed = 5;

        dx = 0;
        dy = 0;

        isFiring = false;
        health = 3;
    }


    //Methods


    @Override
    public void update() {
        if (up && y > r) {
            dy = -speed;
        }
        if (down && y < GamePanel.HEIGHT - r) {
            dy = +speed;
        }
        if (left && x > r) {
            dx = -speed;
        }
        if (right && x < GamePanel.WIDTH - r) {
            dx = +speed;
        }

        if (up && left || up && right || down && left || down && right) {

            dy = dy * Math.sin(Math.toRadians(45)); // потому что расчет в радианах
            dx = dx * Math.cos(Math.toRadians(45));
        }

        y += dy;
        x += dx;

        dy = 0;
        dx = 0;

        if (isFiring) {
            GamePanel.bullets.add(new Bullet2ext());
        }


    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
    }

    @Override
    public void hit() {
        health--;
        System.out.println(health);
    }
}
