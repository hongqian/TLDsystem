package frame.base.employee;

import dao.base.Employee.Employeedao;
import entity.Employee;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 59480 on 2017/3/21.
 */
public class EditempDialog extends JDialog implements ActionListener {
    private int empid;
    private JButton btnEdit;
    private JComboBox listsex;
    private EmpInfoFrame empInfoFrame;
    private JTextField txtid;
    private JTextField txtphone;

    private JTextField txtName;

    private JButton btnCancel;
    private JTextField txtComment;



    public EditempDialog(EmpInfoFrame empInfoFrame,int empid) {
        this.empInfoFrame=empInfoFrame;

        this.empid=empid;

        setTitle("修改员工");
        setBounds(100, 100, 614, 450);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "员工信息", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
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

        JLabel label1 = new JLabel("性别:");
        label1.setBounds(34, 126, 54, 20);
        panel.add(label1);

        listsex = new JComboBox();
        listsex.setModel(new DefaultComboBoxModel(
                new String[] { "男", "女" }));
        listsex.setBounds(108, 126, 153, 21);
        panel.add(listsex);

        JLabel label2 = new JLabel("电话号码:");
        label2.setBounds(34, 176, 54, 20);
        panel.add(label2);

        txtphone = new JTextField();
        txtphone.setColumns(10);
        txtphone.setBounds(108, 176, 153, 21);
        panel.add(txtphone);

        JLabel lblNewLabel_1 = new JLabel("职位");
        lblNewLabel_1.setBounds(34, 226, 54, 15);
        panel.add(lblNewLabel_1);

        txtComment = new JTextField();
        txtComment.setBounds(108, 226, 193, 47);
        panel.add(txtComment);


        btnEdit = new JButton("添加");
        btnEdit.addActionListener(this);
        btnEdit.setBounds(139, 356, 93, 23);
        getContentPane().add(btnEdit);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(371, 356, 93, 23);
        getContentPane().add(btnCancel);

        Employee employee=new Employeedao().find(empid);
        txtid.setText(String.valueOf(employee.getEmployeeId()));
        txtName.setText(employee.getName());
        listsex.setSelectedItem(employee.getSex());
        txtphone.setText(String.valueOf(employee.getPhone()));
        txtComment.setText(employee.getZhiwei());

        this.setLocationRelativeTo(this.empInfoFrame);
        this.setModal(true);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnEdit) {

            String id=txtid.getText();
            Pattern p = Pattern.compile("\\d{1,12}");
            Matcher m = p.matcher(txtid.getText());
            if (!m.matches()) {
                JOptionPane.showMessageDialog(this, "编号输入有误请重新输入！");
                return;
            }
            String name = txtName.getText();
            String sex = (String)listsex.getSelectedItem();
            int phone = Integer.parseInt(txtphone.getText());
            String ps = txtComment.getText();

            Employee employee=new Employee(Integer.parseInt(id),name,sex,phone,ps,empid);
            // 刷新
            new Employeedao().updateEmp(employee);
            empInfoFrame.loademp();

            this.dispose();
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
}
