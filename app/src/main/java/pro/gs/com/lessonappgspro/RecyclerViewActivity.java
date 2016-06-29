package pro.gs.com.lessonappgspro;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.mylist);
        ArrayList<CustomDataObject> customDataObjectArrayList = new ArrayList<>();

        //リスト一つ分のデータオブジェクトを配列（ArrayList）に追加していく。
        CustomDataObject customDataObject = new CustomDataObject();
        customDataObject.setName("東京");
        customDataObject.setImageId(R.drawable.tokyo);
        customDataObjectArrayList.add(customDataObject);

        //リスト一つ分のデータオブジェクトを配列（ArrayList）に追加していく。
        CustomDataObject customDataObject2 = new CustomDataObject();
        customDataObject2.setName("福岡");
        customDataObject2.setImageId(R.drawable.fukuoka);
        customDataObjectArrayList.add(customDataObject2);

        //リスト一つ分のデータオブジェクトを配列（ArrayList）に追加していく。
        CustomDataObject customDataObject3 = new CustomDataObject();
        customDataObject3.setName("大阪");
        customDataObject3.setImageId(R.drawable.oosaka);
        customDataObjectArrayList.add(customDataObject3);


        //配列（ArrayList<CustomDataObject>）をRecyclerAdapterに渡す。
        RecyclerAdapter customAdapater = new RecyclerAdapter(customDataObjectArrayList,this);
        //RecyclerViewウィジェットにアダプターをセットする。
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
}