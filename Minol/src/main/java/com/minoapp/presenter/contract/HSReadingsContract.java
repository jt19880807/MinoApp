package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.base.BaseView;
import com.minoapp.data.bean.HSMeterReadingBean;
import com.minoapp.data.bean.PageBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Path;

/**
 * Created by Devin on 2017/9/8.
 */

public interface HSReadingsContract {
    interface HSReadingsView extends BaseView {
        void showReadings(PageBean<HSMeterReadingBean> hsMeterReadingBeen);
        void showLastReadings(PageBean<HSMeterReadingBean> hsMeterReadingBeen);
        void onLoadMoreComplete();
    }
    interface  IHSReadingModel{
        Observable<BaseResponse<PageBean<HSMeterReadingBean>>> getHSReadings(int meterId,String startDate,
                                                                             String endDate, int pageIndex,
                                                                             int pageSize, int meterType);
        Observable<BaseResponse<PageBean<HSMeterReadingBean>>> getHSLastReadings(int meterId,  int meterType);
    }
}
