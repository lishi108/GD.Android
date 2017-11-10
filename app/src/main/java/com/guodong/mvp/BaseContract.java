package com.guodong.mvp;

import android.content.Context;

/**
 * Description:
 * Created by LSQ108 on 2017/10/24.
 */

public interface BaseContract {
    interface IBaseView{
        Context getContext();
    }
    interface IBasePresennter<V extends IBaseView>{
        /**
         * 绑定View
         * @param view
         */
        void attachView(V view);

        /**
         * 防止内存泄露，清除presenter与activity之间的绑定
         */
        void detachView();

        /**
         * 获取View
         * @return
         */
        IBaseView getView();
    }
    interface IBaseModel{}
}
