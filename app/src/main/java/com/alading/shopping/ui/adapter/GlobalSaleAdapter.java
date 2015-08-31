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

import com.alading.shopping.R;
import com.alading.shopping.common.util.LoaderImage;
import com.alading.shopping.modle.bean.GlobalSale;
import com.alading.shopping.modle.bean.Product;
import com.alading.shopping.modle.constant.HttpServerPort;
import com.alading.shopping.ui.activity.ProductDetailsActivity;
import com.alading.shopping.ui.activity.WebViewActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/8/27.
 */
public class GlobalSaleAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<GlobalSale> _list;
    private Context context;

    public GlobalSaleAdapter(Context context, List<GlobalSale> list) {
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
        if (view == null) {
            view = inflater.inflate(R.layout.home_global_sale_item, null);
            holder = new ViewHolder();
            holder.global_img = (ImageView) view.findViewById(R.id.global_img_spike);
            holder.mobile_name_prcie = (TextView) view.findViewById(R.id.mobile_name_prcie);
            holder.mobile_name_prcie_d = (TextView) view.findViewById(R.id.mobile_name_prcie_d);
            holder.mobile_name_referencePrice = (TextView) view.findViewById(R.id.mobile_name_referencePrice);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        LoaderImage.loadPhoto(HttpServerPort.PUBLIC_IMG + _list.get(i).getProduct().getFlashSaleIcon(), holder.global_img);
        holder.mobile_name_prcie.setText(String.format("¥%s", String.valueOf(_list.get(i).getSalePrice())));
        Double discount = (Double) _list.get(i).getSalePrice() / (Double) _list.get(i).getProduct().getPrice() * 10;
        holder.mobile_name_prcie_d.setText(new BigDecimal(discount).setScale(1, BigDecimal.ROUND_HALF_UP) + " 折");
        holder.mobile_name_referencePrice.setText(String.format("¥%s", String.valueOf(_list.get(i).getProduct().getPrice())));
        holder.mobile_name_referencePrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productId",_list.get(i).getProduct().getPid()+"");
                context.startActivity(intent);
            }
        });
        return view;
    }


    class ViewHolder {
        ImageView global_img;
        TextView mobile_name_prcie;
        TextView mobile_name_prcie_d;
        TextView mobile_name_referencePrice;


    }
}
