package frame.DistributionSites.DbSscope;

import com.sun.xml.internal.ws.api.server.LazyMOMProvider;
import dao.DistributionSites.DistributionScope.DistributionScopedao;
import dao.DistributionSites.DistributionSitesdao;
import entity.DistributionScope;
import frame.DistributionSites.DbSInfoFrame;
import frame.system.MainFrame;
import util.TableSetUtil;

import javax.swing.*;

import javax.swing.table.DefaultTableModel;
import java.awt.*;


/**
 * Created by 59480 on 2017/3/21.
 */
public class ShowscopeDialog extends JDialog  {
    private String scopename;
    private JTable table;
    private DbSInfoFrame dbSInfoFrame;


    public ShowscopeDialog(DbSInfoFrame dbSInfoFrame, String scopename) {

        this.dbSInfoFrame=dbSInfoFrame;
        this.scopename=scopename;

            setBounds(100, 100, 658, 498);
            getContentPane().setLayout(null);

            JPanel panel = new JPanel();
            panel.setBounds(0, 0, 642, 42);
            getContentPane().add(panel);
            panel.setLayout(null);
            JLabel lblNewLabel = new JLabel("配送点配送范围信息");
            lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
            lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
            lblNewLabel.setBounds(0, 0, 640, 42);
            panel.add(lblNewLabel);

            JScrollPane scrollPane = new JScrollPane();
            scrollPane.setBounds(0, 43, 642, 383);
            getContentPane().add(scrollPane);

            String[] header = { "编号","名称","首公斤价格","次公斤价格","次立方价格","配送时间/天","对应配送点名称","备注信息" };
            DefaultTableModel dtm = new DefaultTableModel(null, header);
            table = new JTable(dtm) {
                public boolean isCellEditable(int row, int column) {
                    return false;
                };
            };
            String[] text = { "编号","名称","首公斤价格","次公斤价格","次立方价格","配送时间/天","对应配送点名称","备注信息" };
            TableSetUtil.setTextCellRenderer(table, text, null);
            scrollPane.setViewportView(table);

            loadScope();

        this.setLocationRelativeTo(this.dbSInfoFrame);
        this.setModal(true);
        this.setVisible(true);
    }


    /**
     * 加载Dbs到table
     */
    public void loadScope() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        DistributionScopedao distributionScopedao=new DistributionScopedao();
        java.util.List<DistributionScope> dbsList = distributionScopedao.loadnameList(scopename);
        dtm.setRowCount(0);
        for (DistributionScope distributionScope : dbsList) {
            dtm.addRow(new Object[] { distributionScope.getDbscopeId()
                    ,distributionScope.getDbscopeName()
                    ,distributionScope.getTheFirstKgPrice()
                    ,distributionScope.getTimeKgPrice()
                    ,distributionScope.getACubicPrices()
                    ,distributionScope.getDeliveryTime()
                    ,distributionScope.getDbsname()
                    ,distributionScope.getPs()
            });
        }
    }
}
