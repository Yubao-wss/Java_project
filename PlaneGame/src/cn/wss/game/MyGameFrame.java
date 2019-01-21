package cn.wss.game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

/**
 * 游戏主窗口
 * wss
 * 2019-1-20
 */

public class MyGameFrame extends JFrame {

    Image background = GameUtil.getImage("Image/background.png");
    Image planeImg = GameUtil.getImage("Image/airplane.png");

    Plane plane = new Plane(planeImg,150,350);
    Shell[] shells = new Shell[5];

    Explode explode ;

    Date startTime = new Date();
    Date endTime;
    int period;//游戏持续的时间

    @Override
    public void paint(Graphics g) {//自动被调用，g相当于画笔
        /*super.paint(g);*/
        Color c=g.getColor();
        Font f=g.getFont();
        g.setColor(Color.cyan); //画笔颜色及字体的修改，要提前保存原始颜色

        g.drawImage(background,0,0,null);

        plane.drawSelf(g);

        //画出所有炮弹
        for (int i=0;i<shells.length;i++){
            shells[i].draw(g);

            //检测碰撞
            boolean collision = shells[i].getRect().intersects(plane.getRect());
            if(collision){
                plane.live=false;
                if(explode==null){
                    explode = new Explode(plane.getX(),plane.getY());

                    endTime = new Date();
                    period=(int)((endTime.getTime()-startTime.getTime())/1000);
                }

                explode.draw(g);
            }
        }
        //计时功能
        if(!plane.live){
            g.setColor(Color.red);

            g.drawString("时间："+period+"秒",150,350);

        }
        g.setColor(c);
        g.setFont(f);  //使用完后，要将颜色还原



    }
    //反复重画窗口
    class PaintThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                repaint();//重画

                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //定义键盘监听的内部类
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);
        }
    }




    /**
     * 初始化窗口
     */
    public void launchFrame(){
        this.setTitle("无敌小飞机");
        this.setVisible(true);
        this.setSize(Constant.GAME_WIDTH,Constant.GAME_HEIGHT);
        this.setLocation(700,300);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });//关闭窗口时，结束程序

        new PaintThread().start();//启动重画窗口的线程
        addKeyListener(new KeyMonitor());//给窗口增加键盘的监听

        //初始化50个炮弹
        for (int i=0;i<shells.length;i++){
            shells[i] = new Shell();
        }
    }

    public static void main(String[] args) {
        MyGameFrame f=new MyGameFrame();
        f.launchFrame();

    }
}
