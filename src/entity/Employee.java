package entity;

/**
 * Created by 59480 on 2017/3/19.
 */
public class Employee {
    private int EmployeeId;
    private String Name;
    private String Sex;
    private int phone;
    private String zhiwei;
    private int EmployeeId1;

    public Employee() {
    }

    public Employee(int employeeId, String name, String sex, int phone, String zhiwei) {
        EmployeeId = employeeId;
        Name = name;
        Sex = sex;
        this.phone = phone;
        this.zhiwei = zhiwei;
    }

    public Employee(int employeeId, String name, String sex, int phone, String zhiwei, int employeeId1) {
        EmployeeId = employeeId;
        Name = name;
        Sex = sex;
        this.phone = phone;
        this.zhiwei = zhiwei;
        EmployeeId1 = employeeId1;
    }

    public Employee(String name, String sex, int phone, String zhiwei) {
        Name = name;
        Sex = sex;
        this.phone = phone;
        this.zhiwei = zhiwei;
    }



    public int getEmployeeId1() {
        return EmployeeId1;
    }

    public void setEmployeeId1(int employeeId1) {
        EmployeeId1 = employeeId1;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getEmployeeId() {
        return EmployeeId;
    }

    public void setEmployeeId(int employeeId) {
        EmployeeId = employeeId;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getZhiwei() {
        return zhiwei;
    }

    public void setZhiwei(String zhiwei) {
        this.zhiwei = zhiwei;
    }
}
