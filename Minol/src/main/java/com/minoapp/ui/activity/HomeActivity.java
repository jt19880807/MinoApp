package com.minoapp.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.minoapp.R;
import com.minoapp.adapter.MainGridAdapter;
import com.minoapp.base.BaseActivity;
import com.minoapp.common.Constant;
import com.minoapp.common.utils.ACache;
import com.minoapp.data.bean.MenuBean;
import com.minoapp.data.bean.RightBean;
import com.minoapp.data.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.grid_home)
    GridView gridHome;

    List<MenuBean> menuBeanList=new ArrayList<>();

    @Override
    protected String getTAG() {
        return HomeActivity.class.getSimpleName();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initGridView();

    }

    private void initGridView() {
        List<RightBean> rightBeanList=new ArrayList<>();
//        ACache aCache=ACache.get(this);
//        UserBean userBean=(UserBean)aCache.getAsObject(Constant.USER);
//        if(userBean!=null){
//            rightBeanList=userBean.getParentRights();
//
//            for (RightBean bean :rightBeanList) {
//                if (bean.getRightName().equals("热计量")){
//                    menuBeanList.add(new MenuBean("热计量",R.mipmap.icon_rejl));
//                }
//                else if(bean.getRightName().equals("换热站")){
//                    menuBeanList.add(new MenuBean("换热站",R.mipmap.icon_heatranser));
//                }
//                else if(bean.getRightName().equals("煤改电")){
//                    menuBeanList.add(new MenuBean("煤改电",R.mipmap.icon_meigaidian));
//                }
//            }
//
//        }

        if(!"住户".equals(userBean.getRoleName())){
            menuBeanList.add(new MenuBean("热计量",R.mipmap.icon_rejl));
            menuBeanList.add(new MenuBean("换热站",R.mipmap.icon_heatranser));
            menuBeanList.add(new MenuBean("煤改电",R.mipmap.icon_meigaidian));
        }
        MainGridAdapter adapter=new MainGridAdapter(menuBeanList,this);
        gridHome.setAdapter(adapter);
        gridHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String menuTitle=menuBeanList.get(position).getText();
                if (menuTitle.equals("热计量")){
                    openActivity(CustomerActivity.class);

                }
                else if(menuTitle.equals("换热站")){
                    //Toast.makeText(mContext, "换热站正在开发中...", Toast.LENGTH_SHORT).show();
                    openActivity(HeatStationsActivity.class);
                }
                else if(menuTitle.equals("煤改电")){
                    Toast.makeText(mContext, "煤改电正在开发中...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String msg) {

    }

    @Override
    public void dismissLoading() {

    }
}
