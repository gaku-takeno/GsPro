package pro.gs.com.gspro;

import android.content.Context;

import pro.gs.com.lib.Greeting;
import pro.gs.com.lib.Location;


public class MySelf {

    private Context context;
    private String hobby;

    private String name;

    private int age;

    private int sex;//1:男 0:女

    private Location location;

    /**
     *コンストラクタ
     */
    public MySelf (Context context) {
        this.age = 0;
        this.context = context;
    }

    /**
     * 自己紹介をする
     * @return
     */
    public String doSelftIntruductionBasic() {

        String greeting = "";

        if (this.name != null) {
            greeting += "私は"+this.name + "です。\n";
        }



        greeting += this.age + "歳です。\n";

        if (this.hobby != null) {
            greeting += "趣味は"+this.hobby + "です。\n";
        }

        if (this.sex == 1) {
            greeting += "男です。よろしく！！";
        } else {
            greeting += "女です。よろしくね〜";
        }

        // 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
//        Toast.makeText(this.context, greeting, Toast.LENGTH_LONG).show();

        return greeting;
    }


    /**
     * 自己紹介をする
     * @return
     */
    public String doSelftIntruduction() {

        Greeting greetingObj = new Greeting(location.getLang());
        String greeting = greetingObj.doGreeting()+"\n";

        if (this.name != null) {
            greeting += "私は"+this.name + "です。\n";
        }

        if (location.getArea() != null) {
            greeting += "現在私は、"+location.getArea() + "にいます。\n";
        }

        if (location.getLocalTime() != null) {
            greeting += "日付は、"+location.getLocalTime() + "になります。\n";
        }

        if (location.getLangJp() != null) {
            greeting += "普段、"+location.getLangJp() + "を話します。\n";
        }

        greeting += this.age + "歳です。\n";

        if (this.hobby != null) {
            greeting += "趣味は"+this.hobby + "です。\n";
        }

        if (this.sex == 1) {
            greeting += "男です。よろしく！！";
        } else {
            greeting += "女です。よろしくね〜";
        }

        // 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
//        Toast.makeText(this.context, greeting, Toast.LENGTH_LONG).show();

        return greeting;
    }


    /**
     * 名前を設定する
     * @param name
     */
    public void setName (String name) {
        this.name = name;
    }

    /**
     * 名前を取る
     * @return
     */
    public String getName () {
        return this.name;
    }

    /**
     * 趣味を設定する
     * @param hobby
     */
    public void setHobby (String hobby) {
        this.hobby = hobby;
    }



    public void setLocation (Location location) {
        this.location = location;
    }

    /**
     * 性別を設定する
     * @param sex
     */
    public void setSex (int sex) {
        this.sex = sex;
    }

    /**
     * 性別を取る
     * 1:男 0:女
     * @return
     */
    public int getSex () {
        return this.sex;
    }


    /**
     * 年齢を設定する
     * @param age
     */
    public void setAge (int age) {
        this.age = age;
    }

    /**
     * 年齢を取る
     * @return
     */
    public int getAge () {
        return this.age;
    }



}
