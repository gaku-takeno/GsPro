package pro.gs.com.GsProMemberList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * Facebook APIと連動して、Facebook oauth認証をする。
 *
 */
public class MainActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private LoginButton loginButton;
    private SharedPreferences data;

    /**
     * Activityが作成される時(MainActivityがオブジェクト化された時)に
     * 必ず一番初めに実行されるメソッド
     * MainActivityを開いてる時に、一度他のアプリを開いた後、再びMainActivityをアクティブにした場合は、
     * onCreateは実行されない。
     * MainActivityがバックグラウンドに置かれている状態から、フォアグランドに戻っているので、
     * MainActivityが作成されている訳ではない。
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.your_activity_layout);の前に以下を記述しないとエラーになる。
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        TextView mypageBtn = (TextView)findViewById(R.id.mypageBtn);

//        mypageBtn.setText(2);
        callbackManager = CallbackManager.Factory.create();
        loginButton = (LoginButton) findViewById(R.id.login_btn_facebook);

        //FB APIにログインする。
        fbLogin();


        data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);

        // マイペースへ画面遷移する
        if (mypageBtn != null) {
            mypageBtn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), MyPage.class);
                    startActivity(intent);
                }
            });
        }



        Log.d("gspro_activity_life","onCreate");

    }




    /**
     * FB APIにログインする。
     */
    private void fbLogin () {


        //APIから取得したいユーザー情報の種類を指定し、パーミッションを許可する。
        List<String> permissionNeeds = Arrays.asList("public_profile");
        loginButton.setReadPermissions(permissionNeeds);

        if (loginButton != null) {

            loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                @Override
                public void onSuccess(LoginResult loginResult) {
                    GraphRequest request = GraphRequest.newMeRequest
                            (loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                                @Override
                                public void onCompleted(JSONObject object, GraphResponse response) {

                                    //FB APIで取得したprofileデータをサーバー側に追加する。
                                    addMember(object);

                                    addSharedPref(object);

                                }
                            });
                    Bundle parameters = new Bundle();
                    parameters.putString("fields", "id,name,gender");
                    request.setParameters(parameters);
                    request.executeAsync();
                }

                @Override
                public void onCancel() {
                }

                @Override
                public void onError(FacebookException exception) {
                }

            });


            AccessTokenTracker accessTokenTracker = new AccessTokenTracker() {
                @Override
                protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken,
                                                           AccessToken currentAccessToken) {

                    //ログアウト処理をした場合
                    if (currentAccessToken == null) {
                        loggedout();
                    }
                }
            };

            accessTokenTracker.startTracking();

        }

    }



    private void loggedout () {

        Toast toast = Toast.makeText(getApplicationContext(), Language.COMPLETE_LOGGEDOUT, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
        toast.show();

    }





    /**
     * FB APIで取得したprofileデータをサーバー側に追加する。
     * @param object
     */
    private void addMember(final JSONObject object) {

        RequestQueue mQueue = Volley.newRequestQueue(this);
        //Request
        StringRequest postRequest = new StringRequest(Request.Method.POST,Constants.ADD_GSPRO_MEMBER,
                new Response.Listener<String>() {

                    /**
                     * サーバーにPOST送信し、そのコールバックメソッドとなる。
                     * @param response サーバーからのレスポンス
                     */
                    @Override
                    public void onResponse(String response) {

                        if (response != null) {
                            try {
                                JSONObject responseJsonObject = new JSONObject(response);
                                int status = responseJsonObject.getInt("status");
                                if (status == 1) {
                                    Toast toast = Toast.makeText(getApplicationContext(), Language.COMPLETE_ADD_MEMBER, Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
                                    toast.show();

                                } else {
                                    Toast toast = Toast.makeText(getApplicationContext(), Language.FAIL_ADD_MEMBER, Toast.LENGTH_SHORT);
                                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
                                    toast.show();
                                }


                            } catch (JSONException e) {
                                // TODO 自動生成された catch ブロック
                                e.printStackTrace();

                            }
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
                Map<String,String> params = new HashMap<String,String>();


                try {
                    params.put("sns_user_id",object.getString("id"));
                    params.put("name",object.getString("name"));

                    int sex = 0;
                    if (object.getString("gender").equals("male")) {
                        sex = 1;
                    }

                    params.put("sex",sex+"");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return params;
            }
        };
        mQueue.add(postRequest);

    }


    /**
     * FB profileをSharedPreferenceオブジェクトに保存する
     * @param object
     */
    private void addSharedPref (JSONObject object) {

        try {

            int sex = 0;
            if (object.getString("gender").equals("male")) {
                sex = 1;
            }

            SharedPreferences.Editor editor = data.edit();
            editor.putString("sns_user_id", object.getString("id"));
            editor.apply();

        } catch (JSONException e) {
            e.printStackTrace();
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent data) {
        super.onActivityResult(requestCode, responseCode, data);
        callbackManager.onActivityResult(requestCode, responseCode, data);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Log.d("gspro_activity_life","onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("gspro_activity_life","onPause");
    }


    /**
     * 画面がアクティブになる度に必ず、実行されるメソッドなので、
     * 画面がアクティブになる度に必ず実行したい処理がある場合に使う。
     * 例えば、画面が前面に出る度(フォアグランド)に、最新のデータを取り出し直したい場合など。
     */
    @Override
    protected void onResume() {
        super.onResume();
        Log.d("gspro_activity_life","onResume");

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("gspro_activity_life","onRestart");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("gspro_activity_life","onDestroy");


    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d("gspro_activity_life","onStop");
    }

    public void onBackPressed() {

        AlertDialog.Builder ad = new AlertDialog.Builder(this);
        ad.create();

        ad.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                finish();
            }
        });

        ad.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {}
        });

        ad.setMessage("onDestroy");
        ad.show();

        //以下をコメントアウトすると戻るボタンが無効になる。
//        super.onBackPressed();

    }
}
