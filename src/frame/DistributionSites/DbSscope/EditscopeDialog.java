package frame.DistributionSites.DbSscope;

import dao.DistributionSites.DistributionScope.DistributionScopedao;
import dao.DistributionSites.DistributionSitesdao;
import entity.DistributionScope;


import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by 59480 on 2017/3/20.
 */
public class EditscopeDialog extends JDialog implements ActionListener {

    private int scopeid;
    private JButton btnEdit;
    private JButton btnCancel;
    private JTextField  txtComment;
    private JTextField txtTime;
    private JComboBox listDbs;
    private JTextField txtScubeP;
    private JTextField txtSKgP;
    private JTextField txtFKgP;
    private JTextField txtName;
    private JTextField txtid;
    private ScopeInfoFrame scopeinfoframe;

    public EditscopeDialog(ScopeInfoFrame scopeInfoFrame,int scopeid){
        this.scopeinfoframe=scopeInfoFrame;
        this.scopeid=scopeid;
        setTitle("修改配送范围");
        setBounds(100, 100, 614, 600);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "配送范围信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
        panel.setBounds(0, 0, 598, 450);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel label3 = new JLabel("编号:");
        label3.setBounds(34, 26, 70, 20);
        panel.add(label3);

        txtid = new JTextField();
        txtid.setColumns(10);
        txtid.setBounds(120, 26, 153, 21);
        panel.add(txtid);

        JLabel label = new JLabel("名称:");
        label.setBounds(34, 76, 70, 20);
        panel.add(label);

        txtName = new JTextField();
        txtName.setColumns(10);
        txtName.setBounds(120, 76, 153, 21);
        panel.add(txtName);

        JLabel label1 = new JLabel("首公斤价格:");
        label1.setBounds(34, 126, 70, 20);
        panel.add(label1);

        txtFKgP = new JTextField();
        txtFKgP.setColumns(10);
        txtFKgP.setBounds(120, 126, 153, 21);
        panel.add(txtFKgP);

        JLabel label2 = new JLabel("次公斤价格:");
        label2.setBounds(34, 176, 70, 20);
        panel.add(label2);

        txtSKgP= new JTextField();
        txtSKgP.setColumns(10);
        txtSKgP.setBounds(120, 176, 153, 21);
        panel.add(txtSKgP);

        JLabel label5 = new JLabel("次立方价格:");
        label5.setBounds(34, 226, 70, 20);
        panel.add(label5);

        txtScubeP = new JTextField();
        txtScubeP.setColumns(10);
        txtScubeP.setBounds(120, 226, 153, 21);
        panel.add(txtScubeP);

        JLabel label_6 = new JLabel("对应配送点名称:");
        label_6.setBounds(34, 326, 90, 20);
        panel.add(label_6);

        listDbs = new JComboBox();
        listDbs.setModel(new DefaultComboBoxModel(
                new DistributionSitesdao().loadName()));
        listDbs.setBounds(120, 326, 166, 21);
        panel.add(listDbs);

        JLabel label_7 = new JLabel("配送时间/天:");
        label_7.setBounds(34, 276, 80, 20);
        panel.add(label_7);

        txtTime = new JTextField();
        txtTime.setColumns(10);
        txtTime.setBounds(120, 276, 153, 21);
        panel.add(txtTime);

        JLabel lblNewLabel_1 = new JLabel("备注");
        lblNewLabel_1.setBounds(34, 376, 70, 15);
        panel.add(lblNewLabel_1);

        txtComment = new JTextField();
        txtComment.setBounds(120, 376, 193, 47);
        panel.add(txtComment);


        btnEdit = new JButton("修改");
        btnEdit.addActionListener(this);
        btnEdit.setBounds(139, 470, 93, 23);
        getContentPane().add(btnEdit);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(371, 470, 93, 23);
        getContentPane().add(btnCancel);

        DistributionScopedao scopedao=new DistributionScopedao();
        DistributionScope distributionScope=scopedao.find(scopeid);
        txtid.setText(String.valueOf(distributionScope.getDbscopeId()));
        txtName.setText(distributionScope.getDbscopeName());
        txtFKgP.setText(String.valueOf(distributionScope.getTheFirstKgPrice()));
        txtSKgP.setText(String.valueOf(distributionScope.getTimeKgPrice()));
        txtScubeP.setText(String.valueOf(distributionScope.getACubicPrices()));
        txtTime.setText(String.valueOf(distributionScope.getDeliveryTime()));
        txtComment.setText(String.valueOf(distributionScope.getPs()));


        this.setLocationRelativeTo(this.scopeinfoframe);
        this.setModal(true);
        this.setVisible(true);


    }

   @Override
    public void actionPerformed(ActionEvent e) {
       if(e.getSource()==btnEdit){
           int dbscopeId = Integer.parseInt(txtid.getText());
           String dbscopeName = txtName.getText();
           double theFirstKgPrice = Double.parseDouble(txtFKgP.getText());
           double timeKgPrice = Double.parseDouble(txtSKgP.getText());
           double ACubicPrices = Double.parseDouble(txtScubeP.getText());
           int deliveryTime = Integer.parseInt(txtTime.getText());
           String ps = txtComment.getText();
           String Dbsname=(String) listDbs.getSelectedItem();

           int result = JOptionPane.showConfirmDialog(
               this,
               "确认修改该配送范围的信息吗？",
               "温馨提示",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.QUESTION_MESSAGE);
       if(result==JOptionPane.YES_NO_OPTION){
           DistributionScope distributionScope = new DistributionScope(dbscopeId,dbscopeName,theFirstKgPrice,timeKgPrice,ACubicPrices,deliveryTime,ps,Dbsname,scopeid);
           DistributionScopedao scopedao=new DistributionScopedao();
           scopedao.updatescope(distributionScope);
           scopedao.loadScopeList();
           scopeinfoframe.loadScope();
           this.dispose();
       }

   }else if (e.getSource() == btnCancel) {
        this.dispose();
    }
    }
}
