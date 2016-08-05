package pro.gs.com.tablayoutviewpager.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pro.gs.com.tablayoutviewpager.fragment.PageFragmentA;
import pro.gs.com.tablayoutviewpager.fragment.PageFragmentB;
import pro.gs.com.tablayoutviewpager.fragment.PageFragmentC;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[] = new String[] { "Page_A", "Page_B", "Page_C" };
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    /**
     * スワイプして画面(Fragment)を切り替わる度に実行される。
     * positionは、切り替った画面のindex値。１番目の画面は0,2番目の画面は1,3番目の画面は2となる。
     * 渡されたposition値により、画面のFragmentオブジェクトを切り分けて、返している。
     * @param position
     * @return
     */
    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PageFragmentA();
        } else if (position == 1) {
            return new PageFragmentB();
        } else {
            return new PageFragmentC();
        }
    }

    /**
     * TabLayoutウィジェットに表示されるメニューボタンのテキストとなる。
     * @param position
     * @return
     */
    @Override
    public CharSequence getPageTitle(int position) {

        return tabTitles[position];

    }
}