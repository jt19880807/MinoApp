package com.minoapp.base;



import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.minoapp.R;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.UserBean;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Devin on 2017/7/14.
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    private Unbinder mUnbinder;
    private View mRootView;
    private UserBean userBean;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(getLayoutId(), container, false);
        mUnbinder=  ButterKnife.bind(this, mRootView);
        ACache aCache=ACache.get(getActivity());
        userBean=(UserBean)aCache.getAsObject(Constant.USER);
        return mRootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mUnbinder != Unbinder.EMPTY){
            mUnbinder.unbind();
        }
    }

    protected abstract int getLayoutId();

    protected void openActivity(Class<?> cls){
        openActivity(cls,null);
    }

    protected void openActivity(Class<?> cls,Bundle bundle){
        Intent intent=new Intent(getActivity(),cls);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);

    }
}
