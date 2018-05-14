package edu.brusoman.mipt.support;

import edu.brusoman.mipt.elements.Bullet2ext;
import edu.brusoman.mipt.elements.Enemy2ext;
import edu.brusoman.mipt.elements.Player;
import edu.brusoman.mipt.elements.Wave;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by BMO on 30.04.2017.
 */
public class GamePanel extends JPanel implements Runnable {

    //Field
    public static int WIDTH = 800;
    public static int HEIGHT = 800;

    public static int mouseX;
    public static int mouseY;
    //FPS flields
    public static int FPS;
    public static int sleepTime;
    public static int deltaframe = 300;
    public static STATES state = STATES.MENUE;
    public static GameBackground background;
    public static Player player;
    public static ArrayList<Bullet2ext> bullets;
    public static ArrayList<Enemy2ext> enemies;
    public static Wave wave;
    public static Menu menu;
    public static boolean leftMouse;
    public static Info info;
    public static int FPSshowed;
    public Thread thread;
    public boolean FPSturn;
    public boolean FPSdegree;
    public Graphics2D g1;
    private long timerFPS;
    private double millisToFPS;
    private BufferedImage image;

    //Constructor
    public GamePanel() {
        super();
        this.setPreferredSize(new Dimension(WIDTH + deltaframe, HEIGHT));
        this.setFocusable(true);//активное окно

        this.requestFocus();

        addKeyListener(new Listeners());
        addMouseMotionListener(new Listeners());
        addMouseListener(new Listeners());
        addMouseWheelListener(new Listeners());
        FPSturn = false;
    }

    public void start() {
        thread = new Thread(this); // Создали новый поток
        thread.start();

    }

    @Override
    public void run() {
        FPS = 30;
        sleepTime = 0;

        image = new BufferedImage(WIDTH + deltaframe, HEIGHT, BufferedImage.TYPE_INT_RGB);

        g1 = (Graphics2D) image.getGraphics();

        g1.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);// Сглаживание

        leftMouse = false;

        // Objects on the screen

        background = new GameBackground();

        player = new Player();
        bullets = new ArrayList<Bullet2ext>();
        enemies = new ArrayList<Enemy2ext>();
        wave = new Wave();
        menu = new Menu();
        info = new Info();

        // Генератор врагов


        while (true) { // в перспективе не бесконечный
            if (FPSturn) {
                FPS += 5;
            }
            if (FPSdegree) {
                FPS -= 5;
            }

            millisToFPS = 1 * FPS;

            timerFPS = System.nanoTime();

            if (state.equals(STATES.MENUE)) {
                background.update();
                background.draw(g1);
                menu.update();
                menu.draw(g1);
                gameDraw();
            }
            if (state.equals(STATES.PLAY)) {
                gameUpdate();
                gameRender();
                gameDraw();
            }
            if (state.equals(STATES.GAMEOVER)) {
                System.exit(0);
            }


            timerFPS = (System.nanoTime() - timerFPS) / 1_000_000;
            if (millisToFPS > timerFPS) {
                sleepTime = (int) (millisToFPS - timerFPS);

            } else sleepTime = 1;

            try {
                thread.sleep(sleepTime); // FPS
                //  System.out.println("FPS: " + sleepTime);
                FPSshowed = sleepTime;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            timerFPS = 0;
            sleepTime = 1;


        }

    }

    public void gameUpdate() { // обновление состояния
        //Background update
        background.update();

        //Player update
        player.update();

        //Bullets update
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).update();
            boolean remove = bullets.get(i).remove();
            if (remove) { // выход за границу карты
                bullets.remove(i);
                i--;
            }
            System.out.println("Bullets number" + bullets.size() + "\n");
        }


        //Enemies update
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).update();
        }


        // Bullets-Enemy collide
        Collider.checkCollide(enemies, bullets);

        //Player Enemy-Collide
        Collider.checkCollide(enemies, player);


        //Wave update
        wave.update();

        //Background update
        background.update();

        //Info update
        info.update();

    }

    public void gameRender() {// обновление графики
        //Background draw
        background.draw(g1);

        //Player draw
        player.draw(g1);

        //Bullets draw
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).draw(g1);
        }

        //Enemies draw
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).draw(g1);
        }

        //Wave draw
        if (wave.showWave()) {
            wave.draw(g1);
        }

        //Info draw
        info.draw(g1);

    }

    private void gameDraw() {

        Graphics g2 = this.getGraphics();
        g2.drawImage(image, 0, 0, null);
        g2.dispose();
    }

    public boolean isFPSturn() {
        return FPSturn;
    }

    public void setFPSturn(boolean FPSturn) {
        this.FPSturn = FPSturn;
    }

    public boolean isFPSdegree() {
        return FPSdegree;
    }

    public void setFPSdegree(boolean FPSturn) {
        this.FPSdegree = FPSturn;
    }

    public static enum STATES {
        MENUE,
        PLAY,
        GAMEOVER
    }
}
