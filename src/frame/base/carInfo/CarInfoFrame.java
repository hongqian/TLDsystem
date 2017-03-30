package frame.base.carInfo;



import dao.car.Cardao;
import entity.Car;
import frame.system.MainFrame;
import util.TableSetUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


/**
 * Created by 59480 on 2017/3/19.
 */
public class CarInfoFrame extends JInternalFrame implements ActionListener {
    private JTable table;
    private JButton btnAdd;
    private JButton btnEdit;
    private JButton btnDel;
    private JButton btnCancel;
    private MainFrame mf;

    /**
     * Create the frame.
     */
    public CarInfoFrame(MainFrame mf) {
        super("车辆管理", true, true, false, true);
        this.mf = mf;
        setBounds(100, 100, 658, 498);
        getContentPane().setLayout(null);

        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 642, 42);
        getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel = new JLabel("车辆管理");
        lblNewLabel.setFont(new Font("隶书", Font.BOLD | Font.ITALIC, 36));
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setBounds(0, 0, 640, 42);
        panel.add(lblNewLabel);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0, 43, 642, 383);
        getContentPane().add(scrollPane);

        String[] header = { "车辆编号","购买时间","生产厂家","型号","载重量","体积","状态" };
        DefaultTableModel dtm = new DefaultTableModel(null, header);
        table = new JTable(dtm) {
            public boolean isCellEditable(int row, int column) {
                return false;
            };
        };
        String[] text = { "车辆编号","购买时间","生产厂家","型号","载重量","体积","状态" };
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
        loadcar();

        int width = (this.mf.getWidth() - this.getWidth() - 200) / 2;
        int height = (this.mf.getHeight() - this.getHeight() - 140) / 2;
        this.setLocation(width, height);
        this.setResizable(false);
        this.setVisible(true);
    }

    /**
     * 加载
     */
    public void loadcar() {
        DefaultTableModel dtm = (DefaultTableModel) table.getModel();
        Cardao cardao = new Cardao();
        java.util.List<Car> carList = cardao.loadcarList();
        dtm.setRowCount(0);
        for (Car car : carList) {
            dtm.addRow(new Object[] { car.getCarId(),car.getBuyT(),car.getManufacturer(),
                   car.getType(),car.getLoadCapacity(),car.getCube() ,car.getCarstate() == 1 ? "空闲" : "运货"});
        }
    }

    /**
     * 事件处理
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAdd) {
            new AddCarDialog(this);
        } else if (e.getSource() == btnEdit) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this, "请选择要修改的车辆!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int carId = (Integer) table.getValueAt(rowCount, 0);
            new EditCarDialog(this,carId);
        } else if (e.getSource() == btnDel) {
            int rowCount = table.getSelectedRow();
            if (rowCount == -1) {
                JOptionPane.showMessageDialog(this, "请选择要删除的车辆!", "温馨提示",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
            int carId = (Integer) table.getValueAt(rowCount, 0);
            int result = JOptionPane.showConfirmDialog(this,
                    "确认删除 " + table.getValueAt(rowCount, 1), "温馨提示",
                    JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            if (result == JOptionPane.YES_OPTION) {
                // 调用DAD层删除
                Cardao cardao = new Cardao();
                cardao.delcar(carId);
                loadcar();
            }
        } else if (e.getSource() == btnCancel) {
            this.dispose();
        }
    }
}
