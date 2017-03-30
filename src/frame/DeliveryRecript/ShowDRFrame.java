package frame.DeliveryRecript;

import dao.DeliveryRecript.DRdao;
import entity.DeliveryReceipt;
import frame.system.MainFrame;
import util.TableSetUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 * Created by 59480 on 2017/3/21.
 */
public class ShowDRFrame extends JInternalFrame  {

    private JTable table;
    private MainFrame mf;

    public ShowDRFrame(MainFrame mf) {
        super("交接单查看", true, true, false, true);

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

        String[] header = {"单号", "货号清单", "收寄地", "交接地", "日期", "运输车号", "承运人", "接收人", "路线起点", "路线终点", "车辆编号"};
        DefaultTableModel dtm = new DefaultTableModel(null, header);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }

            ;
        };
        String[] text = {"单号", "货号清单", "收寄地", "交接地", "日期", "运输车号", "承运人", "接收人", "路线起点", "路线终点", "车辆编号"};
        TableSetUtil.setTextCellRenderer(table, text, null);
        scrollPane.setViewportView(table);


        loadDR();

        int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
        int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
        this.setLocation(width, height);
        this.setResizable(false);
        this.setVisible(true);

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
