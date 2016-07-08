package pro.gs.com.lessonappgsprovolley.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import pro.gs.com.lessonappgsprovolley.R;
import pro.gs.com.lessonappgsprovolley.SubActivity;
import pro.gs.com.lessonappgsprovolley.lib.BitmapCache;
import pro.gs.com.lessonappgsprovolley.objects.CustomDataObject;

public class RecyclerAdapter2 extends RecyclerView.Adapter {
    private ArrayList<CustomDataObject> my_List;
    private Context context;

    private ImageLoader ilImage;
    private RequestQueue rqQueue;


    public RecyclerAdapter2(ArrayList<CustomDataObject> my_List, Context context) {
        this.my_List = my_List;
        this.context =context;

        BitmapCache bmcache = new BitmapCache();
        rqQueue = Volley.newRequestQueue(this.context);
        ilImage = new ImageLoader(rqQueue, bmcache);


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customlistview, parent, false);
        return new ViewHolder(view, context);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder holderView = (ViewHolder) holder;
        //現在、画面に現れたデータを得る
        final CustomDataObject item = my_List.get(position);
        holderView.name.setText(item.getName());

        final String name = item.getName();
        View.OnClickListener myClickListner = new View.OnClickListener() {
            public void onClick(View v) {
                intentTrans(item);
            }
        };

        // nameをクリック時、詳細画面へ画面遷移する。
        holderView.name.setOnClickListener(myClickListner);

        holderView.link.setText(item.getLink());

        String lastStr = item.getName().substring(item.getName().length()-1, item.getName().length());

        String iconUrl;
        if (lastStr.equals("市")) {

            iconUrl = "http://nofiction.deca.jp/img/si.png";


        } else if (lastStr.equals("町")) {
            iconUrl = "http://nofiction.deca.jp/img/mati.jpg";

        } else {
            iconUrl = "http://nofiction.deca.jp/img/ku.png";
        }



        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holderView.icon, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        //画像のurlパスをキャッシュデータを格納する配列のキーとなる。
        ilImage.get(iconUrl, listener);


    }


    /**
     * 詳細画面へ画面遷移する
     * @param customDataObject
     */
    private void intentTrans (CustomDataObject customDataObject) {

        Intent intent = new Intent(this.context, SubActivity.class);
        intent.putExtra("customDataObject",customDataObject);
        context.startActivity(intent);

    }


    @Override
    public int getItemCount() {
        return this.my_List.size();
    }


    // 毎回findViewByIdをしなくてよくし、高速化に使用するholderクラス
    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView link;
        private ImageView icon;

        private ViewHolder(View v,Context context) {
            super(v);
            // CustomDataのデータをViewの各Widgetにセットする
            name = (TextView) v.findViewById(R.id.name);
            link = (TextView) v.findViewById(R.id.link);
            icon = (ImageView) v.findViewById(R.id.icon);
        }

    }

}