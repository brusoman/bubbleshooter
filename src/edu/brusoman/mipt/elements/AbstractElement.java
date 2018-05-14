package edu.brusoman.mipt.elements;

import edu.brusoman.mipt.support.GamePanel;

import java.awt.*;


/**
 * Абстрактный класс для создания объектов игры.
 *
 * @version 1.0
 * @autor BMO on 01.05.2017.
 * @see #update()
 * @see #draw(Graphics2D)
 * @see #remove()
 * @see #hit()
 */

public abstract class AbstractElement {

    /**
     * Поле координат
     */
    protected double x;
    protected double y;
    protected int r;
    protected double speed;
    protected Color color;


    protected int type;
    protected String typeclass = "None";
    protected int rank;
    protected double health;

    protected double dx;
    protected double dy;
    protected int score;

    /**
     * Обновление состояния
     */

    public abstract void update();

    /**
     * Нарисовать объект
     */
    public abstract void draw(Graphics2D g);

    /**
     * Если в объект попали
     */
    public void hit() {
        health--;
    }


    /**
     * Нужно ли удалять Объект?
     */
    public boolean remove() {
        //Проверка на вылет из игровой зоны
        if (y < 0 || x < 0 || y > GamePanel.WIDTH || x > GamePanel.WIDTH) {
            return true;
        }
        return false;
    }


    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getR() {
        return r;
    }

    public String getTypeclass() {
        return typeclass;
    }

    public void setTypeclass(String typeclass) {
        this.typeclass = typeclass;
    }

    public int getType() {
        return type;
    }

    public int getScore() {
        return score;
    }

    public double getHealth() {
        return health;
    }

    public void addScore(int add) {
        score += add;
    }

    public double getSpeed() {
        return speed;
    }
}
