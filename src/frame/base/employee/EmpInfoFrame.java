package frame.base.employee;


import dao.base.Employee.Employeedao;
import entity.Employee;

import frame.system.MainFrame;

import util.TableSetUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by 59480 on 2017/3/21.
 */
public class EmpInfoFrame extends JInternalFrame implements ActionListener {

    private JButton btnCancel;
    private JButton btnDel;
    private JButton btnEdit;
    private JButton btnAdd;
    private JTable table;
    private MainFrame mf;

    public EmpInfoFrame(MainFrame mf) {

        super("员工管理", true, true, false, true);

        this.mf = mf;

        setBounds(100, 100, 658, 498);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 642, 42);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("员工资料");
        lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 640, 42);
        panel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 43, 642, 383);
        getContentPane().add(scrollPane);

        String[] header = {"员工编号", "姓名", "性别", "电话", "职位"};
        DefaultTableModel dtm = new DefaultTableModel(null, header);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        String[] text = {"员工编号", "姓名", "性别", "电话", "职位"};
        TableSetUtil.setTextCellRenderer(table, text, null);
        scrollPane.setViewportView(table);

        btnAdd = new JButton("增加");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(54, 436, 93, 23);
        getContentPane().add(btnAdd);

        btnEdit = new JButton("修改");
        btnEdit.addActionListener(this);
        btnEdit.setBounds(201, 436, 93, 23);
        getContentPane().add(btnEdit);

        btnDel = new JButton("删除");
        btnDel.addActionListener(this);
        btnDel.setBounds(348, 436, 93, 23);
        getContentPane().add(btnDel);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(495, 436, 93, 23);
        getContentPane().add(btnCancel);

        // 启动加载
        loademp();

        int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
        int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
        this.setLocation(width, height);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new AddempDialog(this);
        } else if (e.getSource() == btnEdit) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this, "请选择要修改的员工!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int empid = (Integer) table.getValueAt(rowCount, 0);
            new EditempDialog(this, empid);
        } else if (e.getSource() == btnDel) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this, "请选择要删除的员工!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int empid = (Integer) table.getValueAt(rowCount, 0);
            int result = JOptionPane.showConfirmDialog(this,
                    "确认删除 " + table.getValueAt(rowCount, 1), "温馨提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // 调用DAD层删除
                new Employeedao().delemp(empid);
                loademp();
            }
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }

    void loademp() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        Employeedao employeedao=new Employeedao();
        java.util.List<Employee> empList = employeedao.loadempList();
        dtm.setRowCount(0);

        for (Employee employee : empList) {
            dtm.addRow(new Object[]{employee.getEmployeeId(),employee.getName(),employee.getSex(),employee.getPhone(),employee.getZhiwei()});
        }
    }
}