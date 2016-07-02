package pro.gs.com.lesson1gsmylayout;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView date1View = (TextView) findViewById(R.id.date1);
        TextView date2View = (TextView) findViewById(R.id.date2);

        //東京日時を表示
        String tzTK = getLocalTime("Asia/Tokyo");
        date1View.setText("東京時間:\n"+tzTK);

        //ニューヨーク時間を表示
        String tzNY = getLocalTime("America/New_York");
        date2View.setText("ニューヨーク時間:\n"+tzNY);


        //自分の名前を表示
        TextView nameView = (TextView) findViewById(R.id.name);
        String fullname = "織田 信長";
        nameView.setText(fullname);


        //自己紹介を表示
        int my_age = 35;
        TextView intructionView = (TextView) findViewById(R.id.intruction);
        String self_intruduction = myGreeting(fullname,my_age);
        intructionView.setText(self_intruduction);


        //平均を表示(何の平均かは、とりあえず置いといて・・・)
        TextView averageView = (TextView) findViewById(R.id.average);
        int[] intArg = new int[3];
        intArg[0] = 2;
        intArg[1] = 24;
        intArg[2] = 33;
        double avarage = myAverage(intArg);

        //取り出した平均値を格納するaverage変数は少数型でdouble
        //このままでは、平均値の少数点以下が無数に表示されうる。
        //少数第2位まで表示する。
        String averageVal = String.format("%.2f", avarage);
        averageView.setText("平均得点（平均値）:"+averageVal);


    }


    /**
     * 四捨五入をする
     * @param number
     * @return
     */
    public int myRound (double number) {
        int value = (int) Math.round(number);
        return value;
    }


    /**
     * 10の倍数
     * @param number
     * @return
     */
    public int myBaisu(int number) {
        int value = number * 10;
        return value;
    }


    /**
     * 平均値
     * @param intArg
     * @return
     */
    public double myAverage(int[] intArg) {
        int total = 0;
        for ( int i = 0;i<intArg.length;i++) {
            total += intArg[i];
        }
        double average = (double)total / intArg.length;
//        return myRound(average);
        Log.d("hello_",average+"");
        return average;

    }


    /**
     * 名前に「様」をつける
     * @param name
     * @return
     */
    public String myKeisho (String name) {
        name += "様";
        return name;
    }


    /**
     * 自己紹介文を作成する
     * @param name
     * @param age
     * @return
     */
    public String myGreeting (String name,int age) {
        String greeting = "こんにちは、私の名前は"+name+"と申します。";
        greeting += "年齢は"+age+"歳です。";
        return greeting;
    }





    /**
     * ローカル日時を取得する
     * @param timezone
     * @return
     */
    public String getLocalTime (String timezone) {

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
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd(EEEE) HH:mm",dfs);

        TimeZone tz = TimeZone.getTimeZone(timezone);
        sdf.setTimeZone(tz);

        return sdf.format(new Date());

    }


}