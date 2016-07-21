package pro.gs.com.GsProMemberList;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;

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
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder holderView = (ViewHolder) holder;
        //現在、画面に現れたデータを得る
        CustomDataObject item = my_List.get(position);
        holderView.name.setText(item.getName());


        // リクエストのキャンセル処理
        ImageLoader.ImageContainer imageContainer = (ImageLoader.ImageContainer)((ViewHolder) holder).icon.getTag();
        if (imageContainer != null) {
            imageContainer.cancelRequest();
        }


        ImageLoader.ImageListener listener = ImageLoader.getImageListener(holderView.icon, android.R.drawable.ic_menu_rotate, android.R.drawable.ic_delete);
        //画像のurlパスをキャッシュデータを格納する配列のキーとなる。
        ilImage.get(item.getIcon(), listener);


    }




    @Override
    public int getItemCount() {
        return this.my_List.size();
    }


    // 毎回findViewByIdをしなくてよくし、高速化に使用するholderクラス
    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView icon;

        private ViewHolder(View v) {
            super(v);
            // CustomDataのデータをViewの各Widgetにセットする
            name = (TextView) v.findViewById(R.id.name);
            icon = (ImageView) v.findViewById(R.id.icon);
        }

    }

}
