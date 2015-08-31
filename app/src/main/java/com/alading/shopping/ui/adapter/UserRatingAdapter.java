package com.alading.shopping.ui.adapter;

/**
 * Created by Administrator on 2015/8/29.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.alading.shopping.modle.bean.UserRating;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import com.alading.shopping.R;

/**
 * 用户评价适配
 */
public class UserRatingAdapter extends BaseAdapter implements Observer {

    private LayoutInflater inflater;
    private Context context;
    private List<UserRating> _userRatingList;

    private ArrayList<String> arrayList = new ArrayList<>();

    public UserRatingAdapter(Context context, List<UserRating> userRatingList) {
        this.context = context;
        this._userRatingList = userRatingList;
        inflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return _userRatingList == null ? 0 : _userRatingList.size();
    }

    @Override
    public Object getItem(int position) {
        return _userRatingList == null ? 0 : _userRatingList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return _userRatingList == null ? 0 : position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            view = inflater.inflate(R.layout.rating_item, null);
            holder = new ViewHolder();
            holder.user_rating_text1 = (TextView) view.findViewById(R.id.user_rating_text1);
            holder.user_rating_text2 = (TextView) view.findViewById(R.id.user_rating_text2);
            holder.user_rating_ratingBar = (RatingBar) view.findViewById(R.id.user_rating_ratingBar);
            holder.user_rating_text3 = (TextView) view.findViewById(R.id.user_rating_text3);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        holder.user_rating_text1.setText(_userRatingList.get(position).getUsername());
        holder.user_rating_text2.setText(_userRatingList.get(position).getCreateTime());
        holder.user_rating_ratingBar.setRating(_userRatingList.get(position).getStar());
        holder.user_rating_text3.setText(_userRatingList.get(position).getContent());
        //屏蔽星级选择
        holder.user_rating_ratingBar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View arg0, MotionEvent arg1) {
                return true;
            }
        });
        return view;
    }

    @Override
    public void update(Observable observable, Object data) {
        notifyDataSetChanged();
    }

    class ViewHolder {
        RatingBar user_rating_ratingBar;
        TextView user_rating_text1,user_rating_text2,user_rating_text3;
    }
}
