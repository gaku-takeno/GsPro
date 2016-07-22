package pro.gs.com.GsProMemberList;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ProfileEditActivity extends AppCompatActivity {

    private EditText nameView;
    private EditText messageView;
    private RadioGroup radioGroup;
    private Button submitView;

    private String name = "";
    private String message = "";
    private int sex = 0;
    private String sns_user_id;

    private RequestQueue mQueue;

    private View.OnClickListener submit_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            submit_Click(v);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);

        mQueue = Volley.newRequestQueue(this);
        submitView = (Button)findViewById(R.id.submit_btn);
        nameView = (EditText) findViewById(R.id.edit_name);
        messageView = (EditText) findViewById(R.id.edit_message);
        radioGroup = (RadioGroup) findViewById(R.id.radioSex);


        //submitボタンをタップするリスナー登録をする。
        submitView.setOnClickListener(submit_ClickListener);

        //SharedPreferencesからFBのユーザーIDを取得する
        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        sns_user_id = data.getString("sns_user_id","");

    }

    /**
     * submit
     */
    private void  submit_Click (View v) {

        name = nameView.getText().toString();
        message = messageView.getText().toString();


        // チェックされているラジオボタンの ID を取得します
        RadioButton checkedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        boolean checked = ((RadioButton) checkedRadioButton).isChecked();

        if (checked) {
            String sexId = (String) checkedRadioButton.getText();
            if (sexId.equals("man")) {
                sex = 1;//man
            } else {
                sex = 2;//woman
            }
        }


        Map<String,String> params = new HashMap<String,String>();
        params.put("sns_user_id",sns_user_id);
        params.put("name",name);
        params.put("sex",sex+"");
        params.put("message",message+"");

        //httpリクエスト POSTメソッドを実行する
        volleyPost(params, Constants.UPDATE_GSPRO_MEMBER);



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




    public void onPostRequestTaskFinished(String object) {


        Log.d("gspro",object);

        int status = 0;
        if (object != null) {
            try {
                JSONObject responseJsonObject = new JSONObject(object);
                status = responseJsonObject.getInt("status");
                if (status == 1) {
                    Toast toast = Toast.makeText(getApplicationContext(), Language.COMPLETE_UPDATE_PROFILE, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
                    toast.show();

                    finish();
                    Intent intent = new Intent(getApplicationContext(), MyPage.class);
                    startActivity(intent);

                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), Language.FAIL_UPDATE_PROFILE, Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER, 0, 0);
                    toast.show();
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

        }

    }




}
