package pro.gs.com.lessonappgsprovolley.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

import pro.gs.com.lessonappgsprovolley.objects.CustomDataObject;
import pro.gs.com.lessonappgsprovolley.R;
import pro.gs.com.lessonappgsprovolley.SubActivity;
import pro.gs.com.lessonappgsprovolley.lib.BitmapCache;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<CustomDataObject> my_List;
    private Context context;
    private ImageLoader ilImage;
    private RequestQueue rqQueue;
    public RecyclerAdapter(ArrayList<CustomDataObject> my_List,Context context) {
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
        CustomDataObject item = my_List.get(position);
        holderView.name.setText(item.getName());

        final String name = item.getName();
        View.OnClickListener myClickListner = new View.OnClickListener() {
            public void onClick(View v) {
                intentTrans(name);
            }
        };

        // nameをクリック時、詳細画面へ画面遷移する。
        holderView.name.setOnClickListener(myClickListner);

        holderView.link.setText(item.getLink());

        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holderView.icon, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        //画像のurlパスをキャッシュデータを格納する配列のキーとなる。
        ilImage.get(item.getIcon(), listener);

    }


    /**
     * 詳細画面へ画面遷移する
     * @param name
     */
    private void intentTrans (String name) {

        Intent intent = new Intent(this.context, SubActivity.class);
        intent.putExtra("name", name);
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