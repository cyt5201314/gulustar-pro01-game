package view.utils;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    //声明图片
    Image backIcon;
    public BackgroundPanel(Image backIcon) {
        this.backIcon = backIcon;
    }
    //重写paint方法
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backIcon,0,0,this.getWidth(),this.getHeight(),null);
    }
}


