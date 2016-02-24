package com.f1reking.classpanel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.f1reking.classpanel.R;
import com.f1reking.classpanel.entity.ClassEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HuangYH on 2016/2/22.
 */
public class GridViewItemAdapter extends BaseAdapter {

    private List<ClassEntity> classEntityList;
    private Context context;
    private int pageSize; //ViewPager 页码
    private int pageItemCount; //定义为8个item
    private int totalSize; // 传进来的List的总长度

    public GridViewItemAdapter(List<?> list, Context context) {
        this.classEntityList = (List<ClassEntity>) list;
        this.context = context;
    }

    public GridViewItemAdapter(List<?> list, Context context, int pageSize, int pageItemCount) {
        this.context = context;
        this.pageSize = pageSize;
        this.pageItemCount = pageItemCount;
        classEntityList = new ArrayList<ClassEntity>();
        totalSize = list.size();
        int list_index = pageSize * pageItemCount;
        for (int i = list_index; i < list.size(); i++) {
            classEntityList.add((ClassEntity)list.get(i));
        }
    }

    @Override
    public int getCount() {
        int size = totalSize / pageItemCount;
        if (pageSize == size) {
            return totalSize - pageItemCount * pageSize;
        } else {
            return pageItemCount;
        }
    }

    @Override
    public Object getItem(int position) {
        return classEntityList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (null == convertView) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_gridview_type, null);
            holder.ivType = (ImageView) convertView.findViewById(R.id.iv_type);
            holder.tvTypeName = (TextView) convertView.findViewById(R.id.tv_typeName);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.updateViews(position);
        return convertView;
    }

    class ViewHolder {
        private ImageView ivType;
        private TextView tvTypeName;

        protected void updateViews(int position) {
            ivType.setImageResource(classEntityList.get(position).getClassIcon());
            tvTypeName.setText(classEntityList.get(position).getClassName());
        }
    }
}
