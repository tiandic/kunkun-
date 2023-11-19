package com;

import java.awt.*;

//爆炸类
public class Explode {
    static Image[] imqs = new Image[8];

    static {
        for (int i = 1; i < 10; i++) {
            imqs[i] = GameUtil.getImage("images/explode/90.jpg" + (i + 1) + ".jpg");
            //懒加载
            imqs[i].getWidth(null);
        }
    }

    double x, y;
    int count;
    boolean live = true;

    public Explode(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        if (!live) {
            return;
        }
        if (count <= 8) {
            g.drawImage(imqs[count], (int) x, (int) y, null);
            count++;
        } else {
            live = false;
        }
    }
}
