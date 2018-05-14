package edu.brusoman.mipt.elements;

import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;

/**
 * Created by BMO on 30.04.2017.
 * Класс Волна, регулирующий появление
 * противников и их количество в игре
 */
public class Wave {
    public String s;
    //Fields
    private int waveNumber;
    private String waveText;
    private long waveTimer;
    private long waveDelay;
    private long waveTimerDiff;
    private int waveMultipier;

    //Constructor
    public Wave() {
        waveMultipier = 5;
        waveNumber = 1;
        waveTimer = 0;
        waveDelay = 5000;
        waveTimerDiff = 0;
        waveText = "ВОЛНА -";
    }

    //Methods
    public void createEnemies() {
        int enemyCount = 2 * waveNumber * waveMultipier;
        if (waveNumber <= 2) {
            for (int i = 0; i < enemyCount; i++) {

                int rank = 1;
                GamePanel.enemies.add(new Enemy2ext(Enemy2ext.TYPE.NORMAL, rank));
                GamePanel.enemies.add(new Enemy2ext(Enemy2ext.TYPE.BOSS, rank));

                System.out.println("Normal enemy added");
            }


        }
        if (waveNumber > 2) {
            for (int i = 0; i < enemyCount; i++) {

                int rank = 1;
                GamePanel.enemies.add(new Enemy2ext(Enemy2ext.TYPE.NORMAL, rank));
                GamePanel.enemies.add(new Enemy2ext(Enemy2ext.TYPE.FIRING, rank));
            }
        }

        waveNumber++;
    }


    public void update() {
        if (GamePanel.enemies.size() == 0 && waveTimer == 0) {
            waveTimer = System.nanoTime();

        }
        if (waveTimer > 0) {
            waveTimerDiff += (System.nanoTime() - waveTimer) / 1_000_000;
            waveTimer = System.nanoTime();
        }
        if (waveTimerDiff > waveDelay) {
            createEnemies();
            waveTimer = 0;
            waveTimerDiff = 0;

        }
    }

    public boolean showWave() {
        if (waveTimer != 0) {
            return true;
        } else return false;

    }


    public void draw(Graphics2D g) {
        double divider = waveDelay / 180;
        double alpha = waveTimerDiff / divider;
        alpha = 255 * Math.sin(Math.toRadians(alpha));

        if (alpha < 0) alpha = 0;
        if (alpha > 255) alpha = 255;

        g.setFont(new Font("consolas", Font.PLAIN, 20));
        g.setColor(new Color(255, 255, 255, (int) alpha));
        s = "- " + waveNumber + "ая" + waveText;

        long length = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();// Длинна текста в пикселях

        g.drawString(s, GamePanel.WIDTH / 2 - (int) length / 2, GamePanel.HEIGHT / 2);
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
