package edu.brusoman.mipt.weapon;

import edu.brusoman.mipt.elements.Bullet2ext;

import java.awt.*;

/**
 * Created by BMO on 01.05.2017.
 */
public class Spell extends Bullet2ext {
    public Spell() {
        super();
        typeclass = "Spell";
        color = Color.magenta;
        r = 20;
        speed = 9;
        type = 999;

    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x, (int) y, r, r);

    }

    @Override
    public boolean remove() {
        if (y < 0) {

            return true;
        }
        return false;
    }


}
