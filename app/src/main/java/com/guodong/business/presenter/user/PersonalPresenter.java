package com.guodong.business.presenter.user;

import com.guodong.business.contract.PersonalContract;
import com.guodong.business.model.user.PersonalModel;
import com.guodong.mvp.BasePresenter;

/**
 * Description:
 * Created by Administrator on 2017/11/8.
 */

public class PersonalPresenter extends BasePresenter<PersonalContract.IPersonalView,PersonalContract.IPersonalModel> implements PersonalContract.IPersonalPresenter{

    @Override
    public PersonalContract.IPersonalModel loadModel() {
        return new PersonalModel();
    }
}
