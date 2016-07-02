package pro.gs.com.lesson2gspro;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pro.gs.com.lib.Location;

public class SubActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();

        String first_name = intent.getStringExtra("first_name");
        String family_name = intent.getStringExtra("family_name");
        int sex = intent.getIntExtra("sex",1);
        int age = intent.getIntExtra("age",0);
        TextView nameView = (TextView) findViewById(R.id.name);


        //オブジェクト、物理的に存在する実体。プログラムとして動かせる状態になっているもの。
        //場所と時間と言語を把握するクラス。
        Location location = new Location();

        MySelf myself = new MySelf(this);
        myself.setName(family_name+" "+first_name);
        myself.setSex(sex);
        myself.setAge(age);
        myself.setHobby("音楽");
        myself.setLocation(location);


        String selfIntro = myself.doSelftIntruduction();
        nameView.setText(selfIntro);


    }
}