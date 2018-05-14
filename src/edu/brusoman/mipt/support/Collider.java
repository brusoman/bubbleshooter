package edu.brusoman.mipt.support;

import edu.brusoman.mipt.elements.AbstractElement;
import edu.brusoman.mipt.elements.Bullet2ext;
import edu.brusoman.mipt.elements.Enemy2ext;
import edu.brusoman.mipt.elements.Player;

import java.util.ArrayList;

/**
 * Created by BMO on 01.05.2017.
 */
public class Collider {
    /**
     * Проверка столкновения врага и пули, добавление игроку маны
     */
    public static boolean checkCollide(ArrayList<Enemy2ext> enemies, ArrayList<Bullet2ext> bullets) {


        for (int i = 0; i < enemies.size(); i++) {
            AbstractElement e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = GamePanel.player.getX();
            double py = GamePanel.player.getY();
            for (int j = 0; j < bullets.size(); j++) {

                AbstractElement b = bullets.get(j);

                double bx = b.getX();
                double by = b.getY();

                double dx = ex - bx;
                double dy = ey - by;

                double dpx = px - bx;
                double dpy = py - by;

                double dist = Math.sqrt(dx * dx + dy * dy);
                double distplayer = Math.sqrt(dpx * dpx + dpy * dpy);
                //System.out.println("Distpayer " + distplayer);
                if ((int) dist <= e.getR() + b.getR() && b.getType() != 90) {
                    //Заходим сюда, если произошло столкновение пули от врага и врага


                    if (b.getTypeclass().equals("Spell")) {
                        //Событие если это спелл
                        System.out.println("It is Spell");
                        for (int k = 0; k < 30; k++) {//////////////////здесь после взрыва вылетает k пуль
                            GamePanel.bullets.add(new Bullet2ext(b));
                        }
                    }


                    e.hit();

                    bullets.remove(j);
                    j--;
                    boolean remove = e.remove();
                    System.out.println("Bullet removed");

                    if (remove) {
                        //сюда написать выпадение какой -нибуть херни если враг умер

                        enemies.remove(i);//Здесь производится удаление этемента
                        i--;
                        GamePanel.player.addMana();// Если враг убит, добавляем маны
                        GamePanel.player.addScore(e.getScore());
                        break;
                    }
                }
                // Проверка на столкновение пули от врага и игрока
                if ((int) distplayer <= GamePanel.player.getR() + b.getR() && b.getType() == 90) {
                    GamePanel.player.hit();
                    bullets.remove(j);
                    j--;
                }


            }
        }


        return false;
    }

    public static boolean checkCollide(ArrayList<Enemy2ext> enemies, Player player) {
        for (int i = 0; i < enemies.size(); i++) {
            Enemy2ext e = enemies.get(i);
            double ex = e.getX();
            double ey = e.getY();

            double px = player.getX();
            double py = player.getY();

            double dx = ex - px;
            double dy = ey - py;

            double dist = Math.sqrt(dx * dx + dy * dy);
            if ((int) dist <= e.getR() + player.getR()) {

                e.hit();
                player.hit();

                boolean remove = e.remove();

                if (remove) {
                    enemies.remove(i);
                    i--;

                }


            }

        }


        return false;
    }


}
