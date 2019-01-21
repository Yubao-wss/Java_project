package cn.wss.game;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Plane extends GameObject{
    boolean left,up,right,down;
    boolean live = true;
    public void drawSelf(Graphics g) {
        if (live) {
            double planeX = getX();
            double planeY = getY();
            g.drawImage(getImg(), (int) planeX, (int) planeY, null);
            /*setX(++planeX);*/

            if (left) {
                planeX -= getSpeed();
                setX(planeX);
            }
            if (right) {
                planeX += getSpeed();
                setX(planeX);
            }
            if (up) {
                planeY -= getSpeed();
                setY(planeY);
            }
            if (down) {
                planeY += getSpeed();
                setY(planeY);
            }
            if (planeX>Constant.GAME_WIDTH-30){
                planeX = Constant.GAME_WIDTH-30;
                setX(planeX);
            }
            if (planeX<-40){
                planeX = -40;
                setX(planeX);
            }
            if (planeY>Constant.GAME_HEIGHT-30){
                planeY = Constant.GAME_HEIGHT-30;
                setY(planeY);
            }
            if (planeY<16){
                planeY = 16;
                setY(planeY);
            }
        }else{

        }
    }


    //按下某个键，增加相应的方向
    public void addDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=true;
                break;
            case KeyEvent.VK_UP:
                up=true;
                break;
            case KeyEvent.VK_RIGHT:
                right=true;
                break;
            case KeyEvent.VK_DOWN:
                down=true;
                break;
            default:
                break;
        }
    }

    //按下某个键，取消相应的方向
    public void minusDirection(KeyEvent e){
        switch (e.getKeyCode()){
            case KeyEvent.VK_LEFT:
                left=false;
                break;
            case KeyEvent.VK_UP:
                up=false;
                break;
            case KeyEvent.VK_RIGHT:
                right=false;
                break;
            case KeyEvent.VK_DOWN:
                down=false;
                break;
            default:
                break;
        }
    }


    public Plane(Image img, double x, double y) {
        super(img, x, y);
        setSpeed(4);
        setWidth(img.getWidth(null));
        setHeight(img.getHeight(null));
    }
}
