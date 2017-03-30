package entity;

/**
 * Created by 59480 on 2017/3/19.
 */
public class Client {
    private int id;
    private String name;
    private String sex;
    private int phone;
    private String addess;
    private int id1;

    public Client(int id, String name, String sex, int phone, String addess) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.addess = addess;
    }

    public Client(int id, String name, String sex, int phone, String addess, int id1) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.addess = addess;
        this.id1 = id1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getAddess() {
        return addess;
    }

    public void setAddess(String addess) {
        this.addess = addess;
    }

    public int getId1() {
        return id1;
    }

    public void setId1(int id1) {
        this.id1 = id1;
    }
}
