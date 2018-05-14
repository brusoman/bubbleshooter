package edu.brusoman.mipt.weapon;

import edu.brusoman.mipt.elements.Bullet2ext;
import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 02.05.2017.
 */
public class Shotgun extends Bullet2ext {
    public static int requiredammo = 3;

    public Shotgun() {

        typeclass = "Shotgun";
        color = Color.RED;
        r = 20;
        speed = 9;
        type = 999;
        requiredammo = 3;
    }

    public static void fire() {
        GamePanel.bullets.add(new Bullet2ext(-30));
        GamePanel.bullets.add(new Bullet2ext(0));
        GamePanel.bullets.add(new Bullet2ext(30));

    }

}
