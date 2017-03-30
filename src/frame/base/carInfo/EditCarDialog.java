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
public class EditCarDialog extends JDialog implements ActionListener {
    private JButton btnEdit;
    private JTextField txtweight;
    private JTextField txtType;
    private JTextField txtcube;
    private JTextField txtcang;
    private JComboBox listStatus;
    private JButton btnCancel;
    private CarInfoFrame carInfoFrame;
    private JTextField txtDate;
    private int carId;

    public EditCarDialog(CarInfoFrame carInfoFrame,int carId) {
        this.carInfoFrame = carInfoFrame;
        this.carId=carId;
        setTitle("修改车辆信息");
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
                new String[]{"空闲", "运货"}));
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
        //加载
        Cardao cardao = new Cardao();
        Car car = cardao.findCar(carId);
        txtDate.setText(String.valueOf(new java.sql.Date(car.getBuyT().getTime())));
        txtcang.setText(car.getManufacturer());
        txtcube.setText(String.valueOf(car.getCube()));
        txtType.setText(car.getType());
        txtweight.setText(String.valueOf(car.getLoadCapacity()));
        listStatus.setSelectedItem(car.getCarstate() == 1 ? "空闲" : "运货");

        btnEdit = new JButton("修改");
        btnEdit.addActionListener(this);
        btnEdit.setBounds(139, 356, 93, 23);
        getContentPane().add(btnEdit);

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
        if (e.getSource() == btnEdit) {

            String cang = txtcang.getText();
            Pattern p = Pattern
                    .compile("[\\u4E00-\\u9FA5\\uF900-\\uFA2D]{1,20}|[a-zA-Z]{1,20}");
            Matcher m = p.matcher(txtcang.getText());
            if (!m.matches()) {
                JOptionPane.showMessageDialog(this, "生产厂家输入有误请重新输入！");
                return;
            }
            String type1 = txtType.getText();

            double wetght = Double.parseDouble(txtcube.getText());


            double cube = Double.parseDouble(txtcube.getText());

            if (txtDate.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "购买时间输入有误请重新输入！");
                return;
            }
            Date butT = DateUtil.formatStringToDate(txtDate.getText());
            int carStatus = listStatus.getSelectedItem().equals("空闲") ? 1 : 0;

            Car car = new Car(carId,butT, cang, type1, wetght,cube
                    , carStatus);
            int result = JOptionPane.showConfirmDialog(this, "确认修改该车辆的信息吗？", "温馨提示", JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (result == JOptionPane.YES_OPTION) {
                // 调用DAO层修改
                Cardao cardao = new Cardao();
                cardao.updatecar(car);
                // 刷新
                carInfoFrame.loadcar();
                this.dispose();
            }
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
        }
    }

