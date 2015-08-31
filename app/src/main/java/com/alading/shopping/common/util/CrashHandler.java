package com.alading.shopping.common.util;

import org.apache.log4j.Logger;

import android.content.Context;
import android.os.Looper;
import android.os.SystemClock;
import android.widget.Toast;
import com.alading.shopping.R;
/**
 * 接收系统不能处理的异常并提示
 * @author joe.xiao
 * @Date 2013-9-6下午4:27:03
 * @Email joe.xiao@infowarelab.com
 */
public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private static CrashHandler instance;

    private Thread.UncaughtExceptionHandler unCaughException;

    private Context mContext;

    private static final Logger log = Logger.getLogger(CrashHandler.class);

    private CrashHandler() {
        unCaughException = Thread.getDefaultUncaughtExceptionHandler();
    }

    public static CrashHandler getInstance() {
        if (instance == null) {
            instance = new CrashHandler();
        }
        return instance;
    }

    public void init(Context context) {
        this.mContext = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        log.error(thread.getName(), ex);
        showMessage();
        SystemClock.sleep(2000);
        android.os.Process.killProcess(android.os.Process.myPid());

    }

    private void showMessage() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Looper.prepare();
                Toast.makeText(mContext, mContext.getString(R.string.crashMeessage), Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }).start();
    }

}
