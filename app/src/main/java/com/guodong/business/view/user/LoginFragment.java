package com.guodong.business.view.user;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.guodong.R;
import com.guodong.business.contract.LoginContract;
import com.guodong.business.presenter.user.LoginPresenter;
import com.guodong.business.view.MainActivity;
import com.guodong.mvp.BaseTitleFragment;
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

public class LoginFragment extends BaseTitleFragment<LoginPresenter> implements LoginContract.ILoginView {
    @BindView(R.id.userNameEdit)
    EditText userNameEdit;
    @BindView(R.id.pwdEdit)
    EditText pwdEdit;
    private Tencent mTencent;
    private final int QQ_REQUEST_CODE = 111;
    private final int WEICHART_REQUEST_CODE = 112;

    @Override
    protected LoginPresenter loadPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
        mActivity.getOther().setVisibility(View.VISIBLE);
        mActivity.getOther().setText(R.string.register);
        mActivity.getOther().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new RegisterFragment());
            }
        });
        mActivity.setTitle(R.string.NullText);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
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
        addFragment(new PhoneLoginFragment());
    }

    @OnClick(R.id.loginButton)
    void onLoginButton(View view) {
        mActivity.startActivity(MainActivity.class);
        ((LoginActivity)mActivity).removeFragment();
        int count =  mActivity.getSupportFragmentManager().getBackStackEntryCount();
            Logger.e("count is  null"+count);

        ((LoginActivity)mActivity).finish();

    }

    @OnClick(R.id.login_select_wechart)
    void onWeiChartView(View view) {

    }

    @OnClick(R.id.login_select_qq)
    void onQQView(View view) {
        mTencent = Tencent.createInstance("123123123", getContext());//将123123123改为自己的AppID

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

    @Override
    public void onResume() {
        super.onResume();
        Logger.e("LoginFragment is onResume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Logger.e("LoginFragment is onDestroyView");
    }

    private class QQLoginListener implements IUiListener {


//这个类需要实现三个方法 onComplete（）：登录成功需要做的操作写在这里  
// onError onCancel 方法具体内容自己搜索  

        public void onComplete(Object response) {
            // TODO Auto-generated method stub  
            ToastUtil.showToast(getContext(), "登录成功");
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
            UserInfo info = new UserInfo(getContext(), qqToken);

            //    info.getUserInfo(new BaseUIListener(this,"get_simple_userinfo"));
            info.getUserInfo(new IUiListener() {
                @Override
                public void onComplete(Object o) {
                    //用户信息获取到了
                    try {
                        Logger.e("用户名", ((JSONObject) o).getString("nickname"));
                        Logger.e("用户姓名", ((JSONObject) o).getString("gender"));
                        Logger.e("UserInfo",o.toString());
                        Intent intent1 = new Intent(mActivity,MainActivity.class);
                        startActivity(intent1);
                    } catch (JSONException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(UiError uiError) {
                    Logger.e("UserInfo","onError");
                    ToastUtil.showToast(getContext(),uiError.errorMessage);
                }

                @Override
                public void onCancel() {
                    Logger.e("UserInfo","onCancel");
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
