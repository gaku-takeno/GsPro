package pro.gs.com.lib;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by takenogaku on 2016/06/16.
 */
public class Location {

    //0:英語  1:日本語
    private int lang;
    private String langJp;

    private String localTime;

    private String area;

    private TimeZone timeZone;

    public Location () {

        setArea();
        setLanguage();
        setLocalTime();
    }


    public void setArea () {

        timeZone = TimeZone.getDefault();
        area = timeZone.getID();

    }


    public void setLanguage () {

        if (area.indexOf("Tokyo") > 0) {
            this.lang = 1;
            this.langJp = "日本語";
        } else {
            this.lang = 0;
            this.langJp = "英語";
        }

    }




    /**
     * ローカル日時を設定する
     * @return
     */
    public void setLocalTime () {

        String[] weekArg = new String[8];
        weekArg[0] = "";
        weekArg[1] = "日";
        weekArg[2] = "月";
        weekArg[3] = "火";
        weekArg[4] = "水";
        weekArg[5] = "木";
        weekArg[6] = "金";
        weekArg[7] = "土";

        DateFormatSymbols dfs = DateFormatSymbols.getInstance();
        dfs.setWeekdays(weekArg);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日(EEEE) HH時mm分",dfs);

        TimeZone tz = TimeZone.getTimeZone(area);
        sdf.setTimeZone(tz);

        this.localTime = sdf.format(new Date());

    }


    public int getLang () {
        return this.lang;
    }


    public String getLangJp () {
        return this.langJp;
    }

    public String getArea () {
        return this.area;
    }


    public String getLocalTime () {
        return this.localTime;
    }
}
