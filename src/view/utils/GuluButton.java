package view.utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;

public class GuluButton extends JButton {
    Image img1 =null;
    ImageIcon icon =null;
    boolean entered = false;//鼠标移入
    JPopupMenu jPopupMenu =null;
    String tipTxt =null;

    public GuluButton() {
    }

    public GuluButton(String imgPath, String text, String tipText, int positionX, int positionY, int btn_width, int btn_height,Font f) throws IOException {
        img1 = ImageIO.read(new File(imgPath));
        icon = new ImageIcon(img1);
        this.setIcon(icon);
        icon.setImage(icon.getImage().getScaledInstance(btn_width, btn_height, Image.SCALE_DEFAULT));//图片自适应大小
        this.setBounds(positionX, positionY, btn_width, btn_height);//设置标签位置大小，

        this.setOpaque(false);//设置控件是否透明，true为不透明，false为透明
        this.setContentAreaFilled(false);//设置图片填满按钮所在的区域
        this.setBorderPainted(false);//设置是否绘制边框
        this.setBorder(null);//设置边框
        //文字效果
        this.setText(text);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setFont(f);
        //鼠标动态效果
        addMouseListener((new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                entered = true;

                if (tipText!=null){
                    try {
                        jPopupMenu.show(GuluButton.this,e.getX(),e.getY());
                    } catch (Exception ex) {
                        return;
                    }
                }
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                entered = false;

                if (tipText!=null){
                    jPopupMenu.setVisible(false);
                }
                repaint();
            }
        }));
        //提示文字
        jPopupMenu = new JPopupMenu();
        jPopupMenu.setBorder(null);
        jPopupMenu.setBorderPainted(false);
        this.tipTxt = tipText;
        if (tipText!=null){
            jPopupMenu.add(new JMenuItem(tipText));
        }


    }

    public GuluButton(String text,String tipText, int positionX, int positionY, int btn_width, int btn_height,Font f) throws IOException {
        this.setBounds(positionX, positionY, btn_width, btn_height);//设置标签位置大小，
        //文字效果
        this.setText(text);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        this.setFont(f);
        //设置控件
        this.setOpaque(false);//设置控件是否透明，true为不透明，false为透明
        this.setContentAreaFilled(false);//设置图片填满按钮所在的区域
        this.setBorderPainted(false);//设置是否绘制边框
        this.setBorder(null);//设置边框
        //鼠标动态效果
        addMouseListener((new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                entered = true;

                if (tipText!=null){
                    try {
                        jPopupMenu.show(GuluButton.this,e.getX(),e.getY()+10);
                    } catch (Exception ex) {
                        return;
                    }
                }
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                entered = false;

                if (tipText!=null){
                    jPopupMenu.setVisible(false);
                }
                repaint();
            }
        }));
        //提示文字
        jPopupMenu = new JPopupMenu();
        jPopupMenu.setBorder(null);
        jPopupMenu.setBorderPainted(false);
        this.tipTxt = tipText;
        if (tipText!=null){
            jPopupMenu.add(new JMenuItem(tipText));
        }


    }

    public GuluButton(String imgPath, String tipText, int positionX, int positionY, int btn_width, int btn_height) throws IOException {
        img1 = ImageIO.read(new File(imgPath));
        icon = new ImageIcon(img1);
        this.setIcon(icon);
        icon.setImage(icon.getImage().getScaledInstance(btn_width, btn_height, Image.SCALE_DEFAULT));//图片自适应大小
        this.setBounds(positionX, positionY, btn_width, btn_height);//设置标签位置大小，

        this.setOpaque(false);//设置控件是否透明，true为不透明，false为透明
        this.setContentAreaFilled(false);//设置图片填满按钮所在的区域
        this.setBorderPainted(false);//设置是否绘制边框
        this.setBorder(null);//设置边框
        //鼠标动态效果
        addMouseListener((new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                entered = true;

                if (tipText!=null){
                    try {
                        jPopupMenu.show(GuluButton.this,e.getX(),e.getY()+10);
                    } catch (Exception ex) {
                        return;
                    }
                }
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                entered = false;

                if (tipText!=null){
                    jPopupMenu.setVisible(false);
                }
                repaint();
            }
        }));
        //提示文字
        jPopupMenu = new JPopupMenu();
        jPopupMenu.setBorder(null);
        jPopupMenu.setBorderPainted(false);
        this.tipTxt = tipText;
        if (tipText!=null){
            jPopupMenu.add(new JMenuItem(tipText));
        }


    }


    public GuluButton(String imgPath, String text, String tipText, int positionX, int positionY, int btn_width, int btn_height) throws IOException {
        img1 = ImageIO.read(new File(imgPath));
        icon = new ImageIcon(img1);
        this.setIcon(icon);
        icon.setImage(icon.getImage().getScaledInstance(btn_width, btn_height, Image.SCALE_DEFAULT));//图片自适应大小
        this.setBounds(positionX, positionY, btn_width, btn_height);//设置标签位置大小，

        this.setOpaque(false);//设置控件是否透明，true为不透明，false为透明
        this.setContentAreaFilled(false);//设置图片填满按钮所在的区域
        this.setBorderPainted(false);//设置是否绘制边框
        this.setBorder(null);//设置边框
        //文字效果
        this.setText(text);
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        //鼠标动态效果
        addMouseListener((new MouseAdapter(){

            @Override
            public void mouseEntered(MouseEvent e) {
                entered = true;

                if (tipText!=null){
                    try {
                        jPopupMenu.show(GuluButton.this,e.getX(),e.getY());
                    } catch (Exception ex) {
                        return;
                    }
                }
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                entered = false;

                if (tipText!=null){
                    jPopupMenu.setVisible(false);
                }
                repaint();
            }
        }));
        //提示文字
        jPopupMenu = new JPopupMenu();
        jPopupMenu.setBorder(null);
        jPopupMenu.setBorderPainted(false);
        this.tipTxt = tipText;
        if (tipText!=null){
            jPopupMenu.add(new JMenuItem(tipText));
        }


    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));

        if (entered){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.1f));
            g2.fillRect(0,0,getWidth(),getHeight());

        }

    }
}
