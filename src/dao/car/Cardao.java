package dao.car;

import dao.DBUtil;
import entity.Car;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 59480 on 2017/3/19.
 */
public class Cardao {
    private Car car;


    /**
     * 添加新的员工到数据库
     *
     * @param car
     */
    public void addCarInfo(Car car) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "insert into car( "
                    + "buyTime, " + "cang, " + "modle, "
                    + "deadweight, " + "volume, " + "carstate) "
                    + "values(?,?,?,?,?,?)";
            stat = con.prepareStatement(sql);
            stat.setDate(1, new java.sql.Date(car.getBuyT().getTime()));
            stat.setString(2, car.getManufacturer());
            stat.setString(3, car.getType());
            stat.setDouble(4, car.getLoadCapacity());
            stat.setDouble(5, car.getCube());
            stat.setInt(6, car.getCarstate());

            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }


    /**
     * 修改指定车辆信息
     *
     * @param car
     */

    public void updatecar(Car car) {
        Connection con = null;
        PreparedStatement stat = null;
        try {
            con = DBUtil.getConn();
            String sql = "update car set buyTime = ?,"
                    + " cang = ?," + " modle = ?,"
                    + " deadweight = ?, " + "volume = ?,"+ " carstate = ?"
                    + " where carId = ? ";
            stat = con.prepareStatement(sql);
            stat.setDate(1, new java.sql.Date(car.getBuyT().getTime()));
            stat.setString(2, car.getManufacturer());
            stat.setString(3, car.getType());
            stat.setDouble(4, car.getLoadCapacity());
            stat.setDouble(5, car.getCube());
            stat.setInt(6, car.getCarstate());
            stat.setInt(7, car.getCarId());
            stat.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, null);
        }
    }

    public Car findCar(int id) {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        Car car = null;
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM car"
                    + " WHERE carId = "+id;
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int carId = rs.getInt("carId");
                Date buyTime = rs.getDate("buyTime");
                String Manufacturer = rs.getString("cang");
                String modle = rs.getString("modle");
                double deadweight = rs.getDouble("deadweight");
                double roleName = rs.getDouble("volume");
                int carstate = rs.getInt("carstate");


                car = new Car(carId,buyTime,Manufacturer,modle,deadweight,roleName,carstate);

            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return car;
    }




    /**
     * 删除指定id的用户
     *
     * @param carId
     */
    public void delcar(int carId) {
        String sql = "delete from car where carId = '" + carId + "'";
        DBUtil.executeUpdate(sql);
    }



    /**
     * 加载car到list
     *
     * @return
     */
    public List<Car> loadcarList() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List<Car> carList = new ArrayList<Car>();
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select *" + " FROM car"
                    + " order by carId desc";
            rs = stat.executeQuery(sql);
            while (rs.next()) {
                int carId = rs.getInt("carId");
                Date buyTime = rs.getDate("buyTime");
                String Manufacturer = rs.getString("cang");
                String modle = rs.getString("modle");
                double deadweight = rs.getDouble("deadweight");
                double roleName = rs.getDouble("volume");
                int carstate = rs.getInt("carstate");


                Car car = new Car(carId,buyTime,Manufacturer,modle,deadweight,roleName,carstate);
                carList.add(car);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeConn(con, stat, rs);
        }
        return carList;
    }
    public Integer[] loadid() {
        Connection con = null;
        Statement stat = null;
        ResultSet rs = null;
        List rl = new ArrayList();
        try {
            con = DBUtil.getConn();
            stat = con.createStatement();
            String sql = "select carId from car";
            rs = stat.executeQuery(sql);

            while (rs.next()) {
                int id = rs.getInt("carId");
                rl.add(id);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            DBUtil.closeConn(con, stat, rs);

        }
        Integer[] s=new Integer[rl.size()];
        for(int i=0;i<rl.size();i++){
            s[i]=(Integer) rl.get(i);
        }
        return s;
    }
}
