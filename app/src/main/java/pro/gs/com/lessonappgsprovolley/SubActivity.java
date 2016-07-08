package pro.gs.com.lessonappgsprovolley;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import pro.gs.com.lessonappgsprovolley.objects.CustomDataObject;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);


        Intent intent = getIntent();
        CustomDataObject customDataObject = (CustomDataObject) intent.getSerializableExtra("customDataObject");

        TextView textView = (TextView) findViewById(R.id.name);
        textView.setText(customDataObject.getName()+"\n"+customDataObject.getLink()+"\n"+customDataObject.getCityCode());



    }
}
