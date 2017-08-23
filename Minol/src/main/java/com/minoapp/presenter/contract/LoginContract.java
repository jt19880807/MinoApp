package com.minoapp.presenter.contract;




import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.UserBean;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/4.
 */

public class LoginContract {
    public interface LoginView extends BaseView {
        void LoginFail();
        void LoginSuccess();
    }
    public interface ILoginModel{
        Observable<BaseResponse<UserBean>> getUserInfo(String name, String pwd);
    }
}
