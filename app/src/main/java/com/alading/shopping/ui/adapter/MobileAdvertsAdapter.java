package com.alading.shopping.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.HomeMobileAdverts;
import com.alading.shopping.modle.constant.HttpServerPort;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 * 广告栏索引
 */
public class MobileAdvertsAdapter extends BaseAdapter{
    private final Context context;
    private LayoutInflater inflater;
    private List<HomeMobileAdverts> _list;

    public MobileAdvertsAdapter(Context context,List<HomeMobileAdverts> list){
        inflater = LayoutInflater.from(context);
        this._list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return _list == null ? 0 : _list.size();
    }

    @Override
    public Object getItem(int i) {
        return _list == null ? 0 : _list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return _list == null ? 0 : i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.home_mobileadverts_item,null);
            holder = new ViewHolder();
            holder.mobile_Img = (ImageView)view.findViewById(R.id.mobile_img);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG+_list.get(i).getIcon(), holder.mobile_Img);

        return view;
    }

    class ViewHolder{
        ImageView mobile_Img;
    }
}
