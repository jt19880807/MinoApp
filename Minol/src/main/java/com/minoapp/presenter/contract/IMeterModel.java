package com.minoapp.presenter.contract;

import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.model.MeterModel;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Devin on 2017/7/19.
 */

public interface IMeterModel {
    /**
     * 根据项目ID获取楼栋大表
     * @param objectID
     * @return
     */
    Observable<BaseResponse<List<HeatMeterBean>>> getBuildMetersByObjectId(int objectID);

    /**
     * 根据项目或者住户的获取楼栋大表
     * @param id
     * @param type 1:项目 2：住户
     * @return
     */
    Observable<BaseResponse<List<MeterBean>>> getTemp(int id, int type);



    /**
     * 获取当前房间下的热分配计信息
     * @param localityId
     * @return
     */
    Observable<BaseResponse<List<HCABean>>> getHCAByLocalityId(int localityId);

    /**
     * 获取换热站表计
     * @param hsid 换热站ID
     * @param type 表计类型
     * @return
     */
    Observable<BaseResponse<List<HeatMeterBean>>> getHeatStationMeters(int hsid,int type);



}
