package view;


import domain.Fish;
import domain.User;
import service.FishPondService;
import service.FishService;
import service.UserService;
import service.impl.FishPondServiceImpl;
import service.impl.FishServiceImpl;
import service.impl.UserServiceImpl;
import view.utils.GuluButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 游戏主体界面
 */
public class GameInterface {

    //创建service
    FishService fishService = new FishServiceImpl();
    FishPondService fishPondService = new FishPondServiceImpl();
    UserService userService = new UserServiceImpl();

    //创建窗口
    JFrame jf = new JFrame("咕噜鱼塘");
    final int FRAME_WIDTH = 1000;
    final int FRAME_HEIGHT = 550;

    //背景图片
    BufferedImage pond;

    //确定现在是哪个用户的鱼塘
    int id;

    //初始鱼坐标
    int x = 150;
    int y = 150;

    //鱼游的速度
    int xSpeed = 5;
    int ySpeed = 5;

    //创建画布,重写paint方法
    private class GuluFishing extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            //绘制背景
            g.drawImage(pond, 0, 0, FRAME_WIDTH, FRAME_HEIGHT, null);
        }
    }

    //创建背景对象
    GuluFishing guluFishing = new GuluFishing();


    public void init(int userId) throws IOException {
        //指定用户鱼塘
        id = userId;
        //设置窗口大小
        jf.setBounds(450, 200, FRAME_WIDTH, FRAME_HEIGHT);
        //设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));

        //设置背景图片
        pond = ImageIO.read(new File("img\\游戏场景背景1.jpeg"));

        //查找用户
        User user = userService.getUserById(id);
        //积分
        JLabel pointLabel = new JLabel("积分:" + user.getPoints());
        pointLabel.setBounds(50, 0, 100, 50);
        pointLabel.setFont(new Font("宋体",Font.BOLD,20));

        //等级
        JLabel lvLabel = new JLabel("等级" + user.getLevel());
        lvLabel.setBounds(200,0,100,50);
        lvLabel.setFont(new Font("宋体",Font.BOLD,20));

        //用户名
        JLabel nameLabel = new JLabel("用户：" + user.getUsername());
        nameLabel.setBounds(300,0,150,50);
        nameLabel.setFont(new Font("宋体",Font.BOLD,20));

        GuluButton setting = new GuluButton("img\\游戏设置.png"
                , "Setting", 670, 0, 75, 75);
        //跳转到游戏设置页面
        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SettingInterface().init(id);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                jf.dispose();
            }
        });
        //排行榜
        GuluButton jb3 = new GuluButton("img\\游戏商城.png"
                , "LeaderBoard", 770, 0, 75, 75);
        //跳转到排行榜页面
        jb3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new LeaderboardInterface().init(id);
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                jf.dispose();
            }
        });

        //游戏说明
        GuluButton info = new GuluButton("img\\游戏说明.png"
                , "Declaration", 870, 0, 75, 75);
        //绑定事件
        info.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //打开说明
                try {
                    new DeclarationInterface().init(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //关闭当前界面
                jf.dispose();
            }
        });

        //左下角按钮
        //出海打鱼
        GuluButton lucky = new GuluButton("img\\游戏出海捕鱼.png"
                , "Fishing", 20, 415, 85, 85);
        //绑定点击事件
        lucky.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //打开背包
                try {
                    new FishingInterface().init(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //关闭当前界面
                jf.dispose();
            }
        });

        //背包
        GuluButton bag = new GuluButton("img\\游戏背包.png"
                , "Bag", 100, 415, 75, 75);
        //绑定点击事件
        bag.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //打开背包
                try {
                    new BagInterface().init(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //关闭当前界面
                jf.dispose();
            }
        });
        //商城
        GuluButton shopping = new GuluButton("img\\游戏商城.png"
                , "Shopping", 200, 415, 75, 75);
        //绑定鼠标点击打开商城
        shopping.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //打开商城
                try {
                    new ShopInterface().init(id);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //关闭当前界面
                jf.dispose();
            }
        });

        //添加到窗口
        guluFishing.add(lucky);
        guluFishing.add(pointLabel);
        guluFishing.add(lvLabel);
        guluFishing.add(nameLabel);
        guluFishing.add(setting);
        guluFishing.add(jb3);
        guluFishing.add(info);
        guluFishing.add(bag);
        guluFishing.add(shopping);


        //获取鱼信息
        List<Fish> fishes = fishService.getFishPond(id);

        //获取鱼塘容量
        int capacity = fishPondService.getCapacity(id);
        JLabel capacityLabel = new JLabel("容量:" + fishes.size() + "/" + capacity);
        capacityLabel.setBounds(100, 30, 100, 100);
        capacityLabel.setFont(new Font("宋体",Font.BOLD,20));
        jf.add(capacityLabel);

        //绘制鱼
        //遍历
        for (Fish fish : fishes) {
            //进度条初始化
            JProgressBar jpb;
            //添加鱼图标
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("img\\" + fish.getName() + ".png")));
            JLabel label = new JLabel(imageIcon);
            //设置位置
            label.setBounds(x, y, 100, 100);
            //自适应大小
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight() - 15, Image.SCALE_DEFAULT));
            label.setBorder(null);//设置边框
            //还有多久成熟
            String time = fishService.getRemainMatureTime(fish.getId());
            //成熟了，进度条100
            if ("0".equals(time)) {
                jpb = new JProgressBar();
                jpb.setBounds(x,y-30,100,20);
                jpb.setStringPainted(true);//显示进度百分比
                jpb.setMinimum(0);//设置最小进度值
                jpb.setMaximum(100);//设置最大进度值
                jpb.setValue(100);//设置当前进度值
                jf.add(jpb);
            }else{
                //没成熟，计算进度条
                //总成熟时间
                Time matureTime = fish.getMatureTime();
                //全部转换成long
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date = null;
                try {
                    //转换成date
                    date = format.parse(time);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                //转换成秒数
                long time1 = date.getTime() + 32400000l;
                long time2 = matureTime.getTime() + 32400000l;
                //设置进度条
                jpb = new JProgressBar();
                jpb.setBounds(x,y-30,100,20);
                jpb.setStringPainted(true);//显示进度百分比
                jpb.setMinimum(0);//设置最小进度值
                jpb.setMaximum(100);//设置最大进度值
                double percent = 100 * (time2 - time1) / time2;//算出当前进度
                jpb.setValue((int)percent);//设置当前进度值
                jf.add(jpb);
            }
            //每次往右偏移
            if (x <= 600) {
                x += 150;
            } else {
                x = 150;
                y += 150;
            }
            //不停刷新鱼坐标
            ActionListener task = new ActionListener() {
                int curX = label.getX();
                int curY = label.getY();

                @Override
                public void actionPerformed(ActionEvent e) {
                    //调整坐标
                    if (curX <= 0 || curX >= FRAME_WIDTH - 200) {
                        xSpeed = -xSpeed;
                    }
                    if (curY <= 0 || curY >= 250) {
                        ySpeed = -ySpeed;
                    }
                    curX += xSpeed;
                    curY += ySpeed;
                    //重新设置鱼的位置
                    label.setBounds(curX, curY, 100, 100);
                    jpb.setBounds(curX, curY - 30, 100, 20);
                    guluFishing.repaint();
                }
            };
            //计时器监听
            Timer timer = new Timer(100, task);
            timer.start();
            //绑定鼠标点击
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //获取还有多久成熟
                    String time = fishService.getRemainMatureTime(fish.getId());
                    //暂停
                    timer.stop();
                    //判断是否成熟
                    if ("0".equals(time)) {
                        //时间是0  说明成熟了
                        int confirmHarvest = JOptionPane.showConfirmDialog(jf, "成熟了，要收获吗");
                        if (confirmHarvest == 0) {
                            fishService.setMature(fish.getId());
                            //收获
                            fishService.fishHarvesting(fish);
                            JOptionPane.showMessageDialog(jf, "获得" + fish.getExp() + "经验," + fish.getSell() + "积分", "成功", JOptionPane.INFORMATION_MESSAGE);
                            //刷新一下
                            //返回
                            try {
                                new GameInterface().init(id);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //关闭当前界面
                            jf.dispose();
                        }
                    } else {
                        //没成熟
                        JOptionPane.showMessageDialog(jf,"还有" + time + "成熟");
                    }
                    //继续游泳
                    timer.start();
                }
            });
            guluFishing.add(label);
        }

        guluFishing.setLayout(null);
        //添加背景
        jf.add(guluFishing);
        //禁止改变窗口大小
        jf.setResizable(false);
        //设置可见
        jf.setVisible(true);
        //设置点X关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * 访问他人鱼塘的方法
     *
     * @param userId
     * @param visitorId
     * @throws IOException
     */
    public void init(int userId, int visitorId) throws IOException {
        //访问自己鱼塘
        if (userId == visitorId) {
            init(visitorId);
            return;
        }
        //指定用户鱼塘
        id = userId;
        //设置窗口大小
        jf.setBounds(450, 200, FRAME_WIDTH, FRAME_HEIGHT);
        //设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));

        //设置背景图片
        pond = ImageIO.read(new File("img\\游戏场景背景1.jpeg"));

        //查找用户
        User user = userService.getUserById(id);
        //来访者
        User visitor = userService.getUserById(visitorId);

        //积分
        JLabel pointLabel = new JLabel("积分:" + visitor.getPoints());
        pointLabel.setBounds(50, 0, 100, 50);
        pointLabel.setFont(new Font("宋体", Font.BOLD, 20));

        //等级
        JLabel lvLabel = new JLabel("等级" + visitor.getLevel());
        lvLabel.setBounds(200, 0, 100, 50);
        lvLabel.setFont(new Font("宋体", Font.BOLD, 20));

        //用户名
        JLabel nameLabel = new JLabel(user.getUsername() + "的鱼塘");
        nameLabel.setBounds(300, 0, 200, 50);
        nameLabel.setFont(new Font("宋体", Font.BOLD, 20));

        GuluButton setting = new GuluButton("img\\游戏设置.png"
                , "Setting", 670, 0, 75, 75);
        //跳转到游戏设置页面
        setting.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new SettingInterface().init(id);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                jf.dispose();
            }
        });

        //添加到窗口
        guluFishing.add(pointLabel);
        guluFishing.add(lvLabel);
        guluFishing.add(nameLabel);
        guluFishing.add(setting);

        //获取鱼信息
        List<Fish> fishes = fishService.getFishPond(id);

        //获取鱼塘容量
        int capacity = fishPondService.getCapacity(id);
        JLabel capacityLabel = new JLabel("容量:" + fishes.size() + "/" + capacity);
        capacityLabel.setBounds(100, 30, 100, 100);
        capacityLabel.setFont(new Font("宋体", Font.BOLD, 20));
        guluFishing.add(capacityLabel);

        //绘制鱼
        //遍历
        for (Fish fish : fishes) {
            //进度条初始化
            JProgressBar jpb;
            //添加鱼图标
            ImageIcon imageIcon = new ImageIcon(ImageIO.read(new File("img\\" + fish.getName() + ".png")));
            JLabel label = new JLabel(imageIcon);
            label.setBounds(x, y, 100, 100);
            imageIcon.setImage(imageIcon.getImage().getScaledInstance(label.getWidth(), label.getHeight() - 15, Image.SCALE_DEFAULT));//自适应大小
            label.setBorder(null);//设置边框
            //还有多久成熟
            String time = fishService.getRemainMatureTime(fish.getId());
            //成熟了，进度条100
            if ("0".equals(time)) {
                jpb = new JProgressBar();
                jpb.setBounds(x, y - 30, 100, 20);
                jpb.setStringPainted(true);//显示进度百分比
                jpb.setMinimum(0);//设置最小进度值
                jpb.setMaximum(100);//设置最大进度值
                jpb.setValue(100);//设置当前进度值
                guluFishing.add(jpb);
            } else {
                //没成熟，计算进度条
                //总成熟时间
                Time matureTime = fish.getMatureTime();
                //全部转换成long
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                Date date = null;
                try {
                    date = format.parse(time);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
                //增加9小时就是正常的时间
                long time1 = date.getTime() + 32400000l;
                long time2 = matureTime.getTime() + 32400000l;
                jpb = new JProgressBar();
                jpb.setBounds(x, y - 30, 100, 20);
                jpb.setStringPainted(true);//显示进度百分比
                jpb.setMinimum(0);//设置最小进度值
                jpb.setMaximum(100);//设置最大进度值
                double percent = 100 * (time2 - time1) / time2;//算出当前进度
                jpb.setValue((int) percent);//设置当前进度值
                guluFishing.add(jpb);
            }
            //每次往右偏移
            if (x <= 600) {
                x += 150;
            } else {
                x = 150;
                y += 150;
            }
            //不停刷新鱼坐标
            ActionListener task = new ActionListener() {
                int curX = label.getX();
                int curY = label.getY();

                @Override
                public void actionPerformed(ActionEvent e) {
                    //调整坐标
                    if (curX <= 0 || curX >= FRAME_WIDTH - 200) {
                        xSpeed = -xSpeed;
                    }
                    if (curY <= 0 || curY >= 250) {
                        ySpeed = -ySpeed;
                    }
                    curX += xSpeed;
                    curY += ySpeed;
                    //重新设置鱼的位置
                    label.setBounds(curX, curY, 100, 100);
                    jpb.setBounds(curX, curY - 30, 100, 20);
                    guluFishing.repaint();
                }
            };
            //计时器监听
            Timer timer = new Timer(100, task);
            timer.start();
            //绑定鼠标点击
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    String time = fishService.getRemainMatureTime(fish.getId());
                    //选中时停止游泳
                    timer.stop();
                    //判断是否成熟
                    if ("0".equals(time)) {
                        //时间是0  说明成熟了
                        int confirmHarvest = JOptionPane.showConfirmDialog(jf, "偷？");
                        if (confirmHarvest == 0) {
                            fishService.setMature(fish.getId());
                            //收获
                            fishService.fishHarvesting(fish, visitorId);
                            JOptionPane.showMessageDialog(jf, "好偷!" + "获得" + fish.getExp() + "经验," + fish.getSell() + "积分", "成功", JOptionPane.INFORMATION_MESSAGE);
                            //刷新一下
                            try {
                                new GameInterface().init(id,visitorId);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                            //关闭当前界面
                            jf.dispose();
                        }
                    } else {
                        //没成熟
                        JOptionPane.showMessageDialog(jf, "还有" + time + "成熟");
                    }
                    //最后重新开游
                    timer.start();
                }
            });
            //添加到画布
            guluFishing.add(label);
        }

        //回家
        GuluButton back = new GuluButton("img\\回家.png"
                , "back", 450, 415, 75, 75);
        //绑定鼠标点击打开商城
        back.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //打开商城
                try {
                    new GameInterface().init(visitorId);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                //关闭当前界面
                jf.dispose();
            }
        });

        guluFishing.add(back);

        //把画布布局改成null
        guluFishing.setLayout(null);
        //添加画布到窗口
        jf.add(guluFishing);
        //禁止改变窗口大小
        jf.setResizable(false);
        //设置可见
        jf.setVisible(true);
        //设置点X关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        try {
            new GameInterface().init(1,4);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}