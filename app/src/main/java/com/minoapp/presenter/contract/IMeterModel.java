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
     * 根据项目ID获取楼栋大表
     * @param objectID
     * @return
     */
    Observable<BaseResponse<List<MeterBean>>> getTempByObjectId(int objectID);

    /**
     * 获取当前房间下的热分配计信息
     * @param localityId
     * @return
     */
    Observable<BaseResponse<List<HCABean>>> getHCAByLocalityId(int localityId);



}
