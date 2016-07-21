package pro.gs.com.GsProMemberList;


import java.io.Serializable;

/**
 * Created by takenogaku on 2016/05/05.
 */
public class CustomDataObject implements Serializable {
    private String link;
    private int cityCode;
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


}
