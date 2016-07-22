package pro.gs.com.GsProMemberList;

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


public class MemberListActivity extends AppCompatActivity {

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        mQueue = Volley.newRequestQueue(this);
        volleyGet(Constants.LIST_GSPRO_MEMBER);

    }



    /**
     * httpリクエスト GETメソッドを実行する
     *
     * @param api_url
     */
    public void volleyGet(String api_url) {

        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, api_url, (String) null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        onGetRequestTaskFinished(response);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // エラー処理 error.networkResponseで確認
                        // 例えばエラー表示などを行う

                    }
                });

        mQueue.add(jsonRequest);

    }




    public void onGetRequestTaskFinished(JSONObject object) {

        ArrayList<CustomDataObject> customDataObjectArrayList = new ArrayList<>();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.member_list);
        RecyclerAdapter customAdapater = new RecyclerAdapter(customDataObjectArrayList, this);

        try {
            JSONArray pinpointLocations = object.getJSONArray("gsmembers");

            for (int c = 0; c < pinpointLocations.length(); c++) {

                JSONObject v1 = (JSONObject) pinpointLocations.get(c);
                String sns_user_id = v1.getString("sns_user_id");
                String name = v1.getString("name");
                String message = v1.getString("message");

                String icon = "http://graph.facebook.com/" + sns_user_id + "/picture?type=large";

                CustomDataObject customDataObject = new CustomDataObject();
                customDataObject.setName(name);
                customDataObject.setIcon(icon);
                customDataObject.setMessage(message);
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