package frame.DistributionSites;

import dao.DistributionSites.DistributionSitesdao;

import entity.DistributionSites;


import frame.DistributionSites.DbSscope.ShowscopeDialog;
import frame.system.MainFrame;

import util.TableSetUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


/**
 * Created by 59480 on 2017/3/20.
 */
public class DbSInfoFrame extends JInternalFrame implements ActionListener {
    private JTable table;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDel;
    private JButton btnCancel;
    private MainFrame mf;

    public DbSInfoFrame(MainFrame mf) {
        super("配送点管理", true, true, false, true);
        this.mf = mf;
        setBounds(100, 100, 658, 498);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 642, 42);
        getContentPane().add(panel);
        panel.setLayout(null);
        JLabel lblNewLabel = new JLabel("配送点信息");
        lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 640, 42);
        panel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 43, 642, 383);
        getContentPane().add(scrollPane);

                String[] header = { "编号","名称","地址","规模","备注信息" };
        DefaultTableModel dtm = new DefaultTableModel(null, header);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        //双击事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int rowCount = table.getSelectedRow();
                    String scopename = (String) table.getValueAt(rowCount, 1);
                    new ShowscopeDialog(DbSInfoFrame.this, scopename);
                    //new AboutDialog(mf);
                }
            }
        });

        String[] text = { "编号","名称","地址","规模","备注信息" };
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

        loadDbS();


        int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
        int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
        this.setLocation(width, height);
        this.setResizable(false);
        this.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new AddDbsDialog(this);
        } else if (e.getSource() == btnEdit) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(
                        this, "请选择要修改的配送点!"
                        , "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
           int DbsId = (int) table.getValueAt(rowCount, 0);
            new EditDbSDialog(this,DbsId);
        } else if (e.getSource() == btnDel) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this
                        , "请选择要删除的配送点!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int DbsId = (int) table.getValueAt(rowCount, 0);
            int result = JOptionPane.showConfirmDialog(this,
                    "确认删除	该配送点的信息吗?", "温馨提示",
                    JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION) {
                DistributionSitesdao distributionSitesdao=new DistributionSitesdao();
                distributionSitesdao.deldbs(DbsId);
                loadDbS();
            }
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }

    /**
     * 加载Dbs到table
     */
    public void loadDbS() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        DistributionSitesdao distributionSitesdao = new DistributionSitesdao();
        java.util.List<DistributionSites> dbsList = distributionSitesdao.loaddbsList();
        dtm.setRowCount(0);
        for (DistributionSites distributionSites : dbsList) {
            dtm.addRow(new Object[] { distributionSites.getDbsId()
                    ,distributionSites.getDbsName()
                    ,distributionSites.getDbsaddress()
                    ,distributionSites.getDbsScale()
                    ,distributionSites.getPs() });
        }
    }
    }

