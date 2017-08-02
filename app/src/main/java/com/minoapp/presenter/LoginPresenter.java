package com.minoapp.presenter;



import com.minoapp.base.BasePresenter;
import com.minoapp.common.Constant;
import com.minoapp.common.rx.RxHttpReponseCompat;
import com.minoapp.common.rx.subscriber.ProgressSubcriber;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.UserBean;
import com.minoapp.presenter.contract.LoginContract;

import io.reactivex.annotations.NonNull;

/**
 * Created by Devin on 2017/6/14.
 */

public class LoginPresenter extends BasePresenter<LoginContract.ILoginModel,LoginContract.LoginView> {
    public LoginPresenter(LoginContract.ILoginModel iLoginModel, LoginContract.LoginView loginView) {
        super(iLoginModel, loginView);
    }

    public void Login(String name,String pwd){

        model.getUserInfo(name,pwd).compose(RxHttpReponseCompat.<UserBean>compatResult())
                .subscribe(new ProgressSubcriber<UserBean>(context,view) {

                    @Override
                    public void onNext(@NonNull UserBean userBean) {
                        //TODO save user
                        SaveUser(userBean);
                        view.LoginSuccess();

                    }
                });

    }

    private void SaveUser(UserBean userBean) {
        ACache aCache=ACache.get(context);
        aCache.put(Constant.USER,userBean);
    }
}
