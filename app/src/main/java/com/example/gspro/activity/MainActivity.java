package com.example.gspro.activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.gspro.helloworld.R;
import com.example.gspro.lib.SqliteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase dbObj;
    private  Button edit_profile;
    private  Button new_profile;
    private  Button prof_list;
    private  Button sexView;
    private TextView nameView;
    private Button nationView;
    private TextView addressView;
    private TextView emailView;

    private LinearLayout friend_listlView;
    private LinearLayout underView;
    private LinearLayout fr_ttlView;
    private ImageView my_iconView;
    private LinearLayout hs;


    private String name = "";
    private String email = "";
    private String address = "";
    private int sex = 0;
    private String nation = "";


    private ArrayList<Map<String, Object>> userData;
    private ArrayList<byte[]> friendIconData;

    private SqliteOpenHelper dbHelper;
    private int user_id = 0;
    private  byte[] user_icon = null;


    private View.OnClickListener submit_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            submit_Click(v);
        }
    };

    private View.OnClickListener new_submit_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {

            new_submit_Click(v);

        }
    };

    private View.OnClickListener prof_list_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            prof_list_Click();
        }
    };


    /**
     * 画面に表示するプロフィールを切り替える。
     */
    class setProfile_ClickListener implements View.OnClickListener {
        private int id;
        // コンストラクタ
        public setProfile_ClickListener(int id) {
            this.id = id;
        }

        @Override
        public void onClick(View v) {
            setCurrentProfile(this.id);

        }
    }

    //    private final int FP = ViewGroup.LayoutParams.MATCH_PARENT;
    private final int WC = ViewGroup.LayoutParams.WRAP_CONTENT;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new SqliteOpenHelper(this);
        userData = new ArrayList<>();

        friendIconData = new ArrayList<>();

        setViewObject();
        setListner();
        setProfileFriendsList();

    }


    /**
     */
    private void setViewObject () {
        edit_profile = (Button)findViewById(R.id.edit_profile);
        new_profile = (Button)findViewById(R.id.new_profile);
        prof_list = (Button)findViewById(R.id.prof_list);
        my_iconView = (ImageView)findViewById(R.id.my_icon);
        nameView  = (TextView)findViewById(R.id.name);
        sexView = (Button)findViewById(R.id.sex);
        nationView = (Button)findViewById(R.id.nation);
        addressView  = (TextView)findViewById(R.id.address);
        emailView  = (TextView)findViewById(R.id.email);
        friend_listlView  = (LinearLayout)findViewById(R.id.friend_list);
        underView  = (LinearLayout)findViewById(R.id.underview);
        fr_ttlView  = (LinearLayout)findViewById(R.id.fr_ttl);

        hs = (LinearLayout)findViewById(R.id.scrollerhrz);

    }


    /**
     * リスナー登録をする。
     */
    private void setListner () {

        edit_profile.setOnClickListener(submit_ClickListener);
        new_profile.setOnClickListener(new_submit_ClickListener);
        prof_list.setOnClickListener(prof_list_ClickListener);

    }

    private void depProfileData () {

        nameView.setText(name);
        if (user_icon != null) {
            Bitmap user_icon_bm= BitmapFactory.decodeByteArray(user_icon, 0, user_icon.length);
            my_iconView.setImageBitmap(user_icon_bm);
        }

        if (sex == 1) {
            sexView.setText("男性");
        } else if (sex == 2) {
            sexView.setText("女性");
        }

        nationView.setText(nation);
        emailView.setText(email);
        addressView.setText(address);




    }


    private void depFriendsList() {
        // 友人リストを表示する
        int c = userData.size();
        if (c > 0) {

            for (int i = 0; i < c; i++) {

                ImageView iconView = new ImageView(this);
                TextView user_nameView = new TextView(this);
                Map<String, Object> mapObject = userData.get(i);
                user_nameView.setText(mapObject.get("friend_name") + "");
                LinearLayout friendListBox = new LinearLayout(this);
                friendListBox.setOrientation(LinearLayout.VERTICAL);

                byte[] friend_icon = friendIconData.get(i);
                if (friend_icon != null) {
                    Bitmap friend_icon_bm= BitmapFactory.decodeByteArray(friend_icon, 0, friend_icon.length);
                    iconView.setImageBitmap(friend_icon_bm);
                } else {
                    Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.user_icon);
                    iconView.setImageBitmap(bm);
                }

                friendListBox.addView(iconView, createParam(WC, WC));
                friendListBox.addView(user_nameView,createParam(WC,WC));

                LinearLayout.LayoutParams layoutParams = createParam(WC, WC);
                layoutParams.setMargins(10,0,10,0);
                friend_listlView.addView(friendListBox,layoutParams);

                int id = (int) mapObject.get("id");
                friendListBox.setOnClickListener(new setProfile_ClickListener(id));

            }

        } else {

            TextView user_nameView = new TextView(this);
            user_nameView.setText("no friend!");
            friend_listlView.addView(user_nameView, createParam(WC, WC));

        }
    }




    private LinearLayout.LayoutParams createParam(int w, int h){
        return new LinearLayout.LayoutParams(w, h);
    }



    /**
     * プロフィール編集登録
     */
    private void  submit_Click (View v) {

        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("user_id", user_id);
        startActivity(intent);

    }

    /**
     * プロフィール新規登録
     */
    private void  new_submit_Click (View v) {

        Intent intent = new Intent(this, EditProfile.class);
        intent.putExtra("user_id", 0);
        startActivity(intent);

    }

    /**
     * 友人リスト画面へ遷移
     */
    private void  prof_list_Click () {

        Intent intent = new Intent(this, MyListViewActivity.class);
        startActivity(intent);

    }


    /**
     * 現在のプロフィールを切り替える
     * @param id
     */
    private void setCurrentProfile (int id) {

        dbObj = dbHelper.getReadableDatabase();
        String sql = "SELECT id,name,icon_blob,sex,nation,email,address FROM profile_table where id = " + id + " order by id desc";
        Cursor c = dbObj.rawQuery(sql, null);

        c.moveToFirst();
        if (c.getCount() > 0) {

            name = c.getString(1);
            user_id = c.getInt(0);
            user_icon = c.getBlob(2);
            sex = c.getInt(3);
            nation = c.getString(4);
            email = c.getString(5);
            address = c.getString(6);
        }
        c.close();
        dbObj.close();

        depProfileData();

    }


    /**
     * プロフィール欄と友達リストを表示する。
     */
    public void setProfileFriendsList() {

        dbObj = dbHelper.getReadableDatabase();
        String sql = "SELECT id,name,icon_blob,sex,nation,email,address FROM profile_table order by id desc";
        Cursor c = dbObj.rawQuery(sql, null);

        if (c.getCount() > 0) {
            int count = 0;
            while (c.moveToNext()) {
                if (count == 1) {
                    name = c.getString(1);
                    user_id = c.getInt(0);
                    user_icon = c.getBlob(2);
                    sex = c.getInt(3);
                    nation = c.getString(4);
                    email = c.getString(5);
                    address = c.getString(6);
                }

                Map<String, Object> map;
                map = new HashMap<String, Object>();
                map.put("id", c.getInt(0));
                map.put("friend_name", c.getString(1)+"");
                userData.add(map);
                friendIconData.add(c.getBlob(2));

                count++;

            }

        }
        c.close();
        dbObj.close();
        depProfileData();
        depFriendsList();

    }
}
