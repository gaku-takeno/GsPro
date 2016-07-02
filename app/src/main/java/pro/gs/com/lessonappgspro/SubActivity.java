package pro.gs.com.lessonappgspro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(name);



    }
}
