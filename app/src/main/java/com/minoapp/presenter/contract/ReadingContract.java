package com.minoapp.presenter.contract;

import com.minoapp.base.BaseView;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;

/**
 * Created by Devin on 2017/7/28.
 */

public interface ReadingContract {
    public interface ReadingView extends BaseView{
        void showHCAReadings(PageBean<HCAReading> pageBean);
        void showHCALastReadings(PageBean<HCAReading> pageBean);
        void showBReadings(PageBean<BuildMeterReadingBean> pageBean);
        void showBLastReadings(PageBean<BuildMeterReadingBean> pageBean);
        void showTempReading(PageBean<ReadingBean> pageBean);
        void showTempLastReading(PageBean<ReadingBean> pageBean);
        void onLoadMoreComplete();
    }
}
