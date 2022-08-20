package view;

import domain.Fish;
import domain.Item;
import domain.User;
import service.FishService;
import service.UserService;
import service.impl.FishServiceImpl;
import service.impl.UserServiceImpl;
import view.utils.GuluButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class ShopInterface {

    UserService userService = new UserServiceImpl();
    FishService fishService = new FishServiceImpl();

    //创建窗口
    JFrame jf = new JFrame("商城");
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 550;

    //背景图片
    BufferedImage shop;

    private class MyShop extends JPanel{
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //绘制背景
            g.drawImage(shop,0,0,FRAME_WIDTH,FRAME_HEIGHT,null);
        }
    }

    //创建画布对象
    MyShop myShop = new MyShop();

    public void init(int id) throws IOException {
        //设置窗口大小
        jf.setBounds(450,200,FRAME_WIDTH, FRAME_HEIGHT);
        //设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));
        //设置背景图片
        shop = ImageIO.read(new File("img\\游戏场景背景1.jpeg"));

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

        //查找用户
        User user = userService.getUserById(id);
        //积分
        JLabel pointLabel = new JLabel("积分:" + user.getPoints());
        pointLabel.setBounds(50, 0, 100, 50);
        pointLabel.setFont(new Font("宋体",Font.BOLD,20));
        jf.add(pointLabel);

        //商城商品
        List<Fish> onSaleFishes = fishService.getOnSaleFish();
        int x = 150;
        int y = 150;
        for(Fish fish : onSaleFishes){
            //添加鱼图标
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("img\\" + fish.getName() + ".png")));
            JLabel label = new JLabel(imageIcon);
            label.setBounds(x, y, 100, 100);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight()-15, Image.SCALE_DEFAULT));//自适应大小
            label.setBorder(null);//设置边框
            //购买按钮
            GuluButton button = new GuluButton("img\\游戏按钮背景1.png",
                    "购买",fish.getName() + fish.getPrice() + "积分", x + 20,y + 80,60,30);
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(jf, "确定购买？");
                    if (confirm == 0){
                        int buy = fishService.buyFish(fish.getType(), id);
                        //买不起
                        if (buy == 0){
                            JOptionPane.showMessageDialog(jf,"余额不足","错误",JOptionPane.ERROR_MESSAGE);
                        }else {
                            //买得起
                            JOptionPane.showMessageDialog(jf,"购买成功");
                            //重新加载 为了刷新积分
                            try {
                                new ShopInterface().init(id);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //关闭当前界面
                            jf.dispose();
                        }
                    }
                }
            });
            if (x <= 600){
                x += 150;
            }else {
                x = 150;
                y += 100;
            }
            jf.add(label);
            jf.add(button);
        }

        //添加按钮
        jf.add(back);
        //添加背景
        jf.add(myShop);
        //设置窗口可见
        jf.setVisible(true);
        //禁止改变窗口大小
        jf.setResizable(false);
        //设置点X关闭
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
