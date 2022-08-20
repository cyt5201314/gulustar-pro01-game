package view;

import domain.Fish;
import service.FishService;
import service.impl.FishServiceImpl;
import view.utils.GuluButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class BagInterface {

    FishService fishService = new FishServiceImpl();

    public void init(int id) throws IOException {
        JFrame jf = new JFrame("背包");
        jf.setBounds(300, 300, 1000, 550);
        //setLayout设置布局，不然可能会出现某些内容不显示的问题
        //      jf.setLayout(new BorderLayout());
        jf.setLocationRelativeTo(null);//改为空布局
        //面板
        Container c = jf.getContentPane();

        //返回界面按钮
        GuluButton back = new GuluButton("img\\游戏按钮背景1.png",
                "返回鱼塘","Back", 450, 450, 100, 50);
        //加监听
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

        //获取鱼信息
        List<Fish> fishes = fishService.getBagFish(id);
        int x = 150;
        int y = 100;
        //绘制鱼
        for(Fish fish : fishes){
            //添加鱼图标
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("img\\" + fish.getName() + ".png")));
            JLabel label = new JLabel(imageIcon);
            label.setBounds(x, y, 50, 100);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight()-15, Image.SCALE_DEFAULT));//自适应大小
            label.setBorder(null);//设置边框
            GuluButton feed = new GuluButton("img\\游戏按钮背景1.png",
                    "培养","feed", x - 20, y + 70, 100, 50);
            //加监听
            feed.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    int confirm = JOptionPane.showConfirmDialog(jf, "确定培养？");
                    if (confirm == 0){
                        int startFeeding = fishService.startFeeding(fish.getId(), id);
                        if (startFeeding == 0){
                            JOptionPane.showMessageDialog(jf,"鱼塘容量已满！","错误",JOptionPane.ERROR_MESSAGE);
                        }
                        try {
                            init(id);
                        } catch (IOException ex) {
                            ex.printStackTrace();
                        }
                        jf.dispose();
                    }
                }
            });
            if (x <= 600){
                x += 150;
            }else {
                x = 150;
                y += 150;
            }
            jf.add(label);
            jf.add(feed);
        }

        //背景图片
        JLayeredPane lp1 = new JLayeredPane();
        JLabel jl0 = new JLabel();//创建一个标签对象
        ImageIcon icon = new ImageIcon("img\\游戏场景背景1.jpeg");//创建背景图片
        jl0.setIcon(icon);
        jl0.setBounds(0, 0, 1000, 550);//设置图像的位置和大小

        //最后在JF里加上背景图
        jf.add(jl0);
        jf.setResizable(false);//禁止改变窗口大小back
        jf.setVisible(true);
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
