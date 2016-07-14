package pro.gs.com.GsProMemberList;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.facebook.FacebookSdk;
import com.facebook.Profile;

public class MyPage extends AppCompatActivity {

    private LinearLayout login_area;
    private LinearLayout logout_area;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.your_activity_layout);の前に以下を記述しないとエラーになる。
        FacebookSdk.sdkInitialize(getApplicationContext());

        setContentView(R.layout.activity_my_page);

        logout_area = (LinearLayout) findViewById(R.id.logout_area);
        login_area = (LinearLayout) findViewById(R.id.login_area);

        Profile fbProf = Profile.getCurrentProfile();
        // FBログイン中
        if(fbProf != null) {
            //profileを表示する。
            loginTrans(fbProf);

        } else {

            logoutTrans();
        }

        SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
        String sns_user_id = data.getString("sns_user_id","");
        String name = data.getString("name","");

        Log.d("gs_my",sns_user_id+":"+name);


    }


    private void loginTrans(Profile profile) {

        login_area.setVisibility(View.VISIBLE);
        logout_area.setVisibility(View.GONE);

        ImageView user_iconView = (ImageView) findViewById(R.id.user_icon);
        TextView nameView = (TextView) findViewById(R.id.name);
        nameView.setText(profile.getName());

        //ネット上の画像のURL
        String imageUrl = "http://graph.facebook.com/"+ profile.getId()+ "/picture?type=large";

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

    }



    private void logoutTrans () {

        login_area.setVisibility(View.GONE);
        logout_area.setVisibility(View.VISIBLE);

        TextView messageView = (TextView) findViewById(R.id.message);
        messageView.setText("ログアウト中です。");



    }
}
