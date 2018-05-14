package edu.brusoman.mipt.support;

import java.awt.*;

/**
 * Created by BMO on 30.04.2017.
 */
public class Menu {
    //Fields
    private int buttononWidth;
    private int buttonHeight;
    private Color color1;
    private String s;
    private int transp = 0; // Прозрачность
    //Constructor

    public Menu() {
        buttononWidth = 120;
        buttonHeight = 60;
        color1 = Color.WHITE;
        s = "Play!";
    }

    //Methods


    public void update() {
        if (GamePanel.mouseX > GamePanel.WIDTH / 2 - buttononWidth / 2 &&
                GamePanel.mouseX < GamePanel.WIDTH / 2 + buttononWidth / 2 &&
                GamePanel.mouseY > GamePanel.HEIGHT / 2 - buttonHeight / 2 &&
                GamePanel.mouseY < GamePanel.WIDTH / 2 + buttonHeight / 2) {

            transp = 60;

            if (GamePanel.leftMouse) { //нажата мышь в окне плей

                GamePanel.state = GamePanel.STATES.PLAY;
            }
        } else {
            transp = 0;
        }
    }


    public void draw(Graphics2D g) {
        g.setColor(color1);
        g.setStroke(new BasicStroke(3));
        g.drawRect(GamePanel.WIDTH / 2 - buttononWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2, buttononWidth, buttonHeight);

        // Для мигания
        g.setColor(new Color(255, 255, 255, transp));
        g.fillRect(GamePanel.WIDTH / 2 - buttononWidth / 2, GamePanel.HEIGHT / 2 - buttonHeight / 2, buttononWidth, buttonHeight);

        g.setStroke(new BasicStroke(1));

        g.setColor(color1);
        g.setFont(new Font("Consolas", Font.BOLD, 40));

        long lenhth = (int) g.getFontMetrics().getStringBounds(s, g).getWidth();
        g.drawString(s, (int) GamePanel.WIDTH / 2 - lenhth / 2, (int) GamePanel.HEIGHT / 2 + buttonHeight / 4);
    }


}
