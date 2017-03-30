package frame.DeliveryRecript;

import dao.DeliveryRecript.DRdao;

import entity.DeliveryReceipt;

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
public class DRInfoFrame extends JInternalFrame implements ActionListener {


    private JButton btnCancel;
    private JButton btnDel;
    private JButton btndeploy;
    private JButton btnAdd;
    private JTable table;
    private MainFrame mf;

    public DRInfoFrame(MainFrame mf) {
        super("交接单管理", true, true, false, true);

        this.mf = mf;

        setBounds(100, 100, 820, 498);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 800, 42);
        getContentPane().add(panel);
        panel.setLayout(null);
        JLabel lblNewLabel = new JLabel("交接单信息");
        lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 800, 42);
        panel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 43, 800, 383);
        getContentPane().add(scrollPane);

        String[] header = { "单号","货号清单","收寄地","交接地","日期","运输车号","承运人","接收人","路线起点","路线终点","车辆编号" };
        DefaultTableModel dtm = new DefaultTableModel(null, header);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        String[] text = { "单号","货号清单","收寄地","交接地","日期","运输车号","承运人","接收人","路线起点","路线终点","车辆编号"};
        TableSetUtil.setTextCellRenderer(table, text, null);
        scrollPane.setViewportView(table);

        btnAdd = new JButton("增加");
        btnAdd.addActionListener(this);
        btnAdd.setBounds(54, 436, 93, 23);
        getContentPane().add(btnAdd);

        btndeploy = new JButton("调配");
        btndeploy.addActionListener(this);
        btndeploy.setBounds(201, 436, 93, 23);
        getContentPane().add(btndeploy);

        btnDel = new JButton("删除");
        btnDel.addActionListener(this);
        btnDel.setBounds(348, 436, 93, 23);
        getContentPane().add(btnDel);

        btnCancel = new JButton("取消");
        btnCancel.addActionListener(this);
        btnCancel.setBounds(495, 436, 93, 23);
        getContentPane().add(btnCancel);

        loadDR();

        int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
        int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
        this.setLocation(width, height);
        this.setResizable(false);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new AddDRDialog(this);
        } else if (e.getSource() == btndeploy) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this, "请选择要修改的员工!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (Integer) table.getValueAt(rowCount, 0);
            new EditDRDialog(this, id);
        } else if (e.getSource() == btnDel) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this, "请选择要删除的员工!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = (Integer) table.getValueAt(rowCount, 0);
            int result = JOptionPane.showConfirmDialog(this,
                    "确认删除 " + table.getValueAt(rowCount, 1), "温馨提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // 调用DAD层删除
                new DRdao().delDR(id);
                loadDR();
            }
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
    void loadDR() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        DRdao drdao=new DRdao();
        java.util.List<DeliveryReceipt> empList = drdao.loadDRList();
        dtm.setRowCount(0);

        for (DeliveryReceipt deliveryReceipt : empList) {
            dtm.addRow(new Object[]{deliveryReceipt.getSealNumber()
                    ,deliveryReceipt.getTheArticleNumberList()
                    ,deliveryReceipt.getPostingTo()
                    ,deliveryReceipt.getTransitionTo()
                    ,deliveryReceipt.getDRTime()
                    ,deliveryReceipt.getTCarNo()
                    ,deliveryReceipt.getCarrier()
                    ,deliveryReceipt.getRecipient()
                    ,deliveryReceipt.getTheStartingPointLine()
                    ,deliveryReceipt.getLineAtTheEndOf()
                    ,deliveryReceipt.getCarId()});
        }
    }
}
