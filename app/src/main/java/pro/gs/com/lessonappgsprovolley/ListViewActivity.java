package pro.gs.com.lessonappgsprovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ListViewActivity extends AppCompatActivity {


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_list_view);


            //http://techbooster.org/android/ui/9039/

            ListView lv = (ListView) findViewById(R.id.listView1);

            String[] members = {
                    "mhidaka",
                    "rongon_xp",
                    "kacchi0516",
                    "kobashinG",
                    "seit", "kei_i_t",
                    "furusin_oriver" };


            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_expandable_list_item_1, members);

            lv.setAdapter(adapter);




            //リスト項目がクリックされた時の処理
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    ListView listView = (ListView) parent;
                    String item = (String) listView.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), item + " clicked",
                            Toast.LENGTH_LONG).show();
                }
            });

            //リスト項目が長押しされた時の処理
            lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    ListView listView = (ListView) parent;
                    String item = (String) listView.getItemAtPosition(position);
                    Toast.makeText(getApplicationContext(), item + " long clicked",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
            });



        }
    }