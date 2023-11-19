package com;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

//工具类
public class GameUtil {

    public static final int FRAME_WIDTH = 1000;
    public static final int FRAME_HIGHT = 1000;

    //构造器私有，防止外部创建对象
    private GameUtil() {
    }

    /**
     * 根据路径获取图像
     * @param path 图像路径
     * @return 图像
     */
    public static Image getImage(String path) {
        Image img = null;
        URL url = GameUtil.class.getClassLoader().getResource(path);
        try {
            img = ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }


}
