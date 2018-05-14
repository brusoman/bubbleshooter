package edu.brusoman.mipt.elements;

import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 01.05.2017.
 */
public class Bullet2ext extends AbstractElement {

    public static int requiredammo;
    private boolean cameOutSpell;
    private int angle;

    public Bullet2ext() {
        typeclass = "Bullet";
        x = GamePanel.player.getX();
        y = GamePanel.player.getY();
        r = 5;
        color = Color.WHITE;
        speed = 10;
        angle = 0;
        cameOutSpell = false;
    }

    public Bullet2ext(int angle) {
        this();
        this.angle = angle;
    }

    public Bullet2ext(int angle, int radius) {
        this();
        this.r = radius;
        this.angle = angle;
    }

    public Bullet2ext(int angle, int radius, String typeclass) {
        this(angle, radius);
        this.typeclass = typeclass;

    }

    public Bullet2ext(int angle, int radius, int speed, Color color) {
        this();
        this.r = radius;
        this.angle = angle;
        this.color = color;
        this.speed = speed;


    }

    public Bullet2ext(AbstractElement element) {
        typeclass = "Bullet";
        x = element.getX();
        y = element.getY();
        r = 2;
        this.angle = angle;
        color = Color.RED;
        speed = 10;
        cameOutSpell = true;//?????????????????????
        System.out.println("It came from spell!");
    }

    public Bullet2ext(Enemy2ext enemy) {

        typeclass = "Bullet from enemy";
        type = 90;
        x = enemy.getX();
        y = enemy.getY();
        r = 9;
        angle = 0;
        color = Color.red;
        speed = -10;
        cameOutSpell = false;//?????????????????????
        // System.out.println("Enemy is firing!");
    }

    //Methods
    @Override
    public void update() {
        if (cameOutSpell) {
            y -= speed * Math.sin(Math.toRadians(Math.random() * 360));
            x -= speed * Math.sin(Math.toRadians(Math.random() * 360));
            //   System.out.println("( "+x + ","+ y + ")");
            System.out.println("Came out Spell");
        }

        y -= speed;
        x -= speed * Math.sin(Math.toRadians(angle));

        //System.out.println("X: " + x);
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        if (typeclass.equals("Cannon")) {
            g.fillOval((int) x, (int) y, r, r);
        } else
            g.fillOval((int) x, (int) y, r, 2 * r);

    }

}
