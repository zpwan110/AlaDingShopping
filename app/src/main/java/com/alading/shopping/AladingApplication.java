package com.alading.shopping;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;
import android.telephony.TelephonyManager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import com.alading.library.TAApplication;
import com.alading.library.util.netstate.TANetChangeObserver;
import com.alading.library.util.netstate.TANetWorkUtil;
import com.alading.library.util.netstate.TANetworkStateReceiver;
import com.alading.shopping.common.util.CrashHandler;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import org.apache.log4j.Level;
import de.mindpipe.android.logging.log4j.LogConfigurator;
import java.io.File;

/**
 * Created by Administrator on 2015/8/26.
 */
public class AladingApplication extends TAApplication implements TANetChangeObserver {
    public static int WIDTH;
    public static int HEIGHT;
    public static int DENSITY;
    public  AladingApplication context;
    private static String DEVICE_ID;

    @Override
    protected void onAfterCreateApplication() {
        super.onAfterCreateApplication();
        // 获取屏幕的长宽高
        DisplayMetrics dm = this.getResources().getDisplayMetrics();
        WIDTH = dm.widthPixels;
        HEIGHT = dm.heightPixels;
        DENSITY = (int) dm.density;
        context = this;
        registerNetworkListen();
        initImageLoader(context);
        initCrashHandler();
        initLog4j();
    }

    private void registerNetworkListen(){
        TANetworkStateReceiver.registerObserver(this);
        TANetworkStateReceiver.registerNetworkStateReceiver(this);
    }
    private void unRegisterNetworkListen(){
        TANetworkStateReceiver.removeRegisterObserver(this);
        TANetworkStateReceiver.unRegisterNetworkStateReceiver(this);
    }
    /**
     * 网络连接类型
     * @param type
     */
    @Override
    public void onConnect(TANetWorkUtil.netType type) {
        ConnectivityManager mgr = (ConnectivityManager) this
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] info = mgr.getAllNetworkInfo();
        boolean isConnected = false;
        for (NetworkInfo workInfo:info) {
            if(workInfo.getState() ==NetworkInfo.State.CONNECTED){
                isConnected=true;
            }
        }
        if(isConnected){
            Toast.makeText(this, "网络已连接", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 连接断开 或者未连接
     */
    @Override
    public void onDisConnect() {
        Toast.makeText(this, "网络已断开", Toast.LENGTH_SHORT).show();
    }

    /**
     * 获取本机设备号
     * @return
     */
    public static String getDevice(Application context){
        TelephonyManager tm = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
        DEVICE_ID = tm.getDeviceId();//设备ID
        return DEVICE_ID;
    }
    public static void initImageLoader(Context context) {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .diskCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO)
                .build();
        ImageLoader.getInstance().init(config);
    }
    private void initLog4j() {
        final LogConfigurator logConfigurator = new LogConfigurator();
        String date = String.valueOf(System.currentTimeMillis());
        if (android.os.Environment.getExternalStorageState().equals(
                android.os.Environment.MEDIA_MOUNTED)){
            logConfigurator.setFileName(Environment.getExternalStorageDirectory() + File.separator + "aladingLogPhone" + File.separator +"aladingLogPhone"+date+".log");
        }else{
            logConfigurator.setFileName( File.separator + "aladingLog" + File.separator + "aladingLog"+date+".log");
        }
        Log.i("aladingLog", File.separator + "aladingLog" + File.separator + "aladingLog.log");
        logConfigurator.setRootLevel(Level.INFO);
        // Set log level of a specific logger
        logConfigurator.setLevel("org.apache", Level.INFO);
        logConfigurator.setFilePattern("%d - %p[%c] - %m%n");
        logConfigurator.setLogCatPattern("%m%n");
        logConfigurator.configure();
    }

    private void initCrashHandler(){
        CrashHandler.getInstance().init(getApplicationContext());
    }
}
