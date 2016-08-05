package pro.gs.com.tablayoutviewpager.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import pro.gs.com.tablayoutviewpager.R;

public class PageFragmentC extends Fragment {

    private String mPage;

    /**
     * フラグメントにも、アクティビティと同様のコールバック メソッドが含まれている。
     * https://developer.android.com/guide/components/fragments.html#Creating
     */

    /**
     * フラグメントの作成時にシステムが呼び出します。
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = "PageC";

    }


    /**
     * フラグメントが初めてユーザー インターフェースを描画するタイミングでシステムがこれを呼び出します。
     * フラグメントの UI を描画するには、このメソッドからフラグメントのレイアウトのルートとなっている View を返す必要があります
     * https://developer.android.com/guide/components/fragments.html#Creating
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView textView = (TextView) view;
        textView.setText(mPage);
        return view;
    }


}