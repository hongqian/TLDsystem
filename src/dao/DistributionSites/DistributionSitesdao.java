package dao.DistributionSites;

import dao.DBUtil;

import entity.DistributionSites;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import java.util.List;

/**
 * Created by 59480 on 2017/3/20.
 */
public class DistributionSitesdao {
    /**
     * 添加新的配送点到数据库
     *
     * @param distributionSites
     */
    public void addDbsInfo(DistributionSites distributionSites) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "insert into dbsxx( "+"DbsId, "
                    + "Dbsname, " + "Dbsaddress, " + "Dbsgm, "
                    + "ps ) "
                    + "values(?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setInt(1,distributionSites.getDbsId());
            stat.setString(2, distributionSites.getDbsName());
            stat.setString(3, distributionSites.getDbsaddress());
            stat.setString(4, distributionSites.getDbsScale());
            stat.setString(5, distributionSites.getPs());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 删除指定id的配送点
     *
     * @param DbsId
     */
    public void deldbs(int DbsId) {
        String sql = "delete from dbsxx where DbsId = '" + DbsId + "'";
        DBUtil.executeUpdate(sql);
    }

    /**
     * 修改指定配送点信息
     *
     * @param distributionSites
     */

    public void updatedbs(DistributionSites distributionSites) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "update dbsxx set "+" DbsId = ?,"+" Dbsname = ?,"
                    + " Dbsaddress = ?," + " Dbsgm = ?,"
                    + " ps = ?"
                    + " where DbsId = ? ";
            stat = con.prepareStatement(sql);
            stat.setInt(1,distributionSites.getDbsId());
            stat.setString(2,distributionSites.getDbsName());
            stat.setString(3,distributionSites.getDbsaddress());
            stat.setString(4,distributionSites.getDbsScale());
            stat.setString(5,distributionSites.getPs());
            stat.setInt(6, distributionSites.getDbsId1());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 查找
     * @param DbsId
     * @return
     */
    public DistributionSites find(int DbsId){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;

        DistributionSites distributionSites = null;
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM dbsxx"
                    + " WHERE DbsId = "+DbsId ;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                String Dbsname = rs.getString("Dbsname");
                String Dbsaddress = rs.getString("Dbsaddress");
                String Dbsgm = rs.getString("Dbsgm");
                String ps = rs.getString("ps");

                distributionSites = new DistributionSites(Dbsname, Dbsaddress, Dbsgm, ps);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return distributionSites;
    }

    /**
     * 加载dbs到list
     *
     * @return
     */
    public List<DistributionSites> loaddbsList() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<DistributionSites> dbsList = new ArrayList<DistributionSites>();

        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM dbsxx"
                    + " order by DbsId desc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int DbsId = rs.getInt("DbsId");
                String Dbsname = rs.getString("Dbsname");
                String Dbsaddress = rs.getString("Dbsaddress");
                String Dbsgm = rs.getString("Dbsgm");
                String ps = rs.getString("ps");

                DistributionSites distributionSites = new DistributionSites(DbsId, Dbsname, Dbsaddress, Dbsgm, ps);
                dbsList.add(distributionSites);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return dbsList;

    }

        public String[] loadName() {
            Connection con = null;
            Statement stat = null;
            ResultSet rs = null;
            List rl = new ArrayList();
            try {
                con = DBUtil.getConn();
                stat = con.createStatement();
                String sql = "select Dbsname from dbsxx";
                rs = stat.executeQuery(sql);

                while (rs.next()) {
                    String DbsName = rs.getString("Dbsname");
                    rl.add(DbsName);
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                DBUtil.closeConn(con, stat, rs);

            }
            String[] s=new String[rl.size()];
            for(int i=0;i<rl.size();i++){
                s[i]=(String)rl.get(i);
            }
            return s;
        }
    }


