package dao.base.Employee;

import dao.DBUtil;

import entity.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 59480 on 2017/3/20.
 */
public class Employeedao {
    /**
     * 添加新的配送点到数据库
     *
     * @param employee
     */
    public void addempInfo(Employee employee) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "insert into employee( "+"emp_id, "
                    + "emp_name, " + "emp_sex, emp_phone," + " emp_zhiwei ) "
                    + "values(?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setInt(1,employee.getEmployeeId());
            stat.setString(2, employee.getName());
            stat.setString(3, employee.getSex());
            stat.setInt(4, employee.getPhone());
            stat.setString(5, employee.getZhiwei());
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
     * @param Empid
     */
    public void delemp(int Empid) {
        String sql = "delete from employee where emp_id = " + Empid ;
        DBUtil.executeUpdate(sql);
    }

    /**
     * 修改指定配送点信息
     *
     * @param employee
     */

    public void updateEmp(Employee employee) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "update employee set "+" emp_id = ?,"+" emp_name = ?,"
                    + " emp_sex = ?," + " emp_phone = ?,"
                    + " emp_zhiwei = ?"
                    + " where emp_id = ? ";
            stat = con.prepareStatement(sql);
            stat.setInt(1, employee.getEmployeeId());
            stat.setString(2,employee.getName());
            stat.setString(3,employee.getSex());
            stat.setInt(4, employee.getPhone());
            stat.setString(5,employee.getZhiwei());
            stat.setInt(6, employee.getEmployeeId1());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    /**
     * 查找
     * @param empid
     * @return
     */
    public Employee find(int empid){
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        Employee employee=null;
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM employee"
                    + " WHERE emp_id = "+empid ;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("emp_id");
                String name=rs.getString("emp_name");
                String sex=rs.getString("emp_sex");
                int phone=rs.getInt("emp_phone");
                String zhiwei=rs.getString("emp_zhiwei");

                employee=new Employee(id,name,sex,phone,zhiwei);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return employee;
    }

    /**
     * 加载dbs到list
     *
     * @return
     */
    public List<Employee> loadempList() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<Employee> empList = new ArrayList<Employee>();

        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM employee"
                    + " order by emp_id desc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id=rs.getInt("emp_id");
                String name=rs.getString("emp_name");
                String sex=rs.getString("emp_sex");
                int phone=rs.getInt("emp_phone");
                String zhiwei=rs.getString("emp_zhiwei");

                Employee employee=new Employee(id,name,sex,phone,zhiwei);
                empList.add(employee);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return empList;

    }


}




