package edu.brusoman.mipt.elements;

import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 01.05.2017.
 */
public class Enemy2ext extends AbstractElement {
    private TYPE type;
    private long timerstart;

    public Enemy2ext(TYPE type, int rank) {
        score = 50;
        typeclass = "Enemy";
        this.type = type;
        this.rank = rank;
        timerstart = System.nanoTime();
        switch (type) {
            case NORMAL:
                switch (rank) {
                    case 1:
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        speed = 2;
                        color = Color.GREEN;
                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        r = 7;
                        health = 2;
                        break;
                }
                break;
            case FIRING:
                switch (rank) {
                    case 1:
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        speed = 6;
                        color = Color.RED;
                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        r = 10;
                        health = 10;
                        System.out.println("HERE");
                        break;


                }
                break;
            case BOSS:
                switch (rank) {
                    case 1:
                        x = Math.random() * GamePanel.WIDTH;
                        y = 0;
                        speed = 5;
                        color = Color.BLACK;
                        double angle = Math.toRadians(Math.random() * 360);
                        dx = Math.sin(angle) * speed;
                        dy = Math.cos(angle) * speed;
                        r = 30;
                        health = 50;

                        break;


                }
                break;
        }
    }

    @Override
    public void update() {


        x += dx;
        y += dy;

        if (x < 0 && dx < 0) {
            dx = -dx;
        }
        if (x > GamePanel.WIDTH && dx > 0) {
            dx = -dx;
        }
        if (y < 0 && dy < 0) {
            dy = -dy;
        }
        if (y > GamePanel.HEIGHT && dy > 0) {
            dy = -dy;
        }

        if (type == TYPE.FIRING) {
            //enemy fires

        }
        if (fireIsReady()) {
            GamePanel.bullets.add(new Bullet2ext(this));
            timerstart = System.nanoTime();
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

    public boolean remove() {
        if (health <= 0) {
            return true;
        }
        return false;
    }

    public boolean fireIsReady() {
        if ((System.nanoTime() - timerstart) / 1_000_000 < 5000) {
            //System.out.println("Enemy is not ready, time: " + (System.nanoTime() - timerstart) / 1_000_000);
            return false;
        } else return true;

    }

    public static enum TYPE {
        NORMAL, FIRING, BOSS
    }


}
