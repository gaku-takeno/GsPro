package pro.gs.com.lessonappgspro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView listView = (TextView) findViewById(R.id.listView);
        TextView recyclerView = (TextView) findViewById(R.id.recyclerView);


        if (listView != null) {

            listView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), ListViewActivity.class);
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

//    private void jsonParse () {


//        String url = "http://nofiction.deca.jp/cities.json";

//        String st = "{'pinpointLocations' : [" +
//                "{'link' : 'http://weather.livedoor.com/area/forecast/4044700','name' : '筑前町', 'icon' : 'http://nofiction.deca.jp/images/icon_11.jpg'}," +
//                "{'link' : 'http://weather.livedoor.com/area/forecast/4044800','name' : '東峰村', 'icon' : 'http://nofiction.deca.jp/images/icon_12.jpg'}" +
//                "]}";



//        if (response != null) {
//            try {
//                JSONObject responseJsonObjec = new JSONObject(response);
//                loadData(responseJsonObjec, isFirstLoading);
//                loading = false;
//
//            } catch (JSONException e) {
//                // TODO 自動生成された catch ブロック
//                e.printStackTrace();
//
//            }
//        }


//    }


//
//    private void loadData (JSONObject response) {
//
//        try {
//            JSONArray pinpointLocations = response.getJSONArray("pinpointLocations");
//
//            for (int c = 0; c<pinpointLocations.length(); c++) {
//
//                JSONObject v1 = (JSONObject) pinpointLocations.get(c);
//                String link = v1.getString("link");
//                String name = v1.getString("name");
//                String icon = v1.getString("icon");
//                Log.d("hello_name",name);
//                //cityCodeを取得する
//                String[] linkArg = link.split("/", 0);
//                int index = linkArg.length-1;
//                int cityCode = Integer.parseInt(linkArg[index]);
//
////                CustomDataObject customDataObject = new CustomDataObject();
////                customDataObject.setName(name);
////                customDataObject.setLink(link);
////                customDataObject.setIcon(icon);
////                customDataObject.setCityCode(cityCode);
////                customDataObjectArrayList.add(customDataObject);
//
//            }
//
//
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//
//    }

}
