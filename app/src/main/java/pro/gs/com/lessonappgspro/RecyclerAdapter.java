package pro.gs.com.lessonappgspro;



import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter {
    private ArrayList<CustomDataObject> my_List;
    private Context context;

    public RecyclerAdapter(ArrayList<CustomDataObject> my_List, Context context) {
        this.my_List = my_List;
        this.context =context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //リスト一つ分のレイアウトを取り出す
        View view = LayoutInflater.from(context).inflate(R.layout.customlistview, parent, false);
        return new ViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder holderView = (ViewHolder) holder;

        //リスト項目一つ分のオブジェクトデータ（CutomDataObject）を取り出す。
        CustomDataObject item = my_List.get(position);

        //レイアウトにデータを流し込み、一つ分のレイアウトを作成する。
        //onBindViewHolder関数の処理が終わる同時にレイアウトがリストに追加される
        holderView.name.setText(item.getName());
        holderView.image.setImageResource(item.getImageId());



    }

    @Override
    public int getItemCount() {
        return this.my_List.size();
    }

    // 毎回findViewByIdをしなくてよくし、高速化に使用するholderクラス
    private static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView image;
        private ViewHolder(View v,Context context) {
            super(v);
            // CustomDataのデータをViewの各Widgetにセットする
            name = (TextView) v.findViewById(R.id.name);
            image = (ImageView) v.findViewById(R.id.image);
        }
    }
}