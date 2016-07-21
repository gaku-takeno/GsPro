package pro.gs.com.tablayoutviewpager.adapter;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import pro.gs.com.tablayoutviewpager.fragment.PageFragment;
import pro.gs.com.tablayoutviewpager.fragment.PageFragmentA;
import pro.gs.com.tablayoutviewpager.fragment.PageFragmentB;
import pro.gs.com.tablayoutviewpager.fragment.PageFragmentC;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "Tab_A", "Tab_B", "Tab_C" };
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {

//        return PageFragment.newInstance(position + 1);
        if (position == 0) {
            return new PageFragmentA();
        } else if (position == 1) {
            return new PageFragmentB();
        } else {
            return new PageFragmentC();
        }


    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }
}