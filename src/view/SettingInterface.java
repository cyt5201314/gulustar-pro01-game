package view;

import view.utils.BackgroundPanel;
import view.utils.Music;
import view.utils.MusicThread;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

import static view.utils.Music.player;


public class SettingInterface {

    public void init(int userId) throws IOException {
        JFrame jf = new JFrame("设置");
        jf.setBounds(400, 300, 1000, 550);
        //setLayout设置布局，不然可能会出现某些内容不显示的问题
        //      jf.setLayout(new BorderLayout());
        jf.setLocationRelativeTo(null);//改为空布局
        //面板
        Container c = jf.getContentPane();

        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("img\\游戏场景背景3.jpeg")));


        //标签
        JLabel jl1 = new JLabel("游戏音乐:");
        jl1.setBounds(280,100,200,50);
        jl1.setFont(new Font("",1,30));
        c.add(jl1);

        //按钮
        JButton jb1_1 = new JButton("开");
        jb1_1.setBounds(430,100,80,50);
        jb1_1.setFont(new Font("",1,20));
        jb1_1.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //多线程
                MusicThread mt1 = new MusicThread();
                mt1.start();
            }
        });
        c.add(jb1_1);

        JButton jb1_2 = new JButton("关");
        jb1_2.setBounds(530,100,80,50);
        jb1_2.setFont(new Font("",1,20));
        jb1_2.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.close();
                Music.musicFlag = 2;
            }
        });
        c.add(jb1_2);

        //返回界面按钮
        JButton jb3 = new JButton(("返回游戏主界面"));
        Font f=new Font ("宋体",Font.BOLD,14);
        jb3.setFont(f);
        //设置背景
        Image img1 = ImageIO.read(new File("img\\游戏按钮背景1.png"));
        ImageIcon icon = new ImageIcon(img1);
        jb3.setIcon(icon);
        jb3.setHorizontalTextPosition(SwingConstants.CENTER);
        jb3.setBounds(400, 200, 150, 50);
        icon.setImage(icon.getImage().getScaledInstance(jb3.getWidth(), jb3.getHeight(), Image.SCALE_DEFAULT));//自适应大小
        jb3.setBorder(null);//设置边框
        jb3.setContentAreaFilled(false);//去白边
        //加监听
        jb3.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new GameInterface().init(userId);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                jf.dispose();
            }
        });
        c.add(jb3);

        //切换账号（返回登录界面）
        JButton jb4 = new JButton("切换账号");
        jb4.setIcon(icon);
        jb4.setFont(f);
        jb4.setHorizontalTextPosition(SwingConstants.CENTER);
        jb4.setBounds(400, 265, 150, 50);
        icon.setImage(icon.getImage().getScaledInstance(jb3.getWidth(), jb3.getHeight(), Image.SCALE_DEFAULT));//自适应大小
        jb4.setBorder(null);//设置边框
        jb4.setContentAreaFilled(false);//去白边

        jb4.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                try {
                    new MainInterface().init();//这里就换成登录界面就好了
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        c.add(jb4);


        //退出游戏
        JButton jb5 =new JButton("退出游戏");
        jb5.setIcon(icon);
        jb5.setFont(f);
        jb5.setHorizontalTextPosition(SwingConstants.CENTER);
        jb5.setBounds(400, 330, 150, 50);
        icon.setImage(icon.getImage().getScaledInstance(jb3.getWidth(), jb3.getHeight(), Image.SCALE_DEFAULT));//自适应大小
        jb5.setBorder(null);//设置边框
        jb5.setContentAreaFilled(false);//去白边
        jb5.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jf.dispose();
                player.close();
            }
        });
        c.add(jb5);


        //最后加上背景图
        jf.add(bgPanel);

        //  jf.setResizable(false);//禁止改变窗口大小
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);



    }

}

