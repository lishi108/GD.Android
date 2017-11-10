package com.guodong.utils.glide;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.Target;
import com.guodong.R;

import java.io.File;

/**Glide图片加载简单封装类，用于快捷加载图片
 * 包括：图片路径的加载显示，gif文件的显示，以及图片文件、资源文件等的显示
 * Created by LSQ108 on 2017/5/11.
 */
public class GlideManager {
    private static Handler handler ;
    public final static int CACHE_FILE_MSG_WHAT = 100;
    /**
     * 加载普通图片的显示
     * @param context   上下文
     * @param url  图片地址
     * @param erroImg  加载失败的图片
     * @param emptyImg  占位图片，用于加载之前显示
     * @param iv
     */
    public static void loadImage(Context context, String url, int erroImg, int emptyImg, ImageView iv) {
        //原生 API
        Glide.with(context)
                .load(url)   //加载的图片路径
                .crossFade()    //设置显示动画，
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
        //        .crossFade(int duration):设置显示动画的时间
        //        .crossFade(Animation animation, int duration):设置自定义的动画和时间
        //        .crossFade(int animationId, int duration): 加载动画资源和时间
                .placeholder(emptyImg)   //设置占位图，在加载之前显示
                .error(erroImg)  //在图像加载失败时显示
                .into(iv);  //加载图片的imageview控件
    }

    public static void loadImage(Context context, String url, final ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }


    /**
     * 加载本地资源文件的图片
     * @param context
     * @param resourceId   资源文件ID
     * @param imageView
     */
    public static void loadImage(Context context, final int resourceId, final ImageView imageView) {
        Glide.with(context)
                .load(resourceId)
                .into(imageView);
    }

    /**
     * 动态的GIF图片加载：
     * @param context
     * @param url
     * @param iv
     */
    public static void loadGifImage(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)  //gif动画的路径
                .asGif()   //显示gif格式
//                .asBitmap() //显示gif静态图片
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
//        DiskCacheStrategy.NONE: 不使用硬盘缓存
//        DiskCacheStrategy.SOURCE: 将原始图像缓存在硬盘中
//        DiskCacheStrategy.RESULT: 将显示出来大小的图像缓存在硬盘(默认缓存策略)
//        DiskCacheStrategy.ALL: 显示的图像和原始图像都会缓存//不使用硬盘缓存
//                .skipMemoryCache(true)   //禁止使用内存缓存，false使用内存缓存
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(iv);
    }

    /**
     * 加载图片，显示成圆形图片
     * @param context
     * @param url  图片地址
     * @param iv  图片控件
     */
    public static void loadCircleImage(Context context, String url, ImageView iv) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new GlideCircleTransform(context))
                .into(iv);
    }

    /**
     * 加载图片，处理图片成圆角显示
     * @param context
     * @param url   图片地址
     * @param radius  圆角大小(dp)
     * @param iv  图片控件
     */
    public static void loadRoundCornerImage(Context context, String url, int radius, ImageView iv) {
        Glide.with(context)
                .load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .transform(new GlideRoundTransform(context,radius))
                .into(iv);
    }

    /**
     * 加载图片，源来自图片文件
     * @param context
     * @param file  图片文件
     * @param imageView
     */
    public static void loadFileImage(Context context, final File file, final ImageView imageView) {
        Glide.with(context)
                .load(file)
                .into(imageView);
    }

    public static void getCacheImage(Context context, Handler handler, String url) {
        new getImageCacheAsyncTask(context,handler).execute(url);
    }
    private static class getImageCacheAsyncTask extends AsyncTask<String, Void, File> {
        private final Context context;
        private Handler handler;
        public String filePath = "";

        public getImageCacheAsyncTask(Context context, Handler handler) {
            this.context = context;
            this.handler = handler;
        }

        @Override
        protected File doInBackground(String... params) {
            String imgUrl =  params[0];
            try {
                return Glide.with(context)
                        .load(imgUrl)
                        .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                        .get();
            } catch (Exception ex) {
                return null;
            }
        }

        @Override
        protected void onPostExecute(File result) {
            if (result == null) {
                return;
            }
            //此path就是对应文件的缓存路径
            filePath = result.getPath();
            Message msg = new Message();
            msg.obj = filePath;
            msg.what = CACHE_FILE_MSG_WHAT;
            handler.sendMessage(msg);
            Log.e("path", filePath);
        }
    }
}
