package view;

import domain.User;
import service.UserService;
import service.impl.UserServiceImpl;
import view.utils.GuluButton;
import view.utils.ScreenUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Vector;

public class LeaderboardInterface {
    //调用逻辑层方法的对象
    static UserService userService = new UserServiceImpl();
    static GuluButton pondBtn;

    //保存用户ID
    int id;

    //创建窗口
    JFrame jf = new JFrame("排行榜");
    final int WIDTH = 300;
    final int HEIGHT = 450;
    private JTable table;
    private Vector<String> title;
    private Vector<Vector> tableData;

    private TableModel tableModel;

    public void init(int userId) throws Exception {
        id = userId;
        //设置窗口相关的属性
        //1 窗口位于屏幕正中间
        jf.setBounds((ScreenUtils.getScreenWidth() - WIDTH) / 2, (ScreenUtils.getScreenHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
        //2 窗口大小不变
        jf.setResizable(false);
        //3 设置窗口图标
        jf.setIconImage(ImageIO.read(new File("img\\游戏图标.png")));
        Box verticalBox = Box.createVerticalBox();

        Color color = new Color(203, 220, 217);

        JPanel jPanel = new JPanel();
        jPanel.setBackground(color);
        GuluButton back = new GuluButton("img\\游戏按钮背景1.png",
                "返回鱼塘", "Back", 0, 0, 80, 40);
        back.setFont(new Font("宋体", Font.BOLD, 12));

        pondBtn = new GuluButton("img\\游戏按钮背景1.png",
                "鱼塘", "偷鱼", 0, 0, 20, 20);

        jPanel.setMaximumSize(new Dimension(WIDTH, 150));
        jPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        jPanel.add(back);
        verticalBox.add(jPanel);

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

        //表头

        String[] ts = {"排名", "用户名", "等级", "鱼塘"};
        title = new Vector<>();
        for (String t : ts) {
            title.add(t);
        }
        //请求数据
        Vector tableData = returnTableData();

        tableModel = new DefaultTableModel(tableData, title);

        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {

                if (column == 3)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        };
        table.setRowHeight(30);// 设置表格行宽
        table.setFont(new Font("宋体", Font.BOLD, 13));// 设置表格字体
        table.getColumnModel().getColumn(3).setCellEditor(new GuluButtonEditor());

        table.getColumnModel().getColumn(3).setCellRenderer(new GuluButtonRender());


        //设置单行选中
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //设置滚动条
        JScrollPane scrollPane = new JScrollPane(table);


        verticalBox.add(scrollPane);
        jf.add(verticalBox);


        //6 设置窗口可见
        jf.setVisible(true);

        //设置点X关闭
        jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) throws Exception {
        new LeaderboardInterface().init(1);
    }

    public Vector returnTableData() throws IOException {
        int n;
        int RankId = 1;
        List<User> users = userService.rank();
        n = users.size();
        //vector< vector<int> > v(n，vector<int>(m))；//二维向量
        Vector<Vector<Object>> tableDataTemp = new Vector(n, 1);
        tableData = new Vector<>();

        for (User user : users) {
            Vector<Object> vector = new Vector<>();
            vector.add(RankId++);
            vector.add(user.getUsername());
            vector.add(user.getLevel());
            vector.add(user.getId());
            tableDataTemp.add(vector);
        }

        for (Vector<Object> objects : tableDataTemp) {
            tableData.add(objects);
        }
        return tableData;

    }

    class GuluButtonRender implements TableCellRenderer {
        private JPanel panel;

        private GuluButton button;

        public GuluButtonRender() {
            this.initButton();

            this.initPanel();

            // 添加按钮。
            this.panel.add(this.button);
        }

        private void initButton() {
            this.button = pondBtn;

            // 设置按钮的大小及位置。
            this.button.setBounds(0, 0, 50, 29);

        }

        private void initPanel() {
            this.panel = new JPanel();
            this.panel.setLayout(null);
            this.panel.setBackground(
                    Color.white
            );
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row,
                                                       int column) {
            this.button.setText("偷鱼");

            return this.panel;
        }


    }

    class GuluButtonEditor extends DefaultCellEditor {

        /**
         * serialVersionUID
         */
        private static final long serialVersionUID = -6546334664166791132L;

        private JPanel panel;

        private JButton button;

        public GuluButtonEditor() {
            super(new JTextField());

            // 设置点击几次激活编辑。
            this.setClickCountToStart(1);

            this.initButton();

            this.initPanel();

            // 添加按钮。
            this.panel.add(this.button);
        }

        private void initButton() {
            this.button = new JButton();

            // 设置按钮的大小及位置。
            this.button.setBounds(0, 0, 50, 29);

            this.button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // 触发取消编辑的事件，不会调用tableModel的setValue方法。
                    GuluButtonEditor.this.fireEditingCanceled();
                    // 获取用户id
                    int selectedRow = table.getSelectedRow();
                    int selectedColumn = table.getSelectedColumn();
                    int userId = Integer.parseInt(table.getValueAt(selectedRow, selectedColumn).toString());
                    //这里绑定偷鱼的事件
                    try {
                        new GameInterface().init(userId,id);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    //关闭当前界面
                    jf.dispose();
                }
            });

        }

        private void initPanel() {
            this.panel = new JPanel();
            this.panel.setLayout(null);
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            return this.panel;
        }

        public Object getCellEditorValue() {
            return this.button.getText();
        }

    }
}
