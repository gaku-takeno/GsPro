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

    /**
     * コンストラクタ
     * @param my_List
     * @param context
     */
    public RecyclerAdapter(ArrayList<CustomDataObject> my_List, Context context) {
        this.my_List = my_List;
        this.context =context;
    }

    /**
     * リストの項目一つ分のレイアウトファイルを読み込み、
     * そこで利用するviewを保持するホルダークラスのオブジェクトをrecyclerviewクラスに返す。
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //リストの項目一つ分のレイアウトファイルを読み込む
        View view = LayoutInflater.from(context).inflate(R.layout.customlistview, parent, false);

        //viewのホルダークラスのオブジェクトを作成する。
        return new ViewHolder(view, context);

    }


    /**
     * リストに表示するレイアウトを作成し、リストに追加（表示）する。
     * レイアウトデータを流し込み、一つ分のレイアウトを作成しリストに追加する。
     * CustomDataObjectの配列の個数分、onBindViewHolder関数を実行する。
     * CustomDataObjectの配列の個数分繰り返すことにより、リスト表示を完成させる。
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        ViewHolder holderView = (ViewHolder) holder;
        //現在、画面に現れたデータを得る
        CustomDataObject item = my_List.get(position);
        holderView.name.setText(item.getName());
        holderView.image.setImageResource(item.getImageId());

    }

    /**
     * リストで表示する項目の数を返す.
     * リストで表示する項目の数は、CustomDataObjectの配列の個数になる。
     * @return
     */
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