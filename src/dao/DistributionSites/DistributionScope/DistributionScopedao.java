package dao.DistributionSites.DistributionScope;

import dao.DBUtil;
import entity.DistributionScope;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59480 on 2017/3/20.
 */
public class DistributionScopedao {

    /**
     * 添加新的配送范围到数据库
     *
     * @param distributionScope
     */
    public void addScopeInfo(DistributionScope distributionScope) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "insert into dbss( "+"DBSsId, "
                    + "fwname, " + "fKgp, " + "sKgp, "
                    + "sCubep, "+" time1,"+" ps,"+" Dbsname)"
                    + " values(?,?,?,?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setInt(1,distributionScope.getDbscopeId());
            stat.setString(2,distributionScope.getDbscopeName());
            stat.setDouble(3,distributionScope.getTheFirstKgPrice());
            stat.setDouble(4,distributionScope.getTimeKgPrice());
            stat.setDouble(5,distributionScope.getACubicPrices());
            stat.setInt(6,distributionScope.getDeliveryTime());
            stat.setString(7,distributionScope.getPs());
            stat.setString(8,distributionScope.getDbsname());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 加载配送范围到list
     *
     */
    public List<DistributionScope> loadScopeList() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<DistributionScope> Scopelist=new ArrayList<DistributionScope>();
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM dbss"
                        + " order by DBSsId desc";
            rs = stat.executeQuery(sql);
            while (rs.next()){
                int DbscopeId=rs.getInt("DBSsId");
                String DbscopeName=rs.getString("fwname");
                double TheFirstKgPrice=rs.getDouble("fKgp");
                double TimeKgPrice=rs.getDouble("sKgp");
                double ACubicPrices=rs.getDouble("sCubep");
                int DeliveryTime=rs.getInt("time1");
                String ps=rs.getString("ps");
                String dbsname=rs.getString("Dbsname");

                //打包
                DistributionScope distributionScope=
                        new DistributionScope(DbscopeId
                        ,DbscopeName
                        ,TheFirstKgPrice
                        ,TimeKgPrice
                        ,ACubicPrices
                        ,DeliveryTime
                        ,ps
                        ,dbsname);
                Scopelist.add(distributionScope);

            }
            } catch (SQLException e) {
                e.printStackTrace();
            }finally {
            DBUtil.closeConn(con, stat, rs);
        }

        return Scopelist;
    }


    /**
     * 修改指定配送范围信息
     *
     * @param distributionScope
     */
    public void updatescope(DistributionScope distributionScope) {



            Connection con = null;
            PreparedStatement stat = null;
            try {
                con = DBUtil.getConn();
                String sql = "update dbss set "+" DBSsId = ?,"+" fwname = ?,"
                        + " fKgp = ?," + " sKgp = ?,"+ " sCubep = ?,"+ " time1 = ?,"
                        + " ps = ?"+ " Dbsname = ?,"
                        + " where DbsId = ? ";
                stat = con.prepareStatement(sql);
                stat.setInt(1,distributionScope.getDbscopeId());
                stat.setString(2,distributionScope.getDbscopeName());
                stat.setDouble(3,distributionScope.getTheFirstKgPrice());
                stat.setDouble(4,distributionScope.getTimeKgPrice());
                stat.setDouble(5,distributionScope.getACubicPrices());
                stat.setInt(6,distributionScope.getDeliveryTime());
                stat.setString(7,distributionScope.getPs());
                stat.setInt(8,distributionScope.getDbscopeId1());

                stat.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.closeConn(con, stat, null);
            }

    }
    /**
     * 查找
     * @param scope
     * @return
     */
    public DistributionScope find(int scope){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        DistributionScope distributionScope=null;
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM dbss"
                    + " WHERE DBSsId = "+scope;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int DbscopeId=rs.getInt("DBSsId");
                String DbscopeName=rs.getString("fwname");
                double TheFirstKgPrice=rs.getDouble("fKgp");
                double TimeKgPrice=rs.getDouble("sKgp");
                double ACubicPrices=rs.getDouble("sCubep");
                int DeliveryTime=rs.getInt("time1");
                String ps=rs.getString("ps");
                String dbsname=rs.getString("Dbsname");

                //打包
                distributionScope=
                        new DistributionScope(DbscopeId
                                ,DbscopeName
                                ,TheFirstKgPrice
                                ,TimeKgPrice
                                ,ACubicPrices
                                ,DeliveryTime
                                ,ps
                                ,dbsname);

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return distributionScope;
    }
    /**
     * 通过name相等加载配送范围到list
     *
     */
    public List<DistributionScope> loadnameList(String scopename) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<DistributionScope> Scopelist=new ArrayList<DistributionScope>();
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM dbss"+ " WHERE Dbsname = "+"'"+scopename+"'"
                    + " order by DBSsId desc";
            rs = stat.executeQuery(sql);
            while (rs.next()){
                int DbscopeId=rs.getInt("DBSsId");
                String DbscopeName=rs.getString("fwname");
                double TheFirstKgPrice=rs.getDouble("fKgp");
                double TimeKgPrice=rs.getDouble("sKgp");
                double ACubicPrices=rs.getDouble("sCubep");
                int DeliveryTime=rs.getInt("time1");
                String ps=rs.getString("ps");
                String dbsname=rs.getString("Dbsname");

                //打包
                DistributionScope distributionScope=
                        new DistributionScope(DbscopeId
                                ,DbscopeName
                                ,TheFirstKgPrice
                                ,TimeKgPrice
                                ,ACubicPrices
                                ,DeliveryTime
                                ,ps
                                ,dbsname);
                Scopelist.add(distributionScope);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.closeConn(con, stat, rs);
        }

        return Scopelist;
    }
    /**
     * 删除指定id的配送点
     *
     * @param scopeid
     */
    public void delscope(int scopeid) {
        String sql = "delete from dbss where DBSsId = '" + scopeid + "'";
        DBUtil.executeUpdate(sql);
    }
}
