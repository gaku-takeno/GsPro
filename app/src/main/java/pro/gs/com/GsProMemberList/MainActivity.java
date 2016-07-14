package pro.gs.com.GsProMemberList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.your_activity_layout);の前に以下を記述しないとエラーになる。
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_main);

        TextView mypageBtn = (TextView)findViewById(R.id.mypageBtn);

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
            editor.putString("name", object.getString("name"));
            editor.putInt("sex", sex);
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


}
