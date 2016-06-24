package pro.gs.com.lessonappgspro;

import android.content.Context;


public class MySelf extends Human2{


    public String doGreeting () {

        String greeting;
        greeting = "こんにちは！"+getName()+"です。\n";
        greeting += "年齢は"+getAge()+"です";

        return greeting;

    }


}
