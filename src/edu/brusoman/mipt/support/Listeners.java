package edu.brusoman.mipt.support;

import edu.brusoman.mipt.elements.Player;

import java.awt.event.*;

/**
 * Created by BMO on 30.04.2017.
 */
public class Listeners implements KeyListener, MouseListener, MouseMotionListener, MouseWheelListener {


    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {

            Player.up = true;

        }
        if (key == KeyEvent.VK_A) {

            Player.left = true;

        }
        if (key == KeyEvent.VK_S) {

            Player.down = true;

        }
        if (key == KeyEvent.VK_D) {

            Player.right = true;

        }
        // Bullets FIRE on SPACE
        if (key == KeyEvent.VK_SPACE) {

            Player.isFiring = true;

        }

        if (key == KeyEvent.VK_ESCAPE) {

            GamePanel.state = GamePanel.STATES.MENUE;

        }
        if (key == KeyEvent.VK_Q) {

            GamePanel.player.switchweapon();

        }
        if (key == KeyEvent.VK_F) {

            GameStart.panel.setFPSturn(true);

        }
        if (key == KeyEvent.VK_G) {

            GameStart.panel.setFPSdegree(true);

        }
        if (key == KeyEvent.VK_C) {

            GamePanel.player.addSpeed(true);

        }
        if (key == KeyEvent.VK_V) {

            GamePanel.player.decSpeed(true);

        }


    }


    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_W) {

            Player.up = false;

        }
        if (key == KeyEvent.VK_A) {

            Player.left = false;

        }
        if (key == KeyEvent.VK_S) {

            Player.down = false;

        }
        if (key == KeyEvent.VK_D) {

            Player.right = false;

        }

        if (key == KeyEvent.VK_SPACE) {

            Player.isFiring = false;

        }
        if (key == KeyEvent.VK_F) {

            GameStart.panel.setFPSturn(false);

        }
        if (key == KeyEvent.VK_G) {

            GameStart.panel.setFPSdegree(false);

        }
        if (key == KeyEvent.VK_C) {

            GamePanel.player.addSpeed(false);

        }
        if (key == KeyEvent.VK_V) {

            GamePanel.player.decSpeed(false);

        }


    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GamePanel.player.isFiring = true;
            GamePanel.leftMouse = true;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            GamePanel.player.isCasting = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            GamePanel.player.isFiring = false;
            GamePanel.leftMouse = false;
        }
        if (e.getButton() == MouseEvent.BUTTON3) {
            GamePanel.player.isCasting = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GamePanel.mouseX = e.getX();
        GamePanel.mouseY = e.getY();

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        // Я хз, это долбанное колесо не реагирует ни на что
        //GamePanel.player.weapontype = Player.WEAPONTYPE.SHOTGUN;

        System.out.println(e.getScrollAmount());
        System.out.println(e.getWheelRotation());
        System.out.println(e.getPreciseWheelRotation());
        System.out.println(e.getScrollType());
        System.out.println(e.getUnitsToScroll());
    }
}
