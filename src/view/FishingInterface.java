package view;

import domain.Fish;
import domain.User;
import service.BagService;
import service.FishingSeivice;
import service.UserService;
import service.impl.BagServiceImpl;
import service.impl.FishingServiceImpl;
import service.impl.UserServiceImpl;
import view.utils.BackgroundPanel;
import view.utils.GuluButton;
import view.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

public class FishingInterface {
    //调用逻辑层方法的对象
    static UserService userService = new UserServiceImpl();
    static BagService bagService = new BagServiceImpl();
    static FishingSeivice fishingSeivice =  new FishingServiceImpl();

    //创建窗口
    JFrame jf = new JFrame("大航海");
    final int WIDTH = 1000;
    final int HEIGHT = 550;
    int id;
    String[] fishes = new String[12];

    public void init(int userId) throws Exception {
        id = userId;

        //设置窗口相关的属性
        //1 窗口位于屏幕正中间
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        //2 窗口大小不变
        jf.setResizable(false);
        //3 设置窗口图标
        jf.setIconImage(ImageIO.read(new File("gulufishing\\img\\游戏图标.png")));
        BackgroundPanel bgPanel = new BackgroundPanel(ImageIO.read(new File("gulufishing\\img\\大航海.png")));
        Box verticalBox = Box.createVerticalBox();

        //查找用户
        User user = userService.getUserById(id);
        //积分
        JLabel pointLabel = new JLabel("积分:" + user.getPoints());
        pointLabel.setBounds(50, 0, 100, 50);
        pointLabel.setFont(new Font("宋体", Font.BOLD, 20));
        jf.add(pointLabel);

        //按钮1
        Box horizontalBox1 = Box.createHorizontalBox();
        Font font = new Font("楷体", Font.ITALIC, 25);
        GuluButton btn1 = new GuluButton("欢迎来到大航海",
                "近海安全，但是远航收获更多哦", 20, 20, 500, 300, font);
        btn1.setLayout(new FlowLayout(FlowLayout.CENTER));
        horizontalBox1.add(btn1);
        //按钮2-3
        Box horizontalBox2 = Box.createHorizontalBox();
        GuluButton btn2 = new GuluButton("gulufishing\\img\\游戏按钮2.png", "近海捕捞",
                "需要200积分", 300, 150, 120, 50, font);
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int confirm = JOptionPane.showConfirmDialog(jf, "需要消耗200积分，确定出海？");
                if (confirm == 0) {
                    //买不起
                    if (user.getPoints() < 200 ) {
                        JOptionPane.showMessageDialog(jf, "余额不足", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //买得起

                        fishingSeivice.reducePoints(id);
                        String fish = fishingSeivice.luckyFishing(id);
                        JOptionPane.showMessageDialog(jf, "出海成功！\n本次航海收获："+fish);
                        //重新加载 为了刷新积分
                        jf.dispose();
                        try {
                            init(id);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });


        GuluButton btn3 = new GuluButton("gulufishing\\img\\游戏按钮2.png", "远航",
                "需要2000积分", 300, 150, 120, 50, font);
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(jf, "需要消耗2000积分，确定出海？");
                if (confirm == 0) {
                    //买不起
                    if (user.getPoints() < 2000 ) {
                        JOptionPane.showMessageDialog(jf, "余额不足", "错误", JOptionPane.ERROR_MESSAGE);
                    } else {
                        //买得起
                        for (int i = 0; i < 10; i++) {
                            fishingSeivice.reducePoints(id);
                        }
                        for (int i = 0; i < 12; i++) {
                            String fish = fishingSeivice.luckyFishing(id);
                            fishes[i] =fish;
                        }
                        JOptionPane.showMessageDialog(jf, "出海成功！\n本次航海收获：\n"+ Arrays.toString(fishes));
                        //重新加载 为了刷新积分
                        jf.dispose();
                        try {
                            new FishingInterface().init(id);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });


        horizontalBox2.add(btn2);
        horizontalBox2.add(Box.createHorizontalStrut(200));
        horizontalBox2.add(btn3);

        //按钮4
        Box horizontalBox3 = Box.createHorizontalBox();
        GuluButton back = new GuluButton("gulufishing\\img\\游戏按钮背景1.png",
                "返回鱼塘", "Back", 0, 0, 80, 40);
        back.setFont(new Font("宋体", Font.BOLD, 12));
        // 跳转到鱼塘界面
        back.addActionListener(new ActionListener() {
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
        horizontalBox3.add(back);

        verticalBox.add(horizontalBox1);
        verticalBox.add(Box.createVerticalStrut(250));
        verticalBox.add(horizontalBox2);
        verticalBox.add(Box.createVerticalStrut(100));
        verticalBox.add(horizontalBox3);
        bgPanel.add(verticalBox);

        jf.add(bgPanel);
        //6 设置窗口可见
        jf.setVisible(true);
        //设置点X关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws Exception {
        new FishingInterface().init(1);
    }

}
