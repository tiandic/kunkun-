package com;

import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

import static com.GameUtil.FRAME_HIGHT;
import static com.GameUtil.FRAME_WIDTH;

public class bjsxt extends Frame {
    public Image offScreenImage = null;  // 偏离屏幕图像
    Image bgImg = GameUtil.getImage("images/w.png");  // 背景图像
    Image planeImg = GameUtil.getImage("images/wu.jpg");  // 飞机图像
    Plane plane = new Plane(planeImg, 300, 300, 20);  // 飞机对象
    shell[] shells = new shell[100];  // kunkun数组
    Explode explode;  // 声爆对象
    Date startTime = new Date();  // 开始时间
    Date endTime;  // 结束时间
    int period;  // 玩了多少秒
    int i1 = 0;
    int ji = 0;
    int jiao = 0;
    long currentTime = Calendar.getInstance().getTimeInMillis();  // 当前时间
    int sb = period;  // 防止期初值被刷新
    String dizhi = "src/Sound/";  // 音频文件路径

    public static void main(String[] args) {
        System.out.println(System.getProperty("user.dir"));
        bjsxt frame = new bjsxt();
        frame.launchFrame();

    }

    public static void playSound(String path) {
        File soundFile = new File(path);
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(bgImg, 0, 0, FRAME_WIDTH, FRAME_HIGHT, null);  // 绘制背景图像

        plane.drawMySelf(g);  // 绘制kunkun

        for (int i = 0; i < shells.length; i++) {
            if (shells[i] != null) {
                shells[i].drawMySelf(g);  // 绘制篮球

                boolean peng = shells[i].getRec().intersects(plane.getRec());  // 判断是否命中

                if (peng) {
                    plane.live = false;
                    endTime = new Date();  // 结束时间

                    period = (int) ((endTime.getTime() - startTime.getTime()) / 1000);  // 计算游戏时间

                    if (explode == null) {
                        explode = new Explode(plane.x, plane.y);  // 创建爆炸效果
                    }
                    explode.draw(g);  // 绘制爆炸效果

                }

            }
        }

        if (plane.live == false) {
            if (jiao <= 10) {
                playSound(dizhi + "ngm.wav");  // 播放音效
                jiao++;
            }
            while (i1 < 10) {
                i1++;
                if (i1 == 5) {
                    sb = period;  // 计算游戏时间
                    break;

                }

            }
            printInfo(g, "游戏时间：" + sb + "秒", 40, 150, 250, Color.white);  // 显示游戏时间
        }

    }

    public void printInfo(Graphics g, String str, int size, int x, int y, Color color) {
        Font oldFont = g.getFont();
        Color oldColor = g.getColor();

        Font f = new Font("宋体", Font.BOLD, size);
        g.setFont(f);
        g.setColor(color);
        g.drawString(str, x, y);

        g.setFont(oldFont);
        g.setColor(oldColor);

    }

    // 初始化窗口
    public void launchFrame() {
        this.setTitle("kunkun躲");  // 设置窗口标题
        this.setVisible(true);  // 窗口可见
        this.setSize(1000, 1000);  // 设置窗口大小
        this.setLocation(0, 0);

        // 窗口关闭
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);

            }

        });

        // 启动窗口绘画线程
        new PaintThread().start();

        // 启动键盘监听
        this.addKeyListener(new KeyMonitor());

        // 初始化子弹
        for (int i = 0; i < shells.length; i++) {
            shells[i] = new shell();
        }
    }

    public void update(Graphics g) {
        if (offScreenImage == null)
            offScreenImage = this.createImage(FRAME_WIDTH, FRAME_HIGHT);

        Graphics gOff = offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage, 0, 0, null);


    }

    // 键盘监听内部类
    class KeyMonitor extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            plane.addDirection(e);  // 给飞机添加方向
        }

        @Override
        public void keyReleased(KeyEvent e) {
            plane.minusDirection(e);  // 给飞机减去方向
        }
    }

    // 重画线程
    class PaintThread extends Thread {
        @Override
        public void run() {
            while (true) {
                repaint();
                if (ji == 0 || Calendar.getInstance().getTimeInMillis() - currentTime >= 60000) {
                    playSound(dizhi + "ji.wav");  // 播放音效
                    ji++;
                    currentTime = Calendar.getInstance().getTimeInMillis();
                }
                try {
                    Thread.sleep(40);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
