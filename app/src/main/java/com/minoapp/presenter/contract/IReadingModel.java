package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;

import io.reactivex.Observable;
import retrofit2.http.Path;

/**
 * Created by Devin on 2017/7/28.
 */

public interface IReadingModel {
    /**
     * 获取当前房间下的某个时间段的热分配计读数
     * @param localityId 房间号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageIndex 当前页码
     * @param pageSize 每页数量
     * @return
     */
    Observable<BaseResponse<PageBean<HCAReading>>> getHCAReadings(int localityId,String startDate, String endDate, int pageIndex, int pageSize);

    Observable<BaseResponse<PageBean<BuildMeterReadingBean>>> getBuildMeterReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize);

    Observable<BaseResponse<PageBean<ReadingBean>>> getTempReadings(int meterId, String startDate, String endDate, int pageIndex, int pageSize);

}
