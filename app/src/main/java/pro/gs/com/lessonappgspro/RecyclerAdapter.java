package pro.gs.com.lessonappgspro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
        return new ViewHolder(view);

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

        CustomDataObject item = my_List.get(position);
        holderView.name.setText(item.getName());
        holderView.image.setImageResource(item.getImageId());
//        holderView.age.setText(String.valueOf(item.getAge()));
        holderView.age.setText(Integer.toString(item.getAge()));


        String sexTxt;
        ////0 女性 1:男性
        if (item.getSex() ==  0) {
            sexTxt = "女";
            //テキストの色を変える。
            holderView.name.setTextColor(Color.RED);
        } else {
            sexTxt = "男";
            //テキストの色を変える。
            holderView.name.setTextColor(Color.BLUE);
        }
        holderView.sex.setText(sexTxt);

        final String name = item.getName();

        //リストをタップするとその詳細画面へ遷移する。
        holderView.box.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(context.getApplicationContext(),SubActivity.class);
                intent.putExtra("name",name);
                context.startActivity(intent);
            }
        });

    }

    /**
     * リストで表示する項目の数を返す.
     * リストで表示する項目の数は、CustomDataObjectの配列の個数になる。
     * @return
     */
    @Override
    public int getItemCount() {
        return my_List.size();
    }


    // 毎回findViewByIdをしなくてよくし、高速化に使用するholderクラス
    private static class ViewHolder extends RecyclerView.ViewHolder {

        private LinearLayout box;
        private TextView name;
        private ImageView image;
        private TextView age;
        private TextView sex;

        private ViewHolder(View v) {
            super(v);
            // CustomDataのデータをViewの各Widgetにセットする
            box = (LinearLayout) v.findViewById(R.id.box);
            name = (TextView) v.findViewById(R.id.name);
            image = (ImageView) v.findViewById(R.id.image);
            age = (TextView) v.findViewById(R.id.age);
            sex = (TextView) v.findViewById(R.id.sex);


        }

    }

}