package frame.DeliveryRecript;

import dao.DeliveryRecript.DRdao;
import dao.DistributionSites.DistributionSitesdao;
import dao.base.Client.Clientdao;
import dao.car.Cardao;
import entity.DeliveryReceipt;
import event.DistributionEvent;
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

/**
 * Created by 59480 on 2017/3/21.
 */
public class AddDRDialog extends JDialog implements ActionListener {


    private JButton btnCancel;
    private JButton btnAdd;
    private JComboBox listrecipient;
    private JTextField txtcarrier;
    private JTextField txtcarNo;
    private JTextField txtDate;
    private JTextField txttransitionTo;
    private JTextField txtpostingTo;
    private JTextArea txtqd;
    private JTextField txtid;
    private DRInfoFrame drInfoFrame;

    public AddDRDialog(DRInfoFrame drInfoFrame) {
        this.drInfoFrame=drInfoFrame;

        setTitle("添加交接单");
        setBounds(100, 100, 614, 600);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "交接单信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(0, 0, 598, 450);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label3 = new JLabel("封单号:");
        label3.setBounds(34, 26, 70, 20);
        panel.add(label3);

        txtid = new JTextField();
        txtid.setColumns(10);
        txtid.setBounds(120, 26, 153, 21);
        panel.add(txtid);

        JLabel label = new JLabel("货号清单:");
        label.setBounds(34, 76, 70, 20);
        panel.add(label);

        txtqd = new JTextArea();
        txtqd.setColumns(10);
        txtqd.setBounds(120, 76, 153, 45);
        txtqd.setLineWrap(true);        //激活自动换行功能
        txtqd.setWrapStyleWord(true);// 激活断行不断字功能
        JScrollPane jsp = new JScrollPane(txtqd);
        jsp.setBounds(120,76,153,45);
        panel.add(jsp);

        JLabel label1 = new JLabel("收寄地:");
        label1.setBounds(34, 146, 70, 20);
        panel.add(label1);

        txtpostingTo = new JTextField();
        txtpostingTo.setColumns(10);
        txtpostingTo.setBounds(120, 146, 153, 21);
        panel.add(txtpostingTo);

        JLabel label2 = new JLabel("交接地:");
        label2.setBounds(34, 196, 70, 20);
        panel.add(label2);

        txttransitionTo = new JTextField();
        txttransitionTo.setColumns(10);
        txttransitionTo.setBounds(120, 196, 153, 21);
        panel.add(txttransitionTo);

        JLabel label5 = new JLabel("日期:");
        label5.setBounds(34, 246, 70, 20);
        panel.add(label5);

        txtDate = new JTextField();
        txtDate.setEditable(false);
        txtDate.setBounds(108, 246, 350, 21);
        panel.add(txtDate);
        txtDate.setColumns(10);

        JButton btnChooseTime = new JButton("选择时间");
        btnChooseTime.addActionListener(this);
        btnChooseTime.setBounds(462, 246, 93, 23);
        btnChooseTime.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                DateUI dateUI = DateUI.getInstence(true, txtDate,
                        e.getXOnScreen() - txtDate.getWidth(), e.getYOnScreen()
                                - txtDate.getHeight());
            }
        });
        panel.add(btnChooseTime);

        JLabel label_6 = new JLabel("运输车号:");
        label_6.setBounds(34, 296, 90, 20);
        panel.add(label_6);

        txtcarNo = new JTextField();
        txtcarNo.setColumns(10);
        txtcarNo.setBounds(120, 296, 153, 21);
        panel.add(txtcarNo);


        JLabel label_7 = new JLabel("承运人:");
        label_7.setBounds(34, 346, 80, 20);
        panel.add(label_7);

        txtcarrier = new JTextField();
        txtcarrier.setColumns(10);
        txtcarrier.setBounds(120, 346, 153, 21);
        panel.add(txtcarrier);

        JLabel label_8 = new JLabel("接收人:");
        label_8.setBounds(34, 396, 80, 20);
        panel.add(label_8);

        listrecipient = new JComboBox();
        listrecipient.setModel(new DefaultComboBoxModel(
                new Clientdao().loadname()));
        listrecipient.setBounds(120, 396, 166, 21);
        panel.add(listrecipient);



        btnAdd = new JButton("添加");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(139, 470, 93, 23);
        getContentPane().add(btnAdd);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(371, 470, 93, 23);
        getContentPane().add(btnCancel);

        this.setLocationRelativeTo(this.drInfoFrame);
        this.setModal(true);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            int id=Integer.parseInt(txtid.getText());
            String qd=txtqd.getText();
            String sjd=txtpostingTo.getText();
            String jjd=txttransitionTo.getText();
            if (txtDate.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "时间输入有误请重新输入！");
                return;
            }
            Date time = DateUtil.formatStringToDate(txtDate.getText());
            String chao=txtcarNo.getText();
            String cyr=txtcarrier.getText();
            String jsr=(String) listrecipient.getSelectedItem();
            DeliveryReceipt deliveryReceipt=new DeliveryReceipt(id,qd,sjd,jjd,time,chao,cyr,jsr);
            new DRdao().addDR(deliveryReceipt);
            drInfoFrame.loadDR();
            this.dispose();
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
}