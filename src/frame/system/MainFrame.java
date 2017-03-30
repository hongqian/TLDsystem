package frame.system;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import entity.ReportForms;
import entity.User;
import event.BaseEvent;
import event.DbSEvent;
import event.DistributionEvent;
import event.SystemEvent;
import org.jvnet.substance.SubstanceLookAndFeel;
import org.jvnet.substance.border.StandardBorderPainter;
import org.jvnet.substance.button.StandardButtonShaper;
import org.jvnet.substance.painter.StandardGradientPainter;
import org.jvnet.substance.skin.CremeCoffeeSkin;
import org.jvnet.substance.theme.SubstanceTerracottaTheme;
import org.jvnet.substance.title.FlatTitlePainter;
import org.jvnet.substance.watermark.SubstanceBubblesWatermark;

/**
 * Created by 59480 on 2017/3/19.
 */
public class MainFrame extends JFrame implements ActionListener {

    public User user;
    private JMenu basicMenu;
    private JMenuItem customerInfoItem;
    private JMenuItem employeeInfoItem;
    // 系统设置
    private JMenu systemMenu;
    private JMenuItem userManagerItem;
    private JMenu aboutMenu;
    private JMenuItem aboutItem;
    private JMenuItem quitSystemItem;
    private JMenuItem logoutItem;
    private JMenuItem roleManagerItem;
    private JButton btnReport;
    private JButton btnSystem;
    private JButton btnBasic;
    private JLabel lblWelcome;
    private JLabel lblTime;
    private JPanel panel_1;
    private JLabel lblUser;
    public JDesktopPane desktopPane;
    private JPanel panBasic;
    private JPanel panSystem;
    private JPanel panReport;
    private  JMenuItem carInfoItem;
    private JPanel contentPane;
    private JMenu disMenu;
    private JButton btnDis;
    private JMenuItem DisInfoItem;
    private JMenuItem scopeInfoItem;
    private JPanel panDis;
    private JMenu orderMenu;
    private JMenuItem vipInfoItem;
    private JMenuItem adminInfoItem;
    private JMenu DistributionMenu;
    private JMenuItem findTsInfoItem;
    private JMenuItem TsInfoItem;
    private JButton  btnorder;
    private JButton btnDistribution;
    private JPanel panvip;
    private JPanel panDistribution;
    private JMenu  ReportFormsMenu;
    private JMenuItem RFInfoItem;
    private JMenuItem DbSRFInfoItem;

    public MainFrame(User user) {
        this.setTitle("烟草物流管理系统1.0版本");
        this.user = user;
        // 设置全屏
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        setBounds(0, 0, width - 1000, height - 500);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        basicMenu = new JMenu("基本设置(B)");
        basicMenu.setMnemonic(KeyEvent.VK_B);
        menuBar.add(basicMenu);
        //上方菜单
        customerInfoItem = new JMenuItem("客户资料");
        customerInfoItem.addActionListener(this);
        basicMenu.add(customerInfoItem);

        JSeparator separator = new JSeparator();
        basicMenu.add(separator);

        carInfoItem = new JMenuItem("车辆信息");
        carInfoItem.addActionListener(this);
        basicMenu.add(carInfoItem);

        employeeInfoItem = new JMenuItem("员工资料");
        employeeInfoItem.addActionListener(this);
        basicMenu.add(employeeInfoItem);

        disMenu = new JMenu("配送点管理(D)");
        disMenu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(disMenu);

        DisInfoItem = new JMenuItem("配送点信息");
        DisInfoItem.addActionListener(this);
        disMenu.add(DisInfoItem);

        scopeInfoItem = new JMenuItem("配送范围信息");
        scopeInfoItem.addActionListener(this);
        disMenu.add(scopeInfoItem);

        orderMenu = new JMenu("订单管理(O)");
        orderMenu.setMnemonic(KeyEvent.VK_O);
        menuBar.add(orderMenu);

        vipInfoItem = new JMenuItem("客户订单管理");
        vipInfoItem.addActionListener(this);
        orderMenu.add(vipInfoItem);

        adminInfoItem = new JMenuItem("管理员订单管理");
        adminInfoItem.addActionListener(this);
        orderMenu.add(adminInfoItem);


        DistributionMenu = new JMenu("配送管理(B)");
        DistributionMenu.setMnemonic(KeyEvent.VK_B);
        menuBar.add(DistributionMenu);

        TsInfoItem = new JMenuItem("交接单管理");
        TsInfoItem.addActionListener(this);
        DistributionMenu.add(TsInfoItem);

        findTsInfoItem = new JMenuItem("查看交接单");
        findTsInfoItem.addActionListener(this);
        DistributionMenu.add(findTsInfoItem);

        ReportFormsMenu = new JMenu("报表管理(R)");
        ReportFormsMenu.setMnemonic(KeyEvent.VK_R);
        menuBar.add(ReportFormsMenu);

        RFInfoItem = new JMenuItem("省公司报表管理");
        RFInfoItem.addActionListener(this);
        ReportFormsMenu.add(RFInfoItem);

        DbSRFInfoItem = new JMenuItem("配送点报表管理");
        DbSRFInfoItem.addActionListener(this);
        ReportFormsMenu.add(DbSRFInfoItem);

        systemMenu = new JMenu("系统设置(T)");
        systemMenu.setMnemonic(KeyEvent.VK_T);
        menuBar.add(systemMenu);


        userManagerItem = new JMenuItem("用户管理");
        userManagerItem.addActionListener(this);

        roleManagerItem = new JMenuItem("角色管理");
        roleManagerItem.addActionListener(this);
        systemMenu.add(roleManagerItem);
        systemMenu.add(userManagerItem);

        aboutMenu = new JMenu("关于(T)");
        aboutMenu.setMnemonic(KeyEvent.VK_T);
        menuBar.add(aboutMenu);

        aboutItem = new JMenuItem("关于");
        aboutItem.addActionListener(this);
        aboutMenu.add(aboutItem);

        JSeparator separator_5 = new JSeparator();
        systemMenu.add(separator_5);

        quitSystemItem = new JMenuItem("退出系统");
        quitSystemItem.addActionListener(this);

        logoutItem = new JMenuItem("注销");
        logoutItem.addActionListener(this);
        systemMenu.add(logoutItem);
        systemMenu.add(quitSystemItem);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(200, 0));
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(null);

        JLabel lblFunction = new JLabel("");
        lblFunction.setForeground(Color.RED);
        lblFunction.setFont(new Font("幼圆", Font.BOLD, 24));
        lblFunction.setHorizontalAlignment(SwingConstants.CENTER);
        lblFunction.setBounds(0, 10, 195, 36);
        lblFunction.setText("功能导航");
        panel.add(lblFunction);
        //左边工具栏
        btnBasic = new JButton("基础资料");
        btnBasic.addActionListener(this);
        btnBasic.setHorizontalAlignment(SwingConstants.LEFT);
        btnBasic.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnBasic.setBounds(10, 48, 170, 40);
        panel.add(btnBasic);

        btnDis = new JButton("配送点管理");
        btnDis.addActionListener(this);
        btnDis.setHorizontalAlignment(SwingConstants.LEFT);
        btnDis.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDis.setBounds(10, 99, 170, 40);
        panel.add(btnDis);

        btnorder = new JButton("订单管理");
        btnorder.addActionListener(this);
        btnorder.setHorizontalAlignment(SwingConstants.LEFT);
        btnorder.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnorder.setBounds(10, 150, 170, 40);
        panel.add(btnorder);

        btnDistribution = new JButton("配送管理");
        btnDistribution.addActionListener(this);
        btnDistribution.setHorizontalAlignment(SwingConstants.LEFT);
        btnDistribution.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnDistribution.setBounds(10, 200, 170, 40);
        panel.add(btnDistribution);

        //左边工具栏
        btnReport = new JButton("统计报表");
        btnReport.addActionListener(this);
        btnReport.setHorizontalAlignment(SwingConstants.LEFT);
        btnReport.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnReport.setBounds(10, 243, 170, 40);
        panel.add(btnReport);

        //左边工具栏
        btnSystem = new JButton("系统管理");
        btnSystem.addActionListener(this);
        btnSystem.setHorizontalAlignment(SwingConstants.LEFT);
        btnSystem.setHorizontalTextPosition(SwingConstants.RIGHT);
        btnSystem.setBounds(10, 294, 170, 40);
        panel.add(btnSystem);

        panel_1 = new JPanel();
        panel_1.setPreferredSize(new Dimension(0, 50));
        contentPane.add(panel_1, BorderLayout.SOUTH);
        panel_1.setLayout(new GridLayout(0, 3, 0, 0));

        // 下方提示当前用户
        lblUser = new JLabel();
        lblUser.setText("当前用户:                 " + user.getUserAccount());
        lblUser.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 30));
        lblUser.setForeground(Color.BLUE);
        lblUser.setBorder(new BevelBorder(BevelBorder.LOWERED));
        panel_1.add(lblUser);

        // 欢迎用户使用
        lblWelcome = new JLabel();
        lblWelcome.setForeground(Color.BLUE);
        lblWelcome.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null,
                null, null));
        lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
        lblWelcome.setForeground(Color.BLUE);
        lblWelcome.setText("欢迎使用烟草物流管理系统！");
        lblWelcome.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 30));
        lblWelcome.setHorizontalTextPosition(SwingConstants.RIGHT);
        panel_1.add(lblWelcome);

        // 时间提示栏
        lblTime = new JLabel();
        lblTime.setBorder(new BevelBorder(BevelBorder.LOWERED));
        lblTime.setHorizontalAlignment(SwingConstants.CENTER);

        lblTime.setHorizontalTextPosition(SwingConstants.RIGHT);
        lblTime.setForeground(Color.RED);
        lblTime.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 30));
        panel_1.add(lblTime);

        desktopPane = new JDesktopPane();
        desktopPane.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
        desktopPane.setBackground(UIManager
                .getColor("CheckBox.interiorBackground"));
        contentPane.add(desktopPane, BorderLayout.CENTER);

        // 基础面板
        panBasic = new JPanel();
        panBasic.setBounds(10, 10, 914, 521);
        desktopPane.add(panBasic);
        panBasic.setLayout(null);


        JButton btnBasicEmp = new JButton("员工资料");
        btnBasicEmp.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBasicEmp.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBasicEmp.setBounds(205, 94, 155, 155);
        btnBasicEmp.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                employeeInfoItem.doClick();
            }
        });
        panBasic.add(btnBasicEmp);

        JButton btnBasicGoods = new JButton("车辆信息");
        btnBasicGoods.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBasicGoods.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBasicGoods.setBounds(415, 195, 155, 155);
        btnBasicGoods.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                carInfoItem.doClick();
            }
        });
        panBasic.add(btnBasicGoods);

        JButton btnBasicVip = new JButton("客户资料");
        btnBasicVip.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnBasicVip.setHorizontalTextPosition(SwingConstants.CENTER);
        btnBasicVip.setBounds(618, 311, 155, 155);
        btnBasicVip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                customerInfoItem.doClick();
            }
        });
        panBasic.add(btnBasicVip);
        panBasic.setVisible(false);


        //配送点
        panDis = new JPanel();
        panDis.setBounds(10, 10, 904, 521);
        desktopPane.add(panDis);
        panDis.setLayout(null);

        JButton btnDis = new JButton("配送点信息");
        btnDis.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnDis.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDis.setBounds(205, 94, 155, 155);
        btnDis.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DisInfoItem.doClick();
            }
        });
        panDis.add(btnDis);

        JButton btnScope = new JButton("配送范围信息");
        btnScope.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnScope.setHorizontalTextPosition(SwingConstants.CENTER);
        btnScope.setBounds(415, 195, 155, 155);
        btnScope.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                scopeInfoItem.doClick();
            }
        });
        panDis.add(btnScope);
        panDis.setVisible(false);

        //订单面板
        panvip = new JPanel();
        panvip.setBounds(10, 10, 904, 521);
        desktopPane.add(panvip);
        panvip.setLayout(null);

        JButton btnvip = new JButton("客户订单管理");
        btnvip.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnvip.setHorizontalTextPosition(SwingConstants.CENTER);
        btnvip.setBounds(205, 94, 155, 155);
        btnvip.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                vipInfoItem.doClick();
            }
        });
        panvip.add(btnvip);

        JButton btnadmin = new JButton("管理员订单管理");
        btnadmin.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnadmin.setHorizontalTextPosition(SwingConstants.CENTER);
        btnadmin.setBounds(415, 195, 155, 155);
        btnadmin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                adminInfoItem.doClick();
            }
        });
        panvip.add(btnadmin);
        panvip.setVisible(false);

        //配送面板
        panDistribution = new JPanel();
        panDistribution.setBounds(10, 10, 904, 521);
        desktopPane.add(panDistribution);
        panDistribution.setLayout(null);

        JButton btnTs = new JButton("交接单管理");
        btnTs.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnTs.setHorizontalTextPosition(SwingConstants.CENTER);
        btnTs.setBounds(205, 94, 155, 155);
        btnTs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                TsInfoItem.doClick();
            }
        });
        panDistribution.add(btnTs);

        JButton btnfindTs = new JButton("查看交接单");
        btnfindTs.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnfindTs.setHorizontalTextPosition(SwingConstants.CENTER);
        btnfindTs.setBounds(415, 195, 155, 155);
        btnfindTs.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                findTsInfoItem.doClick();
            }
        });
        panDistribution.add(btnfindTs);
        panDistribution.setVisible(false);



        // 报表面板
        panReport = new JPanel();
        panReport.setBounds(10, 10, 904, 521);
        desktopPane.add(panReport);
        panReport.setLayout(null);

        JButton btnRF = new JButton("省公司报表管理");
        btnRF.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnRF.setHorizontalTextPosition(SwingConstants.CENTER);
        btnRF.setBounds(205, 94, 155, 155);
        btnRF.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                RFInfoItem.doClick();
            }
        });
        panReport.add(btnRF);

        JButton btnDReport = new JButton("配送点报表管理");
        btnDReport.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnDReport.setHorizontalTextPosition(SwingConstants.CENTER);
        btnDReport.setBounds(415, 195, 155, 155);
        btnDReport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DbSRFInfoItem.doClick();
            }
        });
        panReport.add(btnDReport);
        panReport.setVisible(false);





        // 系统面板
        panSystem = new JPanel();
        panSystem.setBounds(10, 10, 904, 521);
        desktopPane.add(panSystem);
        panSystem.setLayout(null);


        JButton btnSystemRoleManager = new JButton("角色管理");
        btnSystemRoleManager.setAlignmentX(1.0f);
        btnSystemRoleManager.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSystemRoleManager.setHorizontalTextPosition(SwingConstants.CENTER);
        btnSystemRoleManager.setBounds(415, 94, 155, 155);
        btnSystemRoleManager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                roleManagerItem.doClick();
            }
        });
        panSystem.add(btnSystemRoleManager);

        JButton btnSystemUserManager = new JButton("用户管理");
        btnSystemUserManager.setAlignmentX(1.0f);
        btnSystemUserManager.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSystemUserManager.setHorizontalTextPosition(SwingConstants.CENTER);

        btnSystemUserManager.setBounds(205, 311, 155, 155);
        btnSystemUserManager.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                userManagerItem.doClick();
            }
        });
        panSystem.add(btnSystemUserManager);

        JButton btnSystemExit = new JButton("退出系统");
        btnSystemExit.setAlignmentX(1.0f);
        btnSystemExit.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnSystemExit.setHorizontalTextPosition(SwingConstants.CENTER);

        btnSystemExit.setBounds(618, 311, 155, 155);
        btnSystemExit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                quitSystemItem.doClick();
            }
        });
        panSystem.add(btnSystemExit);

        JButton btnZhuxiao = new JButton("注销");
        btnZhuxiao.setAlignmentX(1.0f);
        btnZhuxiao.setVerticalTextPosition(SwingConstants.BOTTOM);
        btnZhuxiao.setHorizontalTextPosition(SwingConstants.CENTER);
        btnZhuxiao.setBounds(415, 311, 155, 155);
        btnZhuxiao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                logoutItem.doClick();
            }
        });
        panSystem.add(btnZhuxiao);
        panSystem.setVisible(false);

        // 设置欢迎标栏烁
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                // 欢迎栏闪烁
                Timer timerWelcome = new Timer(1000, new ActionListener() {
                    String welcome = lblWelcome.getText();

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // 时间
                        Date date = new Date();
                        SimpleDateFormat sdf = new SimpleDateFormat(
                                "yyyy年MM月dd日 HH:mm:ss EE ");
                        lblTime.setText(sdf.format(date));

                        if (lblWelcome.getText().isEmpty()) {
                            lblWelcome.setText(welcome);

                        } else {
                            lblWelcome.setText("");
                            lblWelcome.setIcon(null);
                        }
                    }
                });
                timerWelcome.start();
            }

            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(MainFrame.this,
                        "确认退出吗?", "温馨提示", JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if (result == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });


        // 检查用户
        checkAuth(user);
        btnBasic.doClick();

        // 设置按钮的形状
        SubstanceLookAndFeel.setCurrentButtonShaper(new StandardButtonShaper());
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e) {
        // 基本设置
        if (e.getSource() == customerInfoItem
                || e.getSource() ==carInfoItem
                || e.getSource() == employeeInfoItem) {
            String event = e.getActionCommand().toString();
            BaseEvent baseEvent = new BaseEvent(this, event);
            baseEvent.doActionEvent();
        }else if(e.getSource() == DisInfoItem
                || e.getSource() == scopeInfoItem){
            String event = e.getActionCommand().toString();
            DbSEvent dbsEvent=new DbSEvent(this,event);
            dbsEvent.doActionEvent();
        }else if(e.getSource()==vipInfoItem
                ||e.getSource()==adminInfoItem) {

        }else if(e.getSource()==TsInfoItem
                ||e.getSource()==findTsInfoItem){
            String event=e.getActionCommand().toString();
            DistributionEvent distributionEvent=new DistributionEvent(this,event);
            distributionEvent.doActionEvent();
        }else if(e.getSource()==RFInfoItem
                ||e.getSource()==DbSRFInfoItem){

        }else if (e.getSource() == userManagerItem
                || e.getSource() == aboutItem
                || e.getSource() == quitSystemItem
                || e.getSource() == roleManagerItem
                || e.getSource() == logoutItem) {
            String event = e.getActionCommand().toString();
            SystemEvent systemEvent = new SystemEvent(this, event);
            systemEvent.doActionEvent();
            }else if (e.getSource() == btnBasic) {
            panBasic.setVisible(true);
            panDis.setVisible(false);
            panvip.setVisible(false);
            panDistribution.setVisible(false);
            panReport.setVisible(false);
            panSystem.setVisible(false);
        }else if(e.getSource()==btnDis) {
            panBasic.setVisible(false);
            panDis.setVisible(true);
            panvip.setVisible(false);
            panDistribution.setVisible(false);
            panReport.setVisible(false);
            panSystem.setVisible(false);
        }else if(e.getSource()==btnorder){
            panBasic.setVisible(false);
            panDis.setVisible(false);
            panvip.setVisible(true);
            panDistribution.setVisible(false);
            panReport.setVisible(false);
            panSystem.setVisible(false);
        }else if(e.getSource()==btnDistribution){
            panBasic.setVisible(false);
            panDis.setVisible(false);
            panvip.setVisible(false);
            panDistribution.setVisible(true);
            panReport.setVisible(false);
            panSystem.setVisible(false);
        } else if (e.getSource() == btnReport) {
            panBasic.setVisible(false);
            panDis.setVisible(false);
            panvip.setVisible(false);
            panDistribution.setVisible(false);
            panReport.setVisible(true);
            panSystem.setVisible(false);
        } else if (e.getSource() == btnSystem) {
            panBasic.setVisible(false);
            panDis.setVisible(false);
            panvip.setVisible(false);
            panDistribution.setVisible(false);
            panReport.setVisible(false);
            panSystem.setVisible(true);
        }
    }

    /**
     * 检查用户的权限
     *
     * @param user
     */
    public void checkAuth(User user) {
        String roleName = user.getRoleId().getRoleName();
        if (roleName.equals("配送点管理员")) {
            // 左边按钮
            btnReport.setEnabled(false);
            btnSystem.setEnabled(false);
            btnBasic.setEnabled(false);
            // 上边按钮

            roleManagerItem.setEnabled(false);
            userManagerItem.setEnabled(false);
        }
    }

}
