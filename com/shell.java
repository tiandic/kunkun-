package com;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class shell extends GameObject {
    private Image image;  // 篮球图像
    int c = 8;  // 篮球速度
    double degree;  // 篮球旋转角度

    public shell() {
        degree = Math.random() * Math.PI * 2;  // 随机生成初始旋转角度
        x = 100;  // 设置初始横坐标
        y = 100;  // 设置初始纵坐标
        width = 5;  // 篮球宽度
        height = 5;  // 篮球高度
        speed = c;  // 设置初始速度

        // 加载篮球图像
        image = GameUtil.getImage("images/a0.png");
    }

    @Override
    public void drawMySelf(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        AffineTransform oldTransform = g2d.getTransform();

        // 绘制篮球图像
        g2d.translate(x, y);  // 平移坐标
        g2d.rotate(degree);  // 旋转坐标
        g2d.drawImage(image, -width/2, -height/2, width*3, height*3, null);  // 绘制篮球图像

        g2d.setTransform(oldTransform);

        x += speed * Math.cos(degree);  // 根据速度和旋转角度更新横坐标
        y += speed * Math.sin(degree);  // 根据速度和旋转角度更新纵坐标

        // 实现边界碰撞回弹
        if (y > GameUtil.FRAME_HIGHT - 10 || y < 30) {
            degree = -degree;
        }
        if (x > GameUtil.FRAME_WIDTH - 10 || x < 0) {
            degree = Math.PI - degree;
        }
    }
}
