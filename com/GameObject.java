package com;

import java.awt.*;

//游戏物体的根类
public class GameObject {
    Image img;  //对应照片
    int x, y;    //坐标
    int speed;  //物体移动速度
    int width, height;   //物体的宽和高

    /**
     * 无参构造方法
     */
    public GameObject() {
    }

    /**
     * 带参构造方法
     * @param img 对应的照片
     * @param x 坐标x
     * @param y 坐标y
     * @param speed 物体移动速度
     * @param width 物体宽度
     * @param height 物体高度
     */
    public GameObject(Image img, int x, int y, int speed, int width, int height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.width = width;
        this.height = height;
    }

    /**
     * 三个int参数构造方法
     * @param img 对应的照片
     * @param x 坐标x
     * @param y 坐标y
     * @param speed 物体移动速度
     */
    public GameObject(Image img, int x, int y, int speed) {
        this(img, x, y);
        this.speed = speed;
    }

    /**
     * 两个int参数构造方法
     * @param img 对应的照片
     * @param x 坐标x
     * @param y 坐标y
     */
    public GameObject(Image img, int x, int y) {
        this(img);
        this.x = x;
        this.y = y;
    }

    /**
     * 一个int参数构造方法
     * @param img 对应的照片
     */
    public GameObject(Image img) {
        this.img = img;
        if (this.img != null) {
            this.width = img.getWidth(null);
            this.height = img.getHeight(null);
        }
    }

    /**
     * 绘制自己
     * @param g 绘制上下文
     */
    public void drawMySelf(Graphics g) {
        g.drawImage(img, x, y, width, height, null);
    }

    /**
     * 返回物体对应的矩形
     * @return 物体对应的矩形
     */
    public Rectangle getRec() {
        return new Rectangle(x, y, width, height);
    }
}
