package frame.DeliveryRecript;

import dao.DeliveryRecript.DRdao;
import dao.car.Cardao;
import entity.DeliveryReceipt;
import util.DateUtil;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

/**
 * Created by 59480 on 2017/3/21.
 */
public class EditDRDialog extends JDialog implements ActionListener {


    private int fhd;
    private JButton btnCancel;
    private JButton btnAdd;
    private JComboBox listDbs;
    private JTextField txtComment;
    private DRInfoFrame drInfoFrame;
    private JTextField txtComment1;

    public EditDRDialog(DRInfoFrame drInfoFrame,int fhd) {
        this.drInfoFrame=drInfoFrame;
        this.fhd=fhd;
        setTitle("调配交接单");
        setBounds(100, 100, 614, 350);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "交接单信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(0, 0, 598, 200);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("路线起点");
        lblNewLabel_1.setBounds(34, 26, 70, 15);
        panel.add(lblNewLabel_1);

        txtComment = new JTextField();
        txtComment.setBounds(120, 26, 193, 47);
        panel.add(txtComment);

        JLabel lblNewLabel_2 = new JLabel("路线终点");
        lblNewLabel_2.setBounds(34, 76, 70, 15);
        panel.add(lblNewLabel_2);

        txtComment1 = new JTextField();
        txtComment1.setBounds(120, 76, 193, 47);
        panel.add(txtComment1);

        JLabel label_6 = new JLabel("车辆编号:");
        label_6.setBounds(34, 126, 90, 20);
        panel.add(label_6);

        listDbs = new JComboBox();
        listDbs.setModel(new DefaultComboBoxModel(
                new Cardao().loadid()));
        listDbs.setBounds(120, 126, 166, 21);
        panel.add(listDbs);

        btnAdd = new JButton("调配");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(139, 220, 93, 23);
        getContentPane().add(btnAdd);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(371, 220, 93, 23);
        getContentPane().add(btnCancel);

        this.setLocationRelativeTo(this.drInfoFrame);
        this.setModal(true);
        this.setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            String qdian=txtComment.getText();
            String zdian=txtComment1.getText();
            int carid=(int)listDbs.getSelectedItem();
            int fdh=fhd;
            DeliveryReceipt deliveryReceipt=new DeliveryReceipt(fdh,carid,qdian,zdian);
            new DRdao().updateDR(deliveryReceipt);
            drInfoFrame.loadDR();
            this.dispose();
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
}
