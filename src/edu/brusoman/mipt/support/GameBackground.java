package edu.brusoman.mipt.support;

import edu.brusoman.mipt.elements.AbstractElement;

import java.awt.*;

/**
 * Created by BMO on 30.04.2017.
 */
public class GameBackground extends AbstractElement {
    //Fields
    private Color color;


    //Constructors

    public GameBackground() { // DEFAULT
        this.color = Color.BLUE;

    }

    // Functions
    public void update() {
        //если новая волна, то менять цвет
        if (GamePanel.wave.getWaveNumber() > 2) {

            color = Color.BLUE;
        }
    }

    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(color);
        graphics2D.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
        graphics2D.setColor(Color.gray);
        graphics2D.fillRect(GamePanel.WIDTH, 0, GamePanel.deltaframe, GamePanel.HEIGHT);

    }

}
