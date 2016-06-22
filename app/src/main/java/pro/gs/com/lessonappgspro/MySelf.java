package pro.gs.com.lessonappgspro;

import android.content.Context;


public class MySelf extends Human{

    private Context context;
    private String hobby;

    private String name;


    /**
     *コンストラクタ
     */
    public MySelf (Context context) {
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

        greeting += getAge() + "歳です。\n";

        if (this.hobby != null) {
            greeting += "趣味は"+this.hobby + "です。\n";
        }

        if (getSex() == 1) {
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






}
