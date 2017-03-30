package frame.DistributionSites;

import dao.DistributionSites.DistributionSitesdao;
import entity.DistributionSites;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 59480 on 2017/3/20.
 */
public class AddDbsDialog extends JDialog implements ActionListener {


    private JTextField txtid;
    private JTextField txtScale;
    private JTextField txtaddess;
    private JTextField txtName;

    private JButton btnCancel;
    private JTextArea txtComment;
    private JButton btnAdd;
    private DbSInfoFrame dbsinfoframe;

    public AddDbsDialog(DbSInfoFrame dbsinfoframe) {
       this.dbsinfoframe=dbsinfoframe;

        setTitle("添加配送点");
        setBounds(100, 100, 614, 450);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "配送点信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(0, 0, 598, 346);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label3 = new JLabel("编号:");
        label3.setBounds(34, 26, 54, 20);
        panel.add(label3);

        txtid = new JTextField();
        txtid.setColumns(10);
        txtid.setBounds(108, 26, 153, 21);
        panel.add(txtid);

        JLabel label = new JLabel("名称:");
        label.setBounds(34, 76, 54, 20);
        panel.add(label);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(108, 76, 153, 21);
        panel.add(txtName);

        JLabel label1 = new JLabel("地址:");
        label1.setBounds(34, 126, 54, 20);
        panel.add(label1);

        txtaddess = new JTextField();
        txtaddess.setColumns(10);
        txtaddess.setBounds(108, 126, 153, 21);
        panel.add(txtaddess);

        JLabel label2 = new JLabel("规模:");
        label2.setBounds(34, 176, 54, 20);
        panel.add(label2);

        txtScale = new JTextField();
        txtScale.setColumns(10);
        txtScale.setBounds(108, 176, 153, 21);
        panel.add(txtScale);

        JLabel lblNewLabel_1 = new JLabel("备注");
        lblNewLabel_1.setBounds(34, 226, 54, 15);
        panel.add(lblNewLabel_1);

        txtComment = new JTextArea();
        txtComment.setBounds(108, 226, 193, 47);
        panel.add(txtComment);


        btnAdd = new JButton("添加");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(139, 356, 93, 23);
        getContentPane().add(btnAdd);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(371, 356, 93, 23);
        getContentPane().add(btnCancel);

        this.setLocationRelativeTo(this.dbsinfoframe);
        this.setModal(true);
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {

            String id=txtid.getText();
            Pattern p = Pattern.compile("\\d{1,12}");
            Matcher m = p.matcher(txtid.getText());
            if (!m.matches()) {
                JOptionPane.showMessageDialog(this, "编号输入有误请重新输入！");
                return;
            }
            String name = txtName.getText();
            String addess = txtaddess.getText();
            String scale = txtScale.getText();
            String ps = txtComment.getText();

            DistributionSites distributionSites = new DistributionSites(Integer.parseInt(id) ,name,addess,scale,ps);
            // 调用DAO层添加
            DistributionSitesdao distributionSitesdao=new DistributionSitesdao();
            distributionSitesdao.addDbsInfo(distributionSites);
            // 刷新
            dbsinfoframe.loadDbS();
            this.dispose();
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }


        }
    }

