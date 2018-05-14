package edu.brusoman.mipt.elements;

import edu.brusoman.mipt.support.GamePanel;
import edu.brusoman.mipt.weapon.Cannon;
import edu.brusoman.mipt.weapon.Rifle;
import edu.brusoman.mipt.weapon.Shotgun;
import edu.brusoman.mipt.weapon.Spell;

import java.awt.*;

/**
 * Created by BMO on 30.04.2017.
 * <p>
 * Класс, наследуемый от AbstractElement
 *
 * @see #Player()
 * @see #manaIsReady(int)
 * @see #addAmmo(int, int)
 */
public class Player extends AbstractElement {
    //Переменные, отвечающие за перепещение игрока
    public static boolean up;
    public static boolean down;
    public static boolean left;
    public static boolean right;
    //Если ведется стрельба
    public static boolean isFiring;
    public static boolean isCasting;
    public WEAPONTYPE weapontype;
    private double mana;
    private int ammo;
    private double timerstart;// для подсчета отката маны
    private double timerstart2;// для подсчета отката оружия
    private boolean speedON;
    private boolean speedOFF;

    public Player() {

        x = GamePanel.WIDTH / 2;
        y = GamePanel.HEIGHT / 2;
        r = 5;
        color = Color.WHITE;

        up = false;
        down = false;
        left = false;
        right = false;
        speed = 10;

        dx = 0;
        dy = 0;

        isFiring = false;

        weapontype = WEAPONTYPE.RIFLE;
        health = 9999;
        mana = 100;
        ammo = 50;
        timerstart = System.nanoTime();
        timerstart2 = System.nanoTime();
        speedON = false;

    }
    //Contructor

    public void update() {

        //speed contorl
        if (speedON) {
            speed++;
        }
        if (speedOFF) {
            if (speed < 2) {

            } else {
                speed--;
            }

        }

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

        //Автодобавление патронов со временем
        addAmmo(150, 50);

        if (isFiring && ammo > 0) {
            switch (weapontype) {
                case RIFLE:

                    Rifle.fire();
                    ammo -= Rifle.requiredammo;
                    // ammo--;
                    break;

                case SHOTGUN:
                    Shotgun.fire();
                    ammo = ammo - Shotgun.requiredammo;

                    break;

                case CANNON:
                    Cannon.fire();
                    ammo = ammo - Cannon.requiredammo;
                    break;
            }

        }


        if (isCasting && mana >= 20 && manaIsReady(500)) {

            GamePanel.bullets.add(new Spell());
            mana = mana - 10;

        }

        if (health < 0) {
            // GameStart.main(null);
            System.exit(0);
            System.out.println("GAME OVER");
            System.exit(0);
            GamePanel.state = GamePanel.STATES.MENUE;
        }
    }

    public void draw(Graphics2D g) {
        g.setColor(color);
        g.fillOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));
        g.setColor(color.darker());
        g.drawOval((int) x - r, (int) y - r, 2 * r, 2 * r);
        g.setStroke(new BasicStroke(3));

    }

    /**
     * Проверка готовности для стрельбы маной
     *
     * @param time задержка во времени
     * @return
     */
    public boolean manaIsReady(int time) {
        if ((System.nanoTime() - timerstart) / 1_000_000 < time) {
            System.out.println("Mana is not ready, time: " + (System.nanoTime() - timerstart) / 1_000_000);
            return false;
        } else {
            System.out.println("Mana is ready");
            timerstart = System.nanoTime();
            return true;
        }
    }

    /**
     * Функция отвечающая за добавление патронов в бою
     *
     * @param time    задержка по времени
     * @param maxammo максимальное колличество патронов
     */
    public void addAmmo(int time, int maxammo) {

        if ((System.nanoTime() - timerstart2) / 1_000_000 < time) {
            //System.out.println("Ammo is not ready, time: "+ (System.nanoTime() - timerstart2) / 1_000_000);

        } else {
            // System.out.println("ADD Ammo");
            timerstart2 = System.nanoTime();
            if (ammo < maxammo) {
                ammo++;
            }
        }
    }

    /**
     * Функция отвечающая за переключение оружия в бою
     */
    public void switchweapon() {
        switch (weapontype) {
            case RIFLE:
                weapontype = WEAPONTYPE.SHOTGUN;
                System.out.println("Shotgun");
                break;

            case SHOTGUN:

                weapontype = WEAPONTYPE.CANNON;
                System.out.println("Cannon");
                break;

            case CANNON:
                weapontype = WEAPONTYPE.RIFLE;
                System.out.println("Rifle");
                break;

            default:
                weapontype = WEAPONTYPE.RIFLE;
                break;
        }
    }

    public double getMana() {
        return mana;
    }

    public int getAmmo() {
        return ammo;
    }

    public void addMana() {
        mana = mana + 5;
    }

    public void addSpeed(boolean key) {
        speedON = key;

    }

    public void decSpeed(boolean key) {
        if (speed < 2) {
            speedOFF = false;
        }
        speedOFF = key;

    }

    public static enum WEAPONTYPE {
        RIFLE, SHOTGUN, CANNON
    }


}
