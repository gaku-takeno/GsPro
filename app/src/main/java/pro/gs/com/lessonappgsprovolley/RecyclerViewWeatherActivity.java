package pro.gs.com.lessonappgsprovolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import pro.gs.com.lessonappgsprovolley.adapter.RecyclerAdapter;
import pro.gs.com.lessonappgsprovolley.adapter.RecyclerAdapter2;
import pro.gs.com.lessonappgsprovolley.objects.CustomDataObject;

public class RecyclerViewWeatherActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_weather);
        loadData();
    }



    private void loadData () {

        // 東京都の天気情報
        String api_url = "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";
//        String api_url = "http://nofiction.deca.jp/cities.json";

//        RequestQueue mQueue;
        mQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET,api_url,(String)null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // JSONObjectのパース、List、Viewへの追加等
                        Log.d("hello_res", String.valueOf(response));
//
//                        {"pinpointLocations" : [
//            {
//                "link" : "http://weather.livedoor.com/area/forecast/4044700",
//                    "name" : "筑前町",
//                    "icon" : "http://nofiction.deca.jp/images/icon_11.jpg"
//            },
//            {
//                "link" : "http://weather.livedoor.com/area/forecast/4044800",
//                    "name" : "東峰村",
//                    "icon" : "http://nofiction.deca.jp/images/icon_12.jpg"
//            },
//            {
//                "link" : "http://weather.livedoor.com/area/forecast/4050300",
//                    "name" : "大刀洗町",
//                    "icon" : "http://nofiction.deca.jp/images/icon_13.jpg"
//            },
//            {
//                "link" : "http://weather.livedoor.com/area/forecast/4052200",
//                    "name" : "大木町",
//                    "icon" : "http://nofiction.deca.jp/images/icon_14.jpg"
//            },
//            {
//                "link" : "http://weather.livedoor.com/area/forecast/4054400",
//                    "name" : "広川町",
//                    "icon" : "http://nofiction.deca.jp/images/icon_15.jpg"
//            }
//            ]
//        }
                        setList(response);

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // エラー処理 error.networkResponseで確認
                        // エラー表示など
                    }
                });

        mQueue.add(jsonRequest);
    }





    private void setList (JSONObject response) {

        mQueue = Volley.newRequestQueue(this);

        ArrayList<CustomDataObject> customDataObjectArrayList = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mylist);

        RecyclerAdapter2 customAdapater = new RecyclerAdapter2(customDataObjectArrayList, this);

        try {
            JSONArray pinpointLocations = response.getJSONArray("pinpointLocations");

            for (int c = 0; c<pinpointLocations.length(); c++) {

                JSONObject v1 = (JSONObject) pinpointLocations.get(c);
                String link = v1.getString("link");
                String name = v1.getString("name");

                //cityCodeを取得する
                String[] linkArg = link.split("/", 0);
                int index = linkArg.length-1;
                int cityCode = Integer.parseInt(linkArg[index]);

                CustomDataObject customDataObject = new CustomDataObject();
                customDataObject.setName(name);
                customDataObject.setLink(link);
                customDataObject.setCityCode(cityCode);
                customDataObjectArrayList.add(customDataObject);

            }

            recyclerView.setAdapter(customAdapater);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(this);
            recyclerView.setLayoutManager(mLayoutManager);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

}
