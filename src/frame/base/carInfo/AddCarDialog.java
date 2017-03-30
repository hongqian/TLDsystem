package frame.base.carInfo;

import dao.car.Cardao;
import entity.Car;
import util.DateUI;
import util.DateUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 59480 on 2017/3/19.
 */
public class AddCarDialog extends JDialog implements ActionListener {

    private JTextField txtweight;
    private JTextField txtType;
    private JTextField txtcube;
    private JTextField txtcang;
    private JComboBox listStatus;
    private JButton btnCancel;
    private  JButton btnAdd;
    private  CarInfoFrame carInfoFrame;
    private JTextField txtDate;


    public AddCarDialog(CarInfoFrame carInfoFrame) {
        this.carInfoFrame = carInfoFrame;
        setTitle("添加车辆");
        setBounds(100, 100, 700, 470);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "车辆信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(0, 0, 650, 346);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label_2 = new JLabel("生产厂家:");
        label_2.setBounds(34, 26, 90, 20);
        panel.add(label_2);

        txtcang = new JTextField();
        txtcang.setColumns(10);
        txtcang.setBounds(386, 26, 169, 21);
        panel.add(txtcang);

        JLabel label_3 = new JLabel("型号:");
        label_3.setBounds(34, 78, 54, 20);
        panel.add(label_3);

        txtType = new JTextField();
        txtType.setColumns(10);
        txtType.setBounds(386, 78, 169, 21);
        panel.add(txtType);

        JLabel label_4 = new JLabel("载重量:");
        label_4.setBounds(34, 176, 54, 20);
        panel.add(label_4);

        txtweight = new JTextField();
        txtweight.setColumns(10);
        txtweight.setBounds(386, 176, 169, 21);
        panel.add(txtweight);

        JLabel label_5 = new JLabel("体积:");
        label_5.setBounds(34, 125, 54, 20);
        panel.add(label_5);

        txtcube = new JTextField();
        txtcube.setColumns(10);
        txtcube.setBounds(386, 125, 169, 21);
        panel.add(txtcube);

        JLabel label_6 = new JLabel("车辆状态:");
        label_6.setBounds(34, 260, 90, 20);
        panel.add(label_6);

        listStatus = new JComboBox();
        listStatus.setModel(new DefaultComboBoxModel(
                new String[] { "空闲", "运货" }));
        listStatus.setBounds(389, 260, 166, 21);
        panel.add(listStatus);

        JLabel label_7 = new JLabel("购买时间:");
        label_7.setBounds(34, 216, 80, 20);
        panel.add(label_7);

        txtDate = new JTextField();
        txtDate.setEditable(false);
        txtDate.setBounds(108, 216, 350, 21);
        panel.add(txtDate);
        txtDate.setColumns(10);

        JButton btnChooseTime = new JButton("选择时间");
        btnChooseTime.addActionListener(this);
        btnChooseTime.setBounds(462, 215, 93, 23);
        btnChooseTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DateUI dateUI = DateUI.getInstence(true, txtDate,
                        e.getXOnScreen() - txtDate.getWidth(), e.getYOnScreen()
                                - txtDate.getHeight());
            }
        });
        panel.add(btnChooseTime);

        btnAdd = new JButton("添加");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(139, 356, 93, 23);
        getContentPane().add(btnAdd);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(371, 356, 93, 23);
        getContentPane().add(btnCancel);

        this.setLocationRelativeTo(this.carInfoFrame);
        this.setModal(true);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {

            String cang = txtcang.getText();
            Pattern p = Pattern
                    .compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
            Matcher m = p.matcher(txtcang.getText());
            if (!m.matches()) {
                JOptionPane.showMessageDialog(this, "生产厂家输入有误请重新输入！");
                return;
            }
            String type1=txtType.getText();



            double weight = Double.parseDouble(txtcube.getText());


            double cube = Double.parseDouble(txtcube.getText());

            if (txtDate.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "购买时间输入有误请重新输入！");
                return;
            }
            Date butT = DateUtil.formatStringToDate(txtDate.getText());
            int carStatus = listStatus.getSelectedItem().equals("空闲") ? 1 : 0;

            // 组装
            Car car = new Car(0, butT, cang, type1, weight,cube
                    , carStatus);
            // 调用DAO层添加
            Cardao cardao = new Cardao();
            cardao.addCarInfo(car);
            // 刷新
            carInfoFrame.loadcar();
            this.dispose();
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }


        }
    }

