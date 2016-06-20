package pro.gs.com.gspro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //型がTextViewであるnameViewという名前の変数を宣言している。
    //この時点では、nameViewには何も入っていない。つまりNULL
    private TextView greetingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewオブジェクトを取り出して、メンバー変数に格納する。
        greetingView = (TextView) findViewById(R.id.greeting);

        MySelf myself = new MySelf(this);
        myself.setName("伊藤 浩二");
        myself.setSex(1);
        myself.setAge(55);
        myself.setHobby("音楽");

        String selfIntro = myself.doSelftIntruductionBasic();
        greetingView.setText(selfIntro);


    }



}
