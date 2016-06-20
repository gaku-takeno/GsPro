package pro.gs.com.gspro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import pro.gs.com.gspro.R;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView greetingView = (TextView) findViewById(R.id.greeting);

        MySelf myself = new MySelf(this);
        myself.setName("石井 健太");
        myself.setSex(1);//1:男 0:女
        myself.setAge(50);
        myself.setHobby("音楽");


        Log.d("name",myself.getName());


        String selfIntro = myself.doSelftIntruductionBasic();
        greetingView.setText(selfIntro);


    }
}
