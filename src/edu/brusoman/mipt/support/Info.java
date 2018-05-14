package edu.brusoman.mipt.support;

import java.awt.*;

/**
 * Created by BMO on 01.05.2017.
 */
public class Info {
    //Fields
    private int buttononWidth;
    private int buttonHeight;
    private Color color1;
    private String fps;
    private String enemies;
    private String health;
    private String mana;
    private String ammo;
    private String weapontype;
    private String score;
    private String speed;
    private int transp = 0; // Прозрачность
    //Constructor

    public Info() {
        buttononWidth = 120;
        buttonHeight = 60;
        color1 = Color.WHITE;
        fps = "FPS: " + GamePanel.FPS;

    }

    //Methods


    public void update() {
        int speed = 200 - GamePanel.FPSshowed;
        fps = "Gamespeed: " + speed;
        enemies = "Enemies: " + GamePanel.enemies.size();
        health = "Health: " + GamePanel.player.getHealth();
        mana = "Mana: " + GamePanel.player.getMana();
        ammo = "Ammo: " + GamePanel.player.getAmmo();
        weapontype = "Weapon: " + GamePanel.player.weapontype;
        //  score = "Score: "+ GamePanel.player.getScore();
        score = "Speed: " + GamePanel.player.getSpeed();

    }


    public void draw(Graphics2D g) {

        g.setColor(color1);
        g.setFont(new Font("Consolas", Font.BOLD, 30));

        long lenhth = (int) g.getFontMetrics().getStringBounds(fps, g).getWidth();
        //enemies.drawString(fps,(int)GamePanel.WIDTH + GamePanel.deltaframe/2 - lenhth/2,(int)GamePanel.HEIGHT - buttonHeight/4 );

        g.drawString(fps, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight);
        g.drawString(health, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight + 30);
        g.drawString(mana, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight + 90);
        g.drawString(ammo, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight + 120);
        g.drawString(enemies, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight + 150);
        g.drawString(weapontype, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight + 180);
        g.drawString(score, (int) GamePanel.WIDTH + GamePanel.deltaframe / 2 - lenhth / 2 - 80 + 50, buttonHeight + 210);
        // g.drawString(speed,(int)GamePanel.WIDTH  + GamePanel.deltaframe/2 - lenhth/2 - 80,buttonHeight+240 );


    }


}
