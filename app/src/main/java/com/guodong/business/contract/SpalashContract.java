package com.guodong.business.contract;


import com.guodong.business.bean.PictureInfo;
import com.guodong.mvp.BaseContract;

import java.util.List;

import io.reactivex.Observable;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */

public interface SpalashContract {
    interface ISpalashView extends BaseContract.IBaseView{
        void setImages(List<PictureInfo> images);
    }
    interface  ISpalashPresenter extends BaseContract.IBasePresennter{
        void getImages();
    }
    interface ISpalashModel  extends BaseContract.IBaseModel{
//        Observable<User> Login(String userName, String pwd);
        Observable<List<PictureInfo>> getImages();
    }
}
