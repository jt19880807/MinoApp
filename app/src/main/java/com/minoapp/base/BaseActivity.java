package com.minoapp.base;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import com.minoapp.R;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.UserBean;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Devin on 2017/6/9.
 */
//<T extends BasePresenter>
public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected String TAG=getTAG();
    protected abstract String getTAG();
    protected abstract int getLayoutId();
    protected Context mContext;
    //是否登陆
    public boolean isLogin=false;
    //授权
    public String mAuthorization;

    private Unbinder unbinder;
    protected UserBean userBean;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ACache aCache=ACache.get(this);

        userBean=(UserBean)aCache.getAsObject(Constant.USER);
        setContentView(getLayoutId());
        unbinder=ButterKnife.bind(this);
        mContext=this;
    }
    //是否statusBar 状态栏为透明 的方法 默认为真
    protected boolean isTranslucentStatusBar() {
        return true;
    }
    protected void openActivity(Class<?> cls){
        openActivity(cls,null);
    }

    protected void openActivity(Class<?> cls,Bundle bundle){
        Intent intent=new Intent(this,cls);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
    }

    protected void openActivityWithOutAnim(Class<?> cls){
        openActivityWithOutAnim(cls,null);
    }

    protected void openActivityWithOutAnim(Class<?> cls,Bundle bundle){
        Intent intent=new Intent(this,cls);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    protected void openActivity(String action){
        openActivity(action,null);
    }

    protected void openActivity(String action,Bundle bundle){
        Intent intent=new Intent(action);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        overridePendingTransition(R.anim.push_left_in,R.anim.push_left_out);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(unbinder!=null){
            unbinder.unbind();
        }
    }
}
