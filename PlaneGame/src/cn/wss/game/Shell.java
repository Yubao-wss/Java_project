package cn.wss.game;

import java.awt.*;

/**
 * 炮弹类
 */

public class Shell extends GameObject{
    double degree;

    public Shell(){
        setX(50);
        setY(50);
        setWidth(10);
        setHeight(10);
        setSpeed(3);
        degree = Math.random()*Math.PI*2;

    }

    public void draw(Graphics g){
        double shellX = getX();
        double shellY = getY();

        Color c = g.getColor();
        g.setColor(Color.orange);

        g.fillOval((int)shellX,(int)shellY,getWidth(),getHeight());
        //炮弹沿着任意角度去飞
        shellX += getSpeed()*Math.cos(degree);
        setX(shellX);
        shellY += getSpeed()*Math.sin(degree);
        setY(shellY);

        if (shellX<getWidth()||shellX>(Constant.GAME_WIDTH-2*getWidth())){
            degree = Math.PI-degree;
        }

        if (shellY<30||shellY>(Constant.GAME_HEIGHT-2*getHeight())){
            degree = -degree;
        }

        g.setColor(c);
    }
}

