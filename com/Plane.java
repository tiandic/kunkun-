package com;

import java.awt.*;
import java.awt.event.KeyEvent;

// kunkun类
public class Plane extends GameObject {
    boolean left, up, right, down;
    boolean live = true;

    /**
     * 构造函数
     * @param img  kunkun的图像
     * @param x  kunkun的初始x坐标
     * @param y  kunkun的初始y坐标
     * @param speed  kunkun的移动速度
     */
    public Plane(Image img, int x, int y, int speed) {
        super(img, x, y, speed);
    }

    @Override
    public void drawMySelf(Graphics g) {

        if (live != true) {
            return;
        }

        super.drawMySelf(g);

        // 移动飞机
        if (left) {
            x -= speed;
        }
        if (right) {
            x += speed;
        }
        if (up) {
            y -= speed;
        }
        if (down) {
            y += speed;
        }

    }

    /**
     * 添加方向键的监听
     * @param e 方向键事件
     */
    public void addDirection(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
            case KeyEvent.VK_UP:
                up = true;
                break;
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
    }

    /**
     * 减去方向键的监听
     * @param e 方向键事件
     */
    public void minusDirection(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
            case KeyEvent.VK_UP:
                up = false;
                break;
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
    }
}
