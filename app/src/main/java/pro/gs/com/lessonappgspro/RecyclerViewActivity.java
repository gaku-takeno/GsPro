package pro.gs.com.lessonappgspro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mylist);

        String response = "{'mylists' : [" +
                "{'name' : '武田','icon' : 'tokyo','sex':1,'age':32}," +
                "{'name' : '太田','icon' : 'oosaka','sex':1,'age':22}," +
                "{'name' : '松井','icon' : 'oosaka','sex':0,'age':19}," +
                "{'name' : '後藤','icon' : 'fukuoka','sex':0,'age':52}" +
                "]}";

        //リストの項目毎のデータオブジェクトの配列を完成させる。
        ArrayList<CustomDataObject> customDataObjectArrayList = jsonParse(response);

//        ArrayList<CustomDataObject> customDataObjectArrayList = new ArrayList<>();
//
//        //リスト一つ分のデータオブジェクトを作成し、配列オブジェクトのArrayList(customDataObjectArrayList)に追加している。
//        CustomDataObject customDataObject = new CustomDataObject();
//        customDataObject.setName("武田");
//        customDataObject.setImageId(R.drawable.tokyo);
//        customDataObject.setAge(22);
//        customDataObject.setSex(0);//0 女性 1:男性
//        customDataObjectArrayList.add(customDataObject);
//
//
//        //リスト一つ分のデータオブジェクトを作成し、配列オブジェクトのArrayList(customDataObjectArrayList)に追加している。
//        CustomDataObject customDataObject2 = new CustomDataObject();
//        customDataObject2.setName("石井");
//        customDataObject2.setImageId(R.drawable.fukuoka);
//        customDataObject2.setAge(50);
//        customDataObject2.setSex(1);//0 女性 1:男性
//
//        customDataObjectArrayList.add(customDataObject2);
//
//
//        //リスト一つ分のデータオブジェクトを作成し、配列オブジェクトのArrayList(customDataObjectArrayList)に追加している。
//        CustomDataObject customDataObject3 = new CustomDataObject();
//        customDataObject3.setName("宮下");
//        customDataObject3.setImageId(R.drawable.oosaka);
//        customDataObject3.setAge(34);
//        customDataObject3.setSex(1);//0 女性 1:男性
//        customDataObjectArrayList.add(customDataObject3);
//
//        //リスト一つ分のデータオブジェクトを作成し、配列オブジェクトのArrayList(customDataObjectArrayList)に追加している。
//        CustomDataObject customDataObject4 = new CustomDataObject();
//        customDataObject4.setName("イトウ");
//        customDataObject4.setImageId(R.drawable.oosaka);
//        customDataObject4.setAge(33);
//        customDataObject4.setSex(0);//0 女性 1:男性
//        customDataObjectArrayList.add(customDataObject4);
//
//
//

        //アダプタークラスの引数にリスト一つ分のデータオブジェクトの配列(customDataObjectArrayList)を引数にいれている。
        RecyclerAdapter customAdapater = new RecyclerAdapter(customDataObjectArrayList,this);

        //リストの生成が行われる。
        //RecyclerAdapterのonCreateViewHolder/onBindViewHolderの関数のロジックが走る。
        //setAdapterを実行させないとリストは作成されない。
        recyclerView.setAdapter(customAdapater);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        // ここで横方向に設定
//        manager.setOrientation(LinearLayoutManager.HORIZONTAL);

        // ここで立て方向に設定
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

        // ここで2列にグリッド式に表示する
//        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));


    }


    /**
     * リストの項目毎のデータオブジェクトの配列を完成させる。
     * @param response JSONで表記されたString型のデータ
     * @return customDataObjectArrayList リストの項目毎のデータオブジェクトの配列
     *
     */
    private ArrayList<CustomDataObject> jsonParse (String response) {

//        String url = "http://nofiction.deca.jp/cities.json";
        ArrayList<CustomDataObject> customDataObjectArrayList = new ArrayList<>();
        if (response != null) {
            try {

                //JSONデータに変換する
                JSONObject responseJsonObject = new JSONObject(response);
                try {

                    //JSONデータをパースして、必要な値を一つ一つ取り出す
                    JSONArray pinpointLocations = responseJsonObject.getJSONArray("mylists");
                    for (int c = 0; c<pinpointLocations.length(); c++) {
                        JSONObject v1 = (JSONObject) pinpointLocations.get(c);
                        String name = v1.getString("name");
                        int sex = v1.getInt("sex");
                        int age = v1.getInt("age");

                        String icon = v1.getString("icon");
                        int iconid = R.drawable.my_icon;

                        if (icon.equals("tokyo")) {
                            iconid = R.drawable.tokyo;
                        } else if (icon.equals("fukuoka")) {
                            iconid = R.drawable.fukuoka;
                        } else if (icon.equals("oosaka")) {
                            iconid = R.drawable.oosaka;
                        }

                        //リスト一つ分のデータオブジェクトを作成し、
                        // 配列オブジェクトのArrayList(customDataObjectArrayList)に追加している。
                        CustomDataObject customDataObject = new CustomDataObject();
                        customDataObject.setName(name);
                        customDataObject.setAge(age);
                        customDataObject.setSex(sex);
                        customDataObject.setImageId(iconid);
                        customDataObjectArrayList.add(customDataObject);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } catch (JSONException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }
        }
        return customDataObjectArrayList;

    }





}