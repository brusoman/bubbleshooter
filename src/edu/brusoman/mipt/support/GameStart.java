package edu.brusoman.mipt.support;

/**
 * Created by BMO on 30.04.2017.
 */

import javax.swing.*;

public class GameStart {
    public static GamePanel panel = new GamePanel();

    public static void main(String[] args) {

        panel = new GamePanel();


        JFrame startFrame = new JFrame("Bubble shooter 2D");

        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//константы при выходе из приложения при нажатии на крестик

        startFrame.setContentPane(panel);// добавляем Jframe panel в нашу программу? ктотрый мы сами и пришем в другом классе
        startFrame.pack();// размер окна
        startFrame.setLocationRelativeTo(null);// установка окна по-серендине
        startFrame.setVisible(true);// можем видеть

        panel.start();
    }


}
