package com.guodong.business.view.user;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.guodong.R;
import com.guodong.business.contract.LoginContract;
import com.guodong.business.presenter.user.LoginPresenter;
import com.guodong.business.view.MainActivity;
import com.guodong.mvp.AppManager;
import com.guodong.mvp.BaseTitleActivity;
import com.guodong.utils.ToastUtil;
import com.orhanobut.logger.Logger;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Description:
 * Created by Administrator on 2017/11/20.
 */

public class LoginActivity extends BaseTitleActivity<LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.userNameEdit)
    EditText userNameEdit;
    @BindView(R.id.pwdEdit)
    EditText pwdEdit;
    private Tencent mTencent;
    private final int QQ_REQUEST_CODE = 111;
    private final int WEICHART_REQUEST_CODE = 112;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int initTitle() {
        return R.string.NullText;
    }

    @Override
    protected void initData() {
        super.initData();
        getOther().setVisibility(View.VISIBLE);
        getOther().setText(R.string.register);
        getOther().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(RegisterActivity.class);
            }
        });
    }

    @Override
    public String getUserName() {
        return userNameEdit.getText().toString().trim();
    }

    @Override
    public String getPassword() {
        return pwdEdit.getText().toString().trim();
    }

    @OnClick(R.id.forgetPwdView)
    void onForgetPwd(View view) {

    }

    @OnClick(R.id.phoneLoginView)
    void onPhoneLogin(View view) {
       startActivity(PhoneLoginActivity.class);
    }

    @OnClick(R.id.loginButton)
    void onLoginButton(View view) {
        startActivity(MainActivity.class);
        AppManager.getAppManager().finishActivity(LoginActivity.class);

    }

    @OnClick(R.id.login_select_wechart)
    void onWeiChartView(View view) {

    }

    @OnClick(R.id.login_select_qq)
    void onQQView(View view) {
        mTencent = Tencent.createInstance("123123123", mContext);//将123123123改为自己的AppID

        //get_simple_userinfo
        mTencent.login(this, "all", new QQLoginListener());


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Tencent.onActivityResultData(requestCode, resultCode, data, new QQLoginListener());

        if (requestCode == Constants.REQUEST_API) {
            if (resultCode == Constants.REQUEST_LOGIN) {
                Tencent.handleResultData(data, new QQLoginListener());
            }
        }
    }

    private class QQLoginListener implements IUiListener {


//这个类需要实现三个方法 onComplete（）：登录成功需要做的操作写在这里
// onError onCancel 方法具体内容自己搜索

        public void onComplete(Object response) {
            // TODO Auto-generated method stub
            ToastUtil.showToast(mContext, "登录成功");
            try {
                //获得的数据是JSON格式的，获得你想获得的内容
                //如果你不知道你能获得什么，看一下下面的LOG
                Logger.e("----TAG--", "-------------" + response.toString());
                String openidString = ((JSONObject) response).getString("openid");
                mTencent.setOpenId(openidString);
//                saveUser("44", "text", "text", 1);
                mTencent.setAccessToken(((JSONObject) response).getString("access_token"), ((JSONObject) response).getString("expires_in"));


                Logger.e("TAG", "-------------" + openidString);
                //access_token= ((JSONObject) response).getString("access_token");
                //expires_in = ((JSONObject) response).getString("expires_in");
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            QQToken qqToken = mTencent.getQQToken();
            UserInfo info = new UserInfo(mContext, qqToken);

            //    info.getUserInfo(new BaseUIListener(this,"get_simple_userinfo"));
            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    //用户信息获取到了
                    try {
                        Logger.e("用户名", ((JSONObject) o).getString("nickname"));
                        Logger.e("用户姓名", ((JSONObject) o).getString("gender"));
                        Logger.e("UserInfo", o.toString());
                        startActivity(MainActivity.class);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(UiError uiError) {
                    Logger.e("UserInfo", "onError");
                    ToastUtil.showToast(mContext, uiError.errorMessage);
                }

                @Override
                public void onCancel() {
                    Logger.e("UserInfo", "onCancel");
                }
            });


        }

        @Override
        public void onError(UiError uiError) {

        }

        @Override
        public void onCancel() {

        }
    }
}
