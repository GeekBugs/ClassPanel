package com.f1reking.classpanel.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by HuangYH on 2016/2/22.
 */
public class ViewPagerAdapter extends PagerAdapter {

    private List<View> lists;

    public ViewPagerAdapter(List<View> lists) {
        this.lists = lists;
    }

    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        // 解决View只能滑动两屏的方法
        try {
            ViewGroup parent = (ViewGroup) lists.get(position).getParent();
            if (null != parent) {
                parent.removeAllViews();
            }
            ((ViewPager) container).addView(lists.get(position), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return lists.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        try {
            ((ViewPager) container).removeView(lists.get(position));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
