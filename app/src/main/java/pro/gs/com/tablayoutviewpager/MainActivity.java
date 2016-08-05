package pro.gs.com.tablayoutviewpager;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import pro.gs.com.tablayoutviewpager.adapter.MyFragmentPagerAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        //スワイプすることによって切り替わる各画面をViewPagerに当て込む処理を担う
        //FragmentPagerAdapterのオブジェクトをviewPagerウィジェットが持つsetAdapterメソッドの引数に渡すことで、
        //ViewPagerウィジェットとfragmentを紐付けている。
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));


        // Give the TabLayout the ViewPager
        TabLayout tabLayout = (TabLayout) findViewById(R.id.sliding_tabs);

        //タブメニューのUIを完成させる。
        //MyFragmentPagerAdapter.getPageTitleで返されたテキストが各画面のメニュー名となる。
        tabLayout.setupWithViewPager(viewPager);

        //TabLayoutでタブを均等に配置する
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    }
}
