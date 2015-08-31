package com.alading.shopping.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.Product;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.activity.ProductDetailsActivity;

/**
 * Created by Administrator on 2015/8/27.
 */
public class GlobalSelectedAdapter extends BaseAdapter implements Observer {
    private final Context context;
    private LayoutInflater inflater;
    public List<Product> _list;

    public GlobalSelectedAdapter(Context context,List<Product> list) {
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
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            view = inflater.inflate(R.layout.home_global_selected_item,null);
            holder = new ViewHolder();
            holder.global_img = (ImageView)view.findViewById(R.id.global_img);
            holder.global_Img_country = (ImageView)view.findViewById(R.id.global_img_country);
            holder.global_Delivery = (TextView)view.findViewById(R.id.global_delivery);
            holder.global_Name = (TextView)view.findViewById(R.id.global_name);
            holder.global_Price = (TextView)view.findViewById(R.id.global_price);
            holder.global_Price_m = (TextView)view.findViewById(R.id.global_price_m);
            holder.global_Price_d = (TextView)view.findViewById(R.id.global_price_d);
            holder.global_ReferencePrice = (TextView)view.findViewById(R.id.global_referencePrice);
            view.setTag(holder);
        }else {
            holder = (ViewHolder)view.getTag();
        }
        LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG + _list.get(i).getIcon(), holder.global_img);
        LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG + _list.get(i).getP_icon(), holder.global_Img_country);
        holder.global_Delivery.setText(_list.get(i).getDeliveryInformation());
        holder.global_Name.setText(_list.get(i).getName());
        holder.global_Price.setText(_list.get(i).getPrice() + "");


        Double discount = (Double)_list.get(i).getPrice()/(Double)_list.get(i).getReferencePrice() * 10;
        holder.global_Price_d.setText(new BigDecimal(discount).setScale(1, BigDecimal.ROUND_HALF_UP) + " 折");
        holder.global_ReferencePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG );
        holder.global_ReferencePrice.setText(new java.text.DecimalFormat("#.00").format(_list.get(i).getReferencePrice()) + "");
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productId",_list.get(i).getPid()+"");
                context.startActivity(intent);
            }
        });
        return view;
    }

    @Override
    public void update(Observable observable, Object data) {
        notifyDataSetChanged();
    }


    class ViewHolder{
        TextView global_Delivery,global_Name,global_Price,global_ReferencePrice,global_Price_m,global_Price_d;
        ImageView global_img,global_Img_country;
    }
}
