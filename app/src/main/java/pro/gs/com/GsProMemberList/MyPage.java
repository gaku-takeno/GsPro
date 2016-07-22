package pro.gs.com.GsProMemberList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MyPage extends AppCompatActivity {

    private String sns_user_id;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.your_activity_layout);の前に以下を記述しないとエラーになる。
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_my_page);

        mQueue = Volley.newRequestQueue(this);

        //SharedPreferencesからFBのユーザーIDを取得する
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        sns_user_id = data.getString("sns_user_id","");


        Profile fbProf = Profile.getCurrentProfile();
        // FBログイン中
        if(fbProf != null) {

            //profile画像を表示する。
            profileIcon();

            //profileデータを表示する。
            profileInfo();


        }


        Log.d("gs_my",sns_user_id+":");


    }


    private void profileIcon() {


        ImageView user_iconView = (ImageView) findViewById(R.id.user_icon);
        //ネット上の画像のURL
        String imageUrl = "http://graph.facebook.com/"+ sns_user_id+ "/picture?type=large";

        BitmapCache bmcache = new BitmapCache();

        RequestQueue rqQueue = Volley.newRequestQueue(this);
        ImageLoader ilImage = new ImageLoader(rqQueue, bmcache);

        //リスナーとは：何らかの事象（イベント）が発生したときに起動されるよう対応付けられた関数
        //ネットワークから画像をロードする為のリスナーを取得する
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(user_iconView,
                android.R.drawable.ic_menu_rotate,
                android.R.drawable.ic_delete);

        //キャッシュがあれば、キャッシュから、
        // キャッシュがなければ、ネットから画像をロードする。
        //画像のurlパスをキャッシュデータを格納する配列のキーとなる。
        ilImage.get(imageUrl, listener);


        Button listBtn = (Button) findViewById(R.id.listBtn);
        Button editBtn = (Button) findViewById(R.id.editBtn);

        //メンバーリスト画面を画面遷移する
        if (listBtn != null) {
            listBtn.setOnClickListener(new View.OnClickListener() {

                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), MemberListActivity.class);
                    startActivity(intent);

                }
            });
        }


        //プロフィール編集画面を画面遷移する
        if (editBtn != null) {
            editBtn.setOnClickListener(new View.OnClickListener() {

                /**
                 * Called when a view has been clicked.
                 *
                 * @param v The view that was clicked.
                 */
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(getApplicationContext(), ProfileEditActivity.class);
                    startActivity(intent);

                }
            });
        }


    }


    private void profileInfo () {

        //プロフィール情報をマイサーバーから取得する
        Map<String,String> params = new HashMap<String,String>();
        params.put("sns_user_id",sns_user_id);
        params.put("page","r98b$wk221oob");

        //httpリクエスト POSTメソッドを実行する
        volleyPost(params, Constants.LIST_GSPRO_PROFILE);

    }



    /**
     * httpリクエスト POSTメソッドを実行する
     * @param params
     */
    public void volleyPost (final Map<String,String> params,String url) {

        //Request
        StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    /**
                     * サーバーにPOST送信し、そのコールバックメソッドとなる。
                     * @param response サーバーからのレスポンス
                     */
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {
                            onPostRequestTaskFinished(response);
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        //error
                    }
                }){


            /**
             *
             * APIに渡すパラメーターを設定する。
             * API連携により取得したユーザー情報をPOST送信のパラメーターとして設定する
             * @return
             */
            @Override
            protected Map<String,String> getParams(){
                return params;
            }
        };
        mQueue.add(postRequest);
    }




    public void onPostRequestTaskFinished(String response) {


        TextView nameView= (TextView) findViewById(R.id.name);
        TextView sexView= (TextView) findViewById(R.id.sex);
        TextView messageView= (TextView) findViewById(R.id.message);

        if (response != null) {
            try {
                JSONObject responseJsonObject = new JSONObject(response);
                JSONObject gsmembers = responseJsonObject.getJSONObject("gsmembers");
                String name = gsmembers.getString("name");
                int sex = gsmembers.getInt("sex");
                String message = gsmembers.getString("message");

                String gender;
                //男の場合
                if (sex == 1) {
                    gender = "男性";
                } else {
                    gender = "女性";
                }

                nameView.setText(name);
                sexView.setText(gender);
                messageView.setText(message);


            } catch (JSONException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();

            }

        }

    }



}
