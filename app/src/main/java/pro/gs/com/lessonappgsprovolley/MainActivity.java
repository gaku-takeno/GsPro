package pro.gs.com.lessonappgsprovolley;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import pro.gs.com.lessonappgsprovolley.lib.BitmapCache;
import pro.gs.com.lessonappgsprovolley.lib.BitmapCache2;
import pro.gs.com.lessonappgsprovolley.lib.MyErrorSample;
import pro.gs.com.lessonappgsprovolley.lib.MyException;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //volleyを使ってインタネット上のデータをロードする。
        loadDataByVolley();



        //volleyを使ってインタネット上の画像を表示する。
        displayImageByVolley();


        //リスト画面へのリンクテキストを表示する。
        displayListView();






//        //Byte配列について
//        transactionByteArray();
//
//        //Interfaceについて
//        transactionInterface();
//
//        //try catchの例外処理について。
//        transactionException();



    }





    /**
     * volleyを使ってインタネット上のデータをロードする。
     */
    private void loadDataByVolley () {

        // 東京都の天気情報
//        String api_url = "http://weather.livedoor.com/forecast/webservice/json/v1?city=130010";
        String api_url = "http://nofiction.deca.jp/cities.json";


        //パーサー
        //http://www.ctrlshift.net/jsonprettyprinter/

//        RequestQueue mQueue;
        RequestQueue mQueue = Volley.newRequestQueue(this);
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



//                        try {
//                            JSONArray pinpointLocations = response.getJSONArray("pinpointLocations");
//
//                            for (int c = 0; c<pinpointLocations.length(); c++) {
//
//                                JSONObject v1 = (JSONObject) pinpointLocations.get(c);
//                                String link = v1.getString("link");
//                                String name = v1.getString("name");
//                                String icon = v1.getString("icon");
//
//                                Log.d("gs_api",name);
//                                //cityCodeを取得する
//                                String[] linkArg = link.split("/", 0);
//                                int index = linkArg.length-1;
//                                int cityCode = Integer.parseInt(linkArg[index]);
//
//
//                            }
//
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }

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



    /**
     * volleyを使ってインタネット状の画像を表示する。
     */
    private void displayImageByVolley() {

        //ネット上の画像のURL
        String imageUrl = "http://blog-imgs-45.fc2.com/u/r/a/uranaiai/singetu3.jpg";

        //ロードした画像を表示するImageViewのオブジェクトを取り出す。
        ImageView imageView = (ImageView) findViewById(R.id.my_image);

        BitmapCache bmcache = new BitmapCache();
//        BitmapCache2 bmcache = new BitmapCache2();

        RequestQueue rqQueue = Volley.newRequestQueue(this);
//        ImageLoader ilImage = new ImageLoader(rqQueue, bmcache.getImageChache());
        ImageLoader ilImage = new ImageLoader(rqQueue, bmcache);

        //リスナーとは：何らかの事象（イベント）が発生したときに起動されるよう対応付けられた関数
        //ネットワークから画像をロードする為のリスナーを取得する
        ImageLoader.ImageListener listener = ImageLoader.getImageListener(imageView, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);

        //キャッシュがあれば、キャッシュから、
        // キャッシュがなければ、ネットから画像をロードする。
        //画像のurlパスをキャッシュデータを格納する配列のキーとなる。
        ilImage.get(imageUrl, listener);


    }




    private void displayListView() {


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

                    //Volleyを使ってネットからSONデータをロードして、recyclerviewに表示させる。
                    Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                    startActivity(intent);
                }
            });
        }

    }




    /**
     * interfaceについて。
     */
    private void transactionInterface () {


        MyInterface myInterface = new MyInterface() {
            @Override
            public String getYourName(String name) {
                return null;
            }

            @Override
            public void putYourname(String name) {

            }
        };


        TextView recyclerView = (TextView) findViewById(R.id.recyclerView);
        if (recyclerView != null) {

            recyclerView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {

                    //Volleyを使ってネットからSONデータをロードして、recyclerviewに表示させる。
                    Intent intent = new Intent(getApplicationContext(), RecyclerViewActivity.class);
                    startActivity(intent);
            }

            });
        }






    }


    /**
     * Byte配列について。
     *
     * byteは、javaのデータ型の中で一番小さい単位の型です。
     * byteは数値を表すものですが、様々なデータは、byteデータの集まり(byte配列)で表現されています。
     *  String str = "あ";
        System.out.println(str.getBytes().length);
        UTF-8の環境で実行すると、
        UTF-8は日本語のコードを3バイトで表現するので、str.getBytes()で取得されたbyte配列の長さは3になります。
        日本語(一文字)を3バイトで表現→byte3つで表現→byte配列の長さは3
        ということです。

        このように
        データはbyte配列で表現することもできる。

        byte配列から画像を生成することも可能であり、画像データを扱い場合は、場面によって、
        byte配列 => Bitmap
        または、
        Bitmap =>  byte配列
        に変換する。
     *
     *
     *
     */
    private void transactionByteArray () {

        byte[] strByte;
        //======================================================
        try {
            //StringからByte配列に変更するプログラム
            String str = "test";
            strByte = str.getBytes("UTF-8");
            System.out.println("Stringからbyte配列に変換した結果 ： " + strByte);
//        =>Stringからbyte配列に変換した結果 ： [B@175d6ab


            //getBytes関数にてthrows宣言により、
            // 例外が発生した場合、例外クラスUnsupportedEncodingExceptionにスローすることになる。
            strByte = "test".getBytes("UTF-8");
            String result = new String(strByte, "UTF-8");
            System.out.println("byte配列からStringに変換した結果 ： " + result);
            //=>byte配列からStringに変換した結果 ： test


        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //======================================================


    }



    /**
     * 例外処理とtry catchについて。
     */
    private void transactionException () {

        //======================================================
        String moji = null;
        try {
            int len = moji.length();
        } catch (NullPointerException e) {
            Log.d("error","int len = moji.length();の場合=>NullPointerExceptionをキャッチしました。::"+e);
        }


        try {
            int len = "".length();
        } catch (NullPointerException e) {
            Log.d("error","int len = \"\".length()の場合NullPointerExceptionをキャッチしました。::"+e);
        }


        //==========================================================
        //自作例外クラス
        try {
            MyErrorSample myErrorSample = new MyErrorSample();
            myErrorSample.methodB(0);

        } catch (MyException e) {
            Log.d("error",e.getMessage()+":"+e.getErrorMes());
        }


    }

}
