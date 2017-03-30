package entity;

import java.util.Date;

/**
 *
 * 车辆类
 * Created by 59480 on 2017/3/19.
 */
public class Car {
    private int CarId;
    private Date BuyT;
    private String Manufacturer;
    private String Type;
    private double LoadCapacity;
    private double Cube;
    private int carstate;
    public Car() {
    }

    public Car(Date buyT, String manufacturer, String type, double loadCapacity, double cube, int carstate) {
        BuyT = buyT;
        Manufacturer = manufacturer;
        Type = type;
        LoadCapacity = loadCapacity;
        Cube = cube;
        this.carstate = carstate;
    }

    public Car(int carId, Date buyT, String manufacturer, String type, double loadCapacity, double cube, int carstate) {
        CarId = carId;
        BuyT = buyT;
        Manufacturer = manufacturer;
        Type = type;
        LoadCapacity = loadCapacity;
        Cube = cube;
        this.carstate = carstate;
    }

    public int getCarstate() {
        return carstate;
    }

    public void setCarstate(int carstate) {
        this.carstate = carstate;
    }

    public Date getBuyT() {
        return BuyT;
    }

    public void setBuyT(Date buyT) {
        BuyT = buyT;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        Manufacturer = manufacturer;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public double getLoadCapacity() {
        return LoadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        LoadCapacity = loadCapacity;
    }

    public int getCarId() {
        return CarId;
    }

    public void setCarId(int carId) {
        CarId = carId;
    }

    public double getCube() {
        return Cube;
    }

    public void setCube(double cube) {
        Cube = cube;
    }
}
