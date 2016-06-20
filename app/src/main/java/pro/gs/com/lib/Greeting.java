package pro.gs.com.lib;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * Created by takenogaku on 2016/06/13.
 */
public class Greeting extends Dictionary{


    private int hourCategory = 0;
    private HashMap dict;

    //コンストラクタ
    public Greeting (int lang) {

        //時間帯から判断してどの挨拶をするかを決定する。
        setHourCategory();

        //どっちの辞書を使うかを決定（英語？日本語？）
        dict = (HashMap) langArg.get(lang);

    }


    /**
     * 挨拶をする。
     * @return
     */
    public String doGreeting () {

        if  (hourCategory == 1) {
            return (String) dict.get("good_night");

        } else if (hourCategory == 2) {
            return (String) dict.get("good_morning");

        } else if (hourCategory == 3) {
            return (String) dict.get("good_afternoon");

        } else if (hourCategory == 4) {
            return (String) dict.get("good_evening");

        } else {
            return "";
        }



    }


    /**
     * 時間帯を設定する
     */
    public void setHourCategory () {

        DateFormatSymbols dfs = DateFormatSymbols.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("HH",dfs);
        int time = Integer.parseInt(sdf.format(new Date()));

        if ((0 <= time && time < 4) || (21 <= time && time < 24)) {
            hourCategory = 1;//おやすみ
        } else if (4 <= time && time < 12) {
            hourCategory = 2;//おはよう
        } else if (12 <= time && time < 18) {
            hourCategory = 3;//こんにちは
        } else if (18 <= time && time < 21) {
            hourCategory = 4;//こんばんは

        }


    }

}
