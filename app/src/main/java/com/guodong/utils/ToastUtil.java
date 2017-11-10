package com.guodong.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

/**Toast的封装类，解决重复出现Toast的BUG
 * 文件名  ToastUtil
 * 创建日期   2016/10/20
 * 版权声明  Created by LSQ108
 */
public class ToastUtil {
    /** Toast第一次显示的内容 */
    private static String oldMsg ;
    /** Toast对象 */
    private static Toast toast = null ;
    /** Toast第一次显示的时间 */
    private static long oneTime = 0 ;
    /** Toast第二次显示的时间 */
    private static long twoTime = 0 ;


    public static void showToast(Context context,int resourceId){
        showToast(context,context.getString(resourceId));
    }

    /**
     * 显示Toast，为了解决Toast重复出现问题
     * @param context
     * @param message
     */
    public static void showToast(Context context, String message){
        if(toast == null){
            toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
            toast.show() ;
            oneTime = System.currentTimeMillis() ;
        }else{
            twoTime = System.currentTimeMillis() ;
            if(message.equals(oldMsg)){
                if(twoTime - oneTime > Toast.LENGTH_SHORT){
                    toast.show() ;
                }
            }else{
                oldMsg = message ;
                toast.setText(message) ;
                toast.show() ;
            }
        }
        oneTime = twoTime ;
    }

    public static void showToastOnThread(final Activity context, final String str){
        if(Thread.currentThread().getName().equals("main")){
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
        }else {
            context.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
