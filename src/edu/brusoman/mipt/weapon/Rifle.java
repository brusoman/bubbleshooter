package edu.brusoman.mipt.weapon;

import edu.brusoman.mipt.elements.Bullet2ext;
import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 02.05.2017.
 */
public class Rifle extends Bullet2ext {
    public static int requiredammo = 1;

    public Rifle() {

        typeclass = "Rifle";
        color = Color.WHITE;
        r = 20;
        speed = 9;
        type = 999;


    }

    public static void fire() {
        GamePanel.bullets.add(new Bullet2ext(0));
    }

}
