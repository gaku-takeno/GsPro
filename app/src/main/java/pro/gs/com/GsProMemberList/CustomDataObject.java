package pro.gs.com.GsProMemberList;


import java.io.Serializable;


public class CustomDataObject implements Serializable {
    private String message;
    private int sex;
    private String name;
    private String icon;

    public void setName (String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public void setIcon (String icon) {
        this.icon = icon;
    }
    public String getIcon() {
        return this.icon;
    }


    public void setMessage (String message) {
        this.message = message;
    }
    public String getMessage() {
        return this.message;
    }


    public void setSex (int sex) {
        this.sex = sex;
    }
    public int getSex() {
        return this.sex;
    }


}
