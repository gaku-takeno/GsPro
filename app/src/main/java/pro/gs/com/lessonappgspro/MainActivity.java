package pro.gs.com.lessonappgspro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView listView  = (TextView) findViewById(R.id.listView);
        TextView recyclerView  = (TextView) findViewById(R.id.recyclerView);


        if (listView != null) {

            listView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),ListViewActivity.class);
                    startActivity(intent);
            }
            });
        }


        if (recyclerView != null) {

            recyclerView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                    startActivity(intent);
                }
            });
        }

    }

}
