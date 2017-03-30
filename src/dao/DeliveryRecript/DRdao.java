package dao.DeliveryRecript;

import dao.DBUtil;
import entity.DeliveryReceipt;
import entity.Role;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 59480 on 2017/3/21.
 */
public class DRdao {
    /**
     * 修改指定id的DR
     *
     * @param deliveryReceipt
     */

    public void updateDR(DeliveryReceipt deliveryReceipt) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "update psdan set carId = ?,"
                    + " luq = ?," + " luz = ?"
                    + " where fdh = ? ";
            stat = con.prepareStatement(sql);
            stat.setInt(1, deliveryReceipt.getCarId());
            stat.setString(2, deliveryReceipt.getTheStartingPointLine());
            stat.setString(3, deliveryReceipt.getLineAtTheEndOf());
            stat.setInt(4, deliveryReceipt.getSealNumber());

            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 按指定id查找交接单
     *
     * @param id
     * @return
     */
    public DeliveryReceipt find(int id) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        DeliveryReceipt DR = null;
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select * from psdan "+"WHERE fdh = "+ id;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String qd=rs.getString("qd");
                String sjdi=rs.getString("jjdi");
                String jjdi=rs.getString("jjdi");
                Date psdate=rs.getDate("psdate");
                String ysc=rs.getString("ysc");
                String cyr=rs.getString("cyr");
                String jsr=rs.getString("jsr");
                int carid=rs.getInt("carid");
                String luq=rs.getString("luq");
                String luz=rs.getString("luz");
                DR=new DeliveryReceipt(qd,sjdi,jjdi,psdate,ysc,cyr,jsr,carid,luq,luz);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return DR ;
    }

    /**
     * 删除指定id的交接单
     *
     * @param fdh
     */
    public void delDR(int fdh) {
        String sql = "delete from psdan where fdh = '" + fdh + "'";
        DBUtil.executeUpdate(sql);
    }

    /**
     * 添加交接单
     *
     * @param deliveryReceipt
     */
    public void addDR(DeliveryReceipt deliveryReceipt) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "insert into psdan" + "(fdh, qd,"
                    + " sjdi,"+" jjdi," + " psdate,"+" ysc,"+" cyr,"+" jsr"+") values"
                    + "(?,?,?,?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setInt(1,deliveryReceipt.getSealNumber());
            stat.setString(2,deliveryReceipt.getTheArticleNumberList());
            stat.setString(3,deliveryReceipt.getPostingTo());
            stat.setString(4,deliveryReceipt.getTransitionTo());
            stat.setDate(5,new java.sql.Date(deliveryReceipt.getDRTime().getTime()));
            stat.setString(6,deliveryReceipt.getTCarNo());
            stat.setString(7,deliveryReceipt.getCarrier());
            stat.setString(8,deliveryReceipt.getRecipient());

            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 加载DeliveryReceipt到list
     *
     * @return
     */
    public List<DeliveryReceipt> loadDRList() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<DeliveryReceipt> DRList = new ArrayList<DeliveryReceipt>();
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select * " + "from psdan "
                    + "order by fdh desc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("fdh");
                String qd=rs.getString("qd");
                String sjdi=rs.getString("jjdi");
                String jjdi=rs.getString("jjdi");
                Date psdate=rs.getDate("psdate");
                String ysc=rs.getString("ysc");
                String cyr=rs.getString("cyr");
                String jsr=rs.getString("jsr");
                int carid=rs.getInt("carid");
                String luq=rs.getString("luq");
                String luz=rs.getString("luz");

                DeliveryReceipt DR=new DeliveryReceipt(id,qd,sjdi,jjdi,psdate,ysc,cyr,jsr,carid,luq,luz);
                DRList.add(DR);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return DRList;
    }
}
