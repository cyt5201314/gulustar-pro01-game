package view;


import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import view.utils.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 登录界面
 */
public class MainInterface {
    //调用逻辑层方法的对象
    UserService userService = new UserServiceImpl();

    //创建窗口
    JFrame jf = new JFrame("咕噜渔场");
    //窗口宽高
    final int WIDTH = 500;
    final int HEIGHT = 300;


    //组装视图
    public void init() throws Exception {
        //设置窗口相关的属性
        //1 窗口位于屏幕正中间
        jf.setBounds((ScreenUtils.getScreenWidth()-WIDTH)/2,( ScreenUtils.getScreenHeight()-HEIGHT)/2,WIDTH,HEIGHT);
        //2 窗口大小不变
        jf.setResizable(false);
        //3 设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));
        //设置点X关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //4 设置窗口的内容
        //4.1 设置背景图片
        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("img\\登录背景.jpeg")));

        //4.2 组装登陆相关的元素

        //4.2.1 垂直的Box
        Box vBox = Box.createVerticalBox();
        //4.2.2 水平的Box
        //4.2.2.1 组装用户名
        Box uBox = Box.createHorizontalBox();
        //用户名标签和输入栏
        JLabel uLabel = new JLabel("用户名");
        JTextField uField = new JTextField(15);
        //添加到水平box
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));//设置间隔
        uBox.add(uField);
        //4.2.2.2 组装密码
        Box pBox = Box.createHorizontalBox();
        //密码标签和输入栏
        JLabel pLabel = new JLabel("密    码");
        JTextField pField = new JTextField(15);
        //添加到BOX
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));//设置间隔
        pBox.add(pField);
        //4.2.2.3 组装按钮
        Box btnBox = Box.createHorizontalBox();
        GuluButton loginBtn = new GuluButton("img\\游戏按钮背景1.png",
                "登      录","Login", 0, 0, 70, 30);
        GuluButton registerBtn = new GuluButton("img\\游戏按钮背景1.png",
                "注      册", "Register", 0, 0, 70, 30);


        //4.2.2.4 按钮事件实现
        //1 验证登陆，跳转到主界面
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String userame = uField.getText().trim();
                String password = pField.getText().trim();
                //调用登录方法
                User login = userService.login(userame, password);
                //登录成功跳转到游戏界面
                if (login != null){
                    try {
                        //先弹出登录成功
                        JOptionPane.showMessageDialog(jf,"登录成功","成功",JOptionPane.INFORMATION_MESSAGE);
                        //传入user id 进入指定用户鱼塘
                        new GameInterface().init(login.getId());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    jf.dispose();
                }else {
                    //登录失败 提示用户名或密码错误
                    JOptionPane.showMessageDialog(jf,"用户名或密码错误","错误",JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        //2 跳转到注册界面
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new RegisterInterface().init();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

                //当前界面消失
                jf.dispose();
            }
        });
        //把登录和注册按钮添加到BOX
        btnBox.add(loginBtn);
        btnBox.add(Box.createHorizontalStrut(100));//设置间隔
        btnBox.add(registerBtn);
        //4.2.3 将水平行组装到垂直box
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(40));
        vBox.add(btnBox);

        //4.3 将vBox组装到jPanel、
        bgPanel.add(vBox);
        //4.4 将JPanel组装到JFrame
        jf.add(bgPanel);

        //5 设置窗口可见
        jf.setVisible(true);


    }
    //程序的入口
    public static void main(String[] args) throws Exception {
        new MainInterface().init();
        //多线程
        if (Music.musicFlag == 1) {
            Music.musicFlag = 2;
            MusicThread mt1 = new MusicThread();
            mt1.start();
        }
    }

}

