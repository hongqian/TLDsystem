package dao.base.Client;

import dao.DBUtil;
import entity.Client;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59480 on 2017/3/22.
 */
public class Clientdao {
    /**
     * 添加新的客户到数据库
     *
     * @param client
     */
    public void addClientInfo(Client client) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "insert into client( "+"cid, "
                    + "cname, " + "csex, cphone," + " caddess ) "
                    + "values(?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setInt(1,client.getId());
            stat.setString(2, client.getName());
            stat.setString(3, client.getSex());
            stat.setInt(4, client.getPhone());
            stat.setString(5, client.getAddess());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 删除指定id的客户
     *
     * @param cid
     */
    public void delclient(int cid) {
        String sql = "delete from client where cid = " + cid ;
        DBUtil.executeUpdate(sql);
    }

    /**
     * 修改指定配送点信息
     *
     * @param client
     */

    public void updateclient(Client client) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "update client set "+" cid = ?,"+" cname = ?,"
                    + " csex = ?," + " cphone = ?,"
                    + " caddess = ?"
                    + " where cid = ? ";
            stat = con.prepareStatement(sql);
            stat.setInt(1, client.getId());
            stat.setString(2,client.getName());
            stat.setString(3,client.getSex());
            stat.setInt(4, client.getPhone());
            stat.setString(5,client.getAddess());
            stat.setInt(6, client.getId1());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 查找
     * @param cid
     * @return  Client
     */
    public Client find(int cid){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        Client client=null;
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM client"
                    + " WHERE cid = "+cid ;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("cid");
                String name=rs.getString("cname");
                String sex=rs.getString("csex");
                int phone=rs.getInt("cphone");
                String addess=rs.getString("caddess");

                client=new Client(id,name,sex,phone,addess);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return client;
    }

    /**
     * 加载dbs到list
     *
     * @return
     */
    public List<Client> loadclientList() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<Client> clientList = new ArrayList<Client>();

        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM client"
                    + " order by cid desc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("cid");
                String name=rs.getString("cname");
                String sex=rs.getString("csex");
                int phone=rs.getInt("cphone");
                String addess=rs.getString("caddess");

                Client client=new Client(id,name,sex,phone,addess);
                clientList.add(client);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return clientList;

    }

    /**
     * 读取客户的name值
     * @return
     */
    public String[] loadname() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List rl = new ArrayList();
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select cname from client";
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                String name = rs.getString("cname");
                rl.add(name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            DBUtil.closeConn(con, stat, rs);

        }
        String[] s=new String[rl.size()];
        for(int i=0;i<rl.size();i++){
            s[i]=(String) rl.get(i);
        }
        return s;
    }
}
