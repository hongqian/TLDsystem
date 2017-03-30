package entity;

/**
 * 配送点类
 * Created by 59480 on 2017/3/19.
 */
public class DistributionSites {
    private int DbsId;
    private String DbsName;
    private String Dbsaddress;
    private String DbsScale;
    private String ps;
    private int DbsId1;

    public DistributionSites() {
    }

    public DistributionSites(int dbsId, String dbsName, String dbsaddress, String dbsScale, String ps) {
        DbsId = dbsId;
        DbsName = dbsName;
        Dbsaddress = dbsaddress;
        DbsScale = dbsScale;
        this.ps = ps;
    }

    public DistributionSites(String dbsName, String dbsaddress, String dbsScale, String ps) {
        DbsName = dbsName;
        Dbsaddress = dbsaddress;
        DbsScale = dbsScale;
        this.ps = ps;
    }



    public DistributionSites(int dbsId, String dbsName, String dbsaddress, String dbsScale, String ps,int dbsId1) {
        DbsId = dbsId;
        DbsName = dbsName;
        Dbsaddress = dbsaddress;
        DbsScale = dbsScale;
        this.ps = ps;
        this.DbsId1=dbsId1;
    }

    public int getDbsId1() {
        return DbsId1;
    }

    public void setDbsId1(int dbsId1) {
        DbsId1 = dbsId1;
    }

    public int getDbsId() {
        return DbsId;
    }

    public void setDbsId(int dbsId) {
        DbsId = dbsId;
    }

    public String getDbsName() {
        return DbsName;
    }

    public void setDbsName(String dbsName) {
        DbsName = dbsName;
    }

    public String getDbsaddress() {
        return Dbsaddress;
    }

    public void setDbsaddress(String dbsaddress) {
        Dbsaddress = dbsaddress;
    }

    public String getDbsScale() {
        return DbsScale;
    }

    public void setDbsScale(String dbsScale) {
        DbsScale = dbsScale;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }
}
