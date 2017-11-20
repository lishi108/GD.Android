package com.guodong.business.presenter.user;


import com.guodong.business.contract.CodeInputContract;
import com.guodong.business.model.user.CodeInputModel;
import com.guodong.mvp.BasePresenter;

/**
 * Description:
 * Created by Administrator on 2017/10/31.
 */
public class CodeInputPresenter extends BasePresenter<CodeInputContract.ICodeInputView,CodeInputContract.ICodeInputModel> implements CodeInputContract.ICodeInputPresenter{


    @Override
    public CodeInputContract.ICodeInputModel loadModel() {
        return new CodeInputModel();
    }

}

