package entity;

import java.util.Date;

/**
 * 配送范围类
 *
 * Created by 59480 on 2017/3/19.
 */
public class DistributionScope {
    private int DbscopeId;
    private String DbscopeName;
    private double TheFirstKgPrice;
    private double TimeKgPrice;
    private double ACubicPrices;
    private int DeliveryTime;
    private String ps;
    private String dbsname;
    private int DbscopeId1;

    public DistributionScope() {
    }

    public DistributionScope(int dbscopeId, String dbscopeName, double theFirstKgPrice, double timeKgPrice, double ACubicPrices, int deliveryTime, String ps) {
        DbscopeId = dbscopeId;
        DbscopeName = dbscopeName;
        TheFirstKgPrice = theFirstKgPrice;
        TimeKgPrice = timeKgPrice;
        this.ACubicPrices = ACubicPrices;
        DeliveryTime = deliveryTime;
        this.ps = ps;
    }

    public DistributionScope(int dbscopeId, String dbscopeName, double theFirstKgPrice, double timeKgPrice, double ACubicPrices, int deliveryTime, String ps, String dbsname, int dbscopeId1) {
        DbscopeId = dbscopeId;
        DbscopeName = dbscopeName;
        TheFirstKgPrice = theFirstKgPrice;
        TimeKgPrice = timeKgPrice;
        this.ACubicPrices = ACubicPrices;
        DeliveryTime = deliveryTime;
        this.ps = ps;
        this.dbsname = dbsname;
        DbscopeId1 = dbscopeId1;
    }

    public DistributionScope(int dbscopeId, String dbscopeName, double theFirstKgPrice, double timeKgPrice, double ACubicPrices, int deliveryTime, String ps, String dbsname) {
        DbscopeId = dbscopeId;
        DbscopeName = dbscopeName;
        TheFirstKgPrice = theFirstKgPrice;
        TimeKgPrice = timeKgPrice;
        this.ACubicPrices = ACubicPrices;
        DeliveryTime = deliveryTime;
        this.ps = ps;
        this.dbsname=dbsname;
    }

    public int getDbscopeId1() {
        return DbscopeId1;
    }

    public void setDbscopeId1(int dbscopeId1) {
        DbscopeId1 = dbscopeId1;
    }

    public double getACubicPrices() {

        return ACubicPrices;
    }

    public void setACubicPrices(double ACubicPrices) {
        this.ACubicPrices = ACubicPrices;
    }

    public String getDbsname() {
        return dbsname;
    }

    public void setDbsname(String dbsname) {
        this.dbsname = dbsname;
    }

    public int getDbscopeId() {
        return DbscopeId;
    }

    public void setDbscopeId(int dbscopeId) {
        DbscopeId = dbscopeId;
    }

    public String getDbscopeName() {
        return DbscopeName;
    }

    public void setDbscopeName(String dbscopeName) {
        DbscopeName = dbscopeName;
    }

    public double getTheFirstKgPrice() {
        return TheFirstKgPrice;
    }

    public void setTheFirstKgPrice(double theFirstKgPrice) {
        TheFirstKgPrice = theFirstKgPrice;
    }

    public double getTimeKgPrice() {
        return TimeKgPrice;
    }

    public void setTimeKgPrice(double timeKgPrice) {
        TimeKgPrice = timeKgPrice;
    }

    public int getDeliveryTime() {
        return DeliveryTime;
    }

    public void setDeliveryTime(int deliveryTime) {
        DeliveryTime = deliveryTime;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
