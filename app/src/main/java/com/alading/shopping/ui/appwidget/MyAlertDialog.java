package com.alading.shopping.ui.appwidget;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alading.shopping.AladingApplication;
import com.alading.shopping.R;

/**
 * Created by Administrator on 2015/8/29.
 */
public class MyAlertDialog  extends Dialog {


    private final Activity mContext;

    public MyAlertDialog(Activity context, int theme,View view) {
        super(context, theme);
        mContext = context;
        init(view);
    }

    public MyAlertDialog(Activity context,View view) {
        super(context);
        mContext = context;
        init(view);
    }


    private void init(View view) {
        setContentView(view);
        Window dialogWindow = this.getWindow();
        dialogWindow.setWindowAnimations(R.style.windowStyle);
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
//                lp.x = 0; // 新位置X坐标
//                lp.y = DensityUtil.dip2px(mContext, 80); // 新位置Y坐标
        lp.width = (int) AladingApplication.WIDTH; // 宽度
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT; // 高度
        dialogWindow.setAttributes(lp);
    }

}
