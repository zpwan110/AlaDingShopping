package com.alading.shopping.common.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import com.alading.shopping.R;
import com.nostra13.universalimageloader.cache.memory.impl.LimitedAgeMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

import java.io.File;

/**
 * Created by Administrator on 2015/8/26.

 * 使用Picasso库加载网络图片和本地图片
 *
 * @author Alex
 *
 */
public class LoaderImage {
    /**
     *内存缓存
     使用强引用和弱引用相结合的缓存有
     UsingFreqLimitedMemoryCache（如果缓存的图片总量超过限定值，先删除使用频率最小的bitmap）
     LRULimitedMemoryCache（这个也是使用的lru算法，和LruMemoryCache不同的是，他缓存的是bitmap的弱引用）
     FIFOLimitedMemoryCache（先进先出的缓存策略，当超过设定值，先删除最先加入缓存的bitmap）
     LargestLimitedMemoryCache(当超过缓存限定值，先删除最大的bitmap对象)
     LimitedAgeMemoryCache（当 bitmap加入缓存中的时间超过我们设定的值，将其删除）
     **/
    /**
     * 磁盘缓存
     * FileCountLimitedDiscCache（可以设定缓存图片的个数，当超过设定值，删除掉最先加入到硬盘的文件）
     LimitedAgeDiscCache（设定文件存活的最长时间，当超过这个值，就删除该文件）
     TotalSizeLimitedDiscCache（设定缓存bitmap的最大值，当超过这个值，删除最先加入到硬盘的文件）
     UnlimitedDiscCache（这个缓存类没有任何的限制）
     LruMemoryCache（这个类就是这个开源框架默认的内存缓存类，缓存的是bitmap的强引用，下面我会从源码上面分析这个类)
     */
//    private final LimitedAgeMemoryCache timeMenoryCache =new LimitedAgeMemoryCache();
        private static boolean isInited = false;
        static final String FILE = "file:///";
        static final String DRAWABLE = "drawable://";
        //磁盘缓存
        static DisplayImageOptions diskCacheOption = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.choiceness)
                .showImageOnFail(R.drawable.choiceness).cacheOnDisk(true)
                .displayer(new FadeInBitmapDisplayer(200))
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    //内存缓存

        static DisplayImageOptions memoryCacheOption = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.choiceness)
                .showImageOnFail(R.drawable.choiceness)
                .showImageOnLoading(R.drawable.choiceness).cacheInMemory(true)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(200))
                .bitmapConfig(Bitmap.Config.RGB_565).build();
        // 带圆角的15度
        static DisplayImageOptions photoRoundOption = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.choiceness)
                .showImageOnFail(R.drawable.choiceness)
                .showImageOnLoading(R.drawable.choiceness).cacheInMemory(true)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(200))
                .displayer(new RoundedBitmapDisplayer(15))
                .bitmapConfig(Bitmap.Config.RGB_565).build();

    //默认头像
        static DisplayImageOptions headerOption = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.default_head)
                .showImageOnFail(R.drawable.default_head)
                .showStubImage(R.drawable.default_head).cacheInMemory(true)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(200))
                .bitmapConfig(Bitmap.Config.RGB_565).build();
    //加载本地
        static DisplayImageOptions localPhoto = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisk(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(200))
                .bitmapConfig(Bitmap.Config.RGB_565).build();


        //圆角图标30度
        static DisplayImageOptions filletPhoto = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.choiceness)
                .showImageOnFail(R.drawable.choiceness)
                .showImageOnLoading(R.drawable.choiceness).cacheInMemory(true)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(200))
                .displayer(new RoundedBitmapDisplayer(30))
                .bitmapConfig(Bitmap.Config.RGB_565).build();
//圆形头像
        static DisplayImageOptions photoHeadOption = new DisplayImageOptions.Builder()
                .showImageForEmptyUri(R.drawable.choiceness)
                .showImageOnFail(R.drawable.choiceness)
                .showImageOnLoading(R.drawable.choiceness).cacheInMemory(true)
                .cacheOnDisk(true).imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(200))
                .displayer(new RoundedBitmapDisplayer(150))
                .bitmapConfig(Bitmap.Config.RGB_565).build();


        /**
         * 加载本地图片没有默认图
         *
         * @param resId
         * @param view
         */
        public static void loadDrawablePhoto( int resId, ImageView view) {
            String uri = DRAWABLE + resId;
            ImageLoader.getInstance().displayImage(uri, view, localPhoto);
        }

        /**
         * 加载本地图片文件
         *
         * @param file
         * @param view
         */
        public static void loadFilePhoto( File file, ImageView view) {
            String uri = FILE + file.getAbsolutePath();
            ImageLoader.getInstance().displayImage(uri, view, localPhoto);
        }
        public static void loadHeadPhoto(Context context, File file, ImageView view) {
            String uri = FILE + file.getAbsolutePath();
            ImageLoader.getInstance().displayImage(uri, view, photoHeadOption);
        }

        /**
         * 加载本地图片（带监听）
         *
         * @param file
         * @param view
         */
        public static void loadFilePhoto(File file, ImageView view,
                                     ImageLoadingListener listener) {
            String uri = FILE + file.getAbsolutePath();
            ImageLoader.getInstance().displayImage(uri, view, diskCacheOption,
                    listener);
        }

        /**
         * 加载网路图片
         *
         * @param url
         * @param view
         */
        public static void loadPhoto( String url, ImageView view) {
            ImageLoader.getInstance().displayImage(url, view, memoryCacheOption);
        }

    /**
     * 带圆角的15度
     * @param url
     * @param view
     */
        public static void loadRoundPhoto( String url,
                                          ImageView view) {
            ImageLoader.getInstance().displayImage(url, view, photoRoundOption);
        }

    /**
     * 用于显示头像
     * @param url
     * @param view
     */
        public static void loadHeadPhoto( String url,
                                         ImageView view) {
            ImageLoader.getInstance().displayImage(url, view, photoHeadOption);
        }

    /**
     * 圆角图标30度
     * @param url
     * @param view
     */
        public static void loadGameRoundPhoto( String url,
                                              ImageView view) {
            ImageLoader.getInstance().displayImage(url, view, filletPhoto);
        }

        public static void loadPhoto(Context context, String url, ImageView view,
                                     ImageLoadingListener listener) {
            ImageLoader.getInstance().displayImage(url, view, memoryCacheOption);
        }

        public static void removeCacheWithKey(String key) {
            ImageLoader.getInstance().getDiskCache().remove(key);
            ImageLoader.getInstance().getMemoryCache().remove(key);
        }

}
