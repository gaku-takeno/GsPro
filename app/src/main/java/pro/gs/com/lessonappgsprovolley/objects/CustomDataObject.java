package pro.gs.com.lessonappgsprovolley.objects;



/**
 * Created by takenogaku on 2016/05/05.
 */
public class CustomDataObject {
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


    public void setLink (String link) {
        this.link = link;
    }
    public String getLink() {
        return this.link;
    }

    public void setCityCode (int cityCode) {
        this.cityCode = cityCode;
    }
    public int getCityCode() {
        return this.cityCode;
    }

}
