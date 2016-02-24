package com.f1reking.classpanel.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.f1reking.classpanel.R;
import com.f1reking.classpanel.adapter.GridViewItemAdapter;
import com.f1reking.classpanel.adapter.ViewPagerAdapter;
import com.f1reking.classpanel.entity.ClassEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangYH on 2016/2/22.
 */
public class GridViewGallery extends LinearLayout {

    private Context context;
    private List<ClassEntity> list = new ArrayList<ClassEntity>();
    private ViewPager viewPager;
    private LinearLayout llDots;
    private ImageView[] dots;
    private int currentIndex; //ViewPager当前页
    private int viewPagerSize; //ViewPager页数
    private int pageItemCount = 8; //默认一页item8个

    private List<View> listViews;

    public GridViewGallery(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.list = null;
        initView();
    }

    public GridViewGallery(Context context, List<?> list) {
        super(context);
        this.context = context;
        this.list = (List<ClassEntity>) list;
        initView();
        initDots();
        setAdapter();
    }

    private void initView() {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_home_type, null);
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_type);
        llDots = (LinearLayout) view.findViewById(R.id.ll_dots);
        addView(view);
    }

    private void initDots() {
        // 计算出总页数
        if (list.size() % pageItemCount == 0) {
            viewPagerSize = list.size() / pageItemCount;
        } else {
            viewPagerSize = list.size() / pageItemCount + 1;

        }

        if (0 < viewPagerSize) {
            llDots.removeAllViews();
            if (1 == viewPagerSize) {
                llDots.setVisibility(GONE);
            } else if (1 < viewPagerSize) {
                llDots.setVisibility(VISIBLE);
                for (int j = 0; j < viewPagerSize; j++) {
                    ImageView image = new ImageView(context);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(20, 20); //dot的宽高
                    params.setMargins(10, 0, 10, 0);
                    image.setBackgroundResource(R.mipmap.dot_unselected);
                    llDots.addView(image, params);
                }
            }
        }
        if (1 != viewPagerSize) {
            dots = new ImageView[viewPagerSize];
            for (int i = 0; i < viewPagerSize; i++) {
                dots[i] = (ImageView) llDots.getChildAt(i);
            }
            currentIndex = 0; //当前页
            dots[currentIndex].setBackgroundResource(R.mipmap.dot_selected);
            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                }

                @Override
                public void onPageSelected(int position) {
                    setCurDot(position);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                }
            });
        }
    }

    /**
     * 当前底部小圆点
     *
     * @param position
     */
    private void setCurDot(int position) {
        if (position < 0 || position > viewPagerSize - 1 || currentIndex == position) {
            return;
        }
        for (int i = 0; i < dots.length; i++) {
            dots[i].setBackgroundResource(R.mipmap.dot_unselected);
        }
        dots[position].setBackgroundResource(R.mipmap.dot_selected);
        currentIndex = position;
    }


    private void setAdapter() {
        listViews = new ArrayList<View>();
        for (int i = 0; i < viewPagerSize; i++) {
            listViews.add(getViewPagerItem(i));
        }
        viewPager.setAdapter(new ViewPagerAdapter(listViews));
    }


    /**
     * 设置ViewPager中每个页面的GridView布局
     *
     * @param index
     * @return
     */
    private View getViewPagerItem(int index) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.item_viewpager_home, null);
        GridView gridView = (GridView) layout.findViewById(R.id.gridview_type);
        GridViewItemAdapter adapter = new GridViewItemAdapter(list, context, index, pageItemCount);

        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int i = position + 1;
                Toast.makeText(context, "当前点击的是第" + i + "个", Toast.LENGTH_SHORT).show();
            }
        });
        return gridView;
    }

}
