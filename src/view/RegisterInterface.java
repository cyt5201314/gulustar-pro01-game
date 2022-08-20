package view;


import service.UserService;
import service.impl.UserServiceImpl;
import view.utils.BackgroundPanel;
import view.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

/**
 * 注册界面
 */
public class RegisterInterface {
    //调用逻辑层方法的对象
    UserService userService = new UserServiceImpl();
    //创建窗口
    JFrame jf = new JFrame("咕噜渔场-注册");
    //窗口宽高
    final int WIDTH = 500;
    final int HEIGHT = 300;


    //组装视图
    public void init() throws Exception {
        //设置窗口相关的属性
        //1 窗口位于屏幕正中间
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        //2 窗口大小不变
        jf.setResizable(false);
        //3 设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));
        //设置点X关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //4 设置窗口的内容
        //4.1 设置背景图片
        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("img\\游戏场景背景3.jpeg")));

        //4.2 组装登陆相关的元素

        //4.2.1 垂直的Box
        Box vBox = Box.createVerticalBox();
        //4.2.2 水平的Box
        //4.2.2.1 组装用户名
        Box uBox = Box.createHorizontalBox();
        //用户名标签和输入栏
        JLabel uLabel = new JLabel("用户名");
        JTextField uField = new JTextField(15);
        //添加到box
        uBox.add(uLabel);
        uBox.add(Box.createHorizontalStrut(20));//设置间隔
        uBox.add(uField);
        //4.2.2.2 组装密码
        Box pBox = Box.createHorizontalBox();
        //密码标签和输入栏
        JLabel pLabel = new JLabel("密    码");
        JTextField pField = new JTextField(15);
        //添加到box
        pBox.add(pLabel);
        pBox.add(Box.createHorizontalStrut(20));//设置间隔
        pBox.add(pField);
        //4.2.2.6 组装验证码
//        Box rBox = Box.createHorizontalBox();
//        //验证码标签和输入栏和图片
//        JLabel rLabel = new JLabel("验证码：");
//        JTextField rField = new JTextField(4);
//        JLabel rImg = new JLabel("验证码图片");
//        //给验证码图片设置提示信息
//        rImg.setToolTipText("点击刷新");
//        //刷新 -鼠标事件
//        rImg.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseClicked(MouseEvent e) {
//                super.mouseClicked(e);
//                //
//                rImg.updateUI();//重绘界面
//            }
//        });

//        rBox.add(rLabel);
//        rBox.add(Box.createHorizontalStrut(20));//设置间隔
//        rBox.add(rField);
//        rBox.add(rImg);

        //4.2.2.7 组装按钮
        Box btnBox = Box.createHorizontalBox();
        //注册和返回按钮
        JButton registerBtn = new JButton("注册");
        JButton backBtn = new JButton("返回登陆页面");
        //4.2.2.4 按钮事件实现
        //1 验证注册
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //获取用户输入的数据
                String userame = uField.getText().trim();
                String password = pField.getText().trim();
//                String checkCode = rField.getText().trim();
                //调用注册方法
                String registe = userService.registe(userame, password);
                //注册成功,弹出提示
                if ("注册成功".equals(registe)){
                    JOptionPane.showMessageDialog(jf,"注册成功,请返回登录界面登录","成功",JOptionPane.INFORMATION_MESSAGE);
                }else{
                    //注册出问题，打印逻辑层返回的原因
                    JOptionPane.showMessageDialog(jf,registe,"错误",JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        //2 跳转到登陆界面
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new MainInterface().init();
                    jf.dispose();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        btnBox.add(registerBtn);
        btnBox.add(Box.createHorizontalStrut(80));//设置间隔
        btnBox.add(backBtn);
        //4.2.3 将水平行组装到垂直box
        vBox.add(Box.createVerticalStrut(50));
        vBox.add(uBox);
        vBox.add(Box.createVerticalStrut(20));
        vBox.add(pBox);
        vBox.add(Box.createVerticalStrut(20));
//        vBox.add(rBox);
//        vBox.add(Box.createVerticalStrut(20));
        vBox.add(btnBox);

        //4.3 将vBox组装到jPanel、
        bgPanel.add(vBox);
        //4.4 将JPanel组装到JFrame
        jf.add(bgPanel);

        //5 设置窗口可见
        jf.setVisible(true);


    }
}

