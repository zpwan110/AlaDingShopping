package com.alading.shopping.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.HomeCategorys;
import com.alading.shopping.modle.constant.HttpServerPort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 * 首页自定义GridView
 */
public class EntranceSaleAdapter extends BaseAdapter {

    private final Context context;
    private List<HomeCategorys> _list = null;
    private LayoutInflater inflater;

    public EntranceSaleAdapter(Context context,List<HomeCategorys> list){
        inflater = LayoutInflater.from(context);
        this.context = context;
        this._list =list;
    }

    @Override
    public int getCount() {
        return _list.size();
    }

    @Override
    public Object getItem(int i) {
        return _list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.home_categorys_item, null);
            viewHolder = new ViewHolder();
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.itemImage);
            viewHolder.textView = (TextView) convertView.findViewById(R.id.itemName);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        LoaderImage.loadPhoto(
                HttpServerPort.PUBLIC_IMG +_list.get(i).getIcon(),
                viewHolder.imageView);
        viewHolder.textView.setText(/*_list.get(i).getName()*/ "");
        return convertView;
    }

    private static class ViewHolder {
        private TextView textView;
        private ImageView imageView;
    }
}
