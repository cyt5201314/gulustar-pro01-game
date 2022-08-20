package view;

import view.utils.GuluButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class DeclarationInterface {

    //创建窗口
    JFrame jf = new JFrame("咕噜鱼塘");
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 550;

    //背景图片
    BufferedImage declaraImg;

    //说明栏
    JTextArea textArea = new JTextArea(6,30);
    //说明文
    String info = "1.注册账号\n" +
            "2.返回登录界面登录\n" +
            "3.进入游戏 初始1000积分\n 打开左下角的商城购买鱼苗\n" +
            "4.购买后打开背包  点击培养\n" +
            "5.在主界面看到鱼  \n 点击鱼显示还有多久成熟";

    private class MyDeclara extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //绘制背景
            g.drawImage(declaraImg,0,0,FRAME_WIDTH,FRAME_HEIGHT,null);
        }
    }

    //声明对象
    MyDeclara myDeclara = new MyDeclara();

    public void init(int id) throws IOException {
        //设置窗口大小
        jf.setBounds(450, 200, FRAME_WIDTH, FRAME_HEIGHT);
        //设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));

        //背景图片
        declaraImg = ImageIO.read(new File("img\\游戏场景背景1.jpeg"));

        //文字
        textArea.append(info);
        //不可编辑
        textArea.setEditable(false);
        //位置
        textArea.setBounds(300,100,400,300);
        textArea.setFont(new Font("宋体",Font.BOLD,20));

        //返回按钮
        GuluButton back = new GuluButton("img\\游戏按钮背景1.png",
                "返回鱼塘","Back", 450, 450, 100, 50);
        //绑定点击返回事件
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //返回
                try {
                    new GameInterface().init(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //关闭当前界面
                jf.dispose();
            }
        });
        jf.add(back);

        //说明文
        jf.add(textArea);
        //背景图片
        jf.add(myDeclara);
        //禁止改变窗口大小
        jf.setResizable(false);
        //设置可见
        jf.setVisible(true);
        //设置点X关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
