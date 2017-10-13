package com.minoapp.api;


import com.minoapp.base.BaseResponse;
import com.minoapp.data.bean.AreaBean;
import com.minoapp.data.bean.BillingInfoBean;
import com.minoapp.data.bean.BuildMeterReadingBean;
import com.minoapp.data.bean.ChartDataBean;
import com.minoapp.data.bean.Customer;
import com.minoapp.data.bean.CustomerBean;
import com.minoapp.data.bean.HCABean;
import com.minoapp.data.bean.HCAReading;
import com.minoapp.data.bean.HSMeterReadingBean;
import com.minoapp.data.bean.HeatMeterBean;
import com.minoapp.data.bean.HeatSeasonBean;
import com.minoapp.data.bean.HeatStation;
import com.minoapp.data.bean.HeatStationBean;
import com.minoapp.data.bean.IncidentBean;
import com.minoapp.data.bean.MeterBean;
import com.minoapp.data.bean.ObjectBean;
import com.minoapp.data.bean.PageBean;
import com.minoapp.data.bean.ReadingBean;
import com.minoapp.data.bean.ResidentBean;
import com.minoapp.data.bean.UserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Devin on 2017/6/21.
 */

public interface ApiService {
    //public static final String Base_URL="http://192.168.5.117:8011/api/";
    //public static final String Base_URL="http://192.168.1.107/api/";
    public static final String Base_URL="http://202.136.211.102:8181/api/";

    /**
     * 根据用户名和密码获取登录信息
     * @param name
     * @param password
     * @return
     */
    @GET("login/{name}/{pwd}")
    Observable<BaseResponse<UserBean>> getUserInfo(@Path("name") String name, @Path("pwd") String password);

    @GET("object/getcustomersbyobjectid/{objectIds}")
    Observable<BaseResponse<List<CustomerBean>>> getAllCustomers(@Path("objectIds") String objectIds);

    //根据用户ID获取热力公司
    @GET("object/getcustomersbyuserid/{userID}")
    Observable<BaseResponse<List<Customer>>> getAllCustomersByUserID(@Path("userID") String userID);
    //根据用户ID获取热力公司(不分组)
    @GET("object/getcustomersbeanbyuserid/{userID}")
    Observable<BaseResponse<List<CustomerBean>>> getAllCustomerBeanByUserID(@Path("userID") int userID);

    /**
     * 获取当前热力公司下的小区信息
     * @param customerId
     * @return
     */
    @GET("object/getareasbycustomerid/{customerId}")
    Observable<BaseResponse<List<AreaBean>>> getAreasByCustomerId(@Path("customerId") int customerId);

    /**
     * 获取当前热力公司下的小区信息
     * @param customerId
     * @return
     */
    @GET("object/getareadetailsbycustomerid/{customerId}")
    Observable<BaseResponse<List<AreaBean>>> getAreaDetailByCustomerId(@Path("customerId") int customerId);

    /**
     * 根据用户ID和热力公司ID获取当前热力公司下面的项目信息
     * @param userID 用户ID
     * @param customerID 热力公司ID
     * @param pageIndex 页码
     * @param pageSize 每页数据量
     * @return
     */
    @GET("object/getobjectsbyuseridandcustomerid/{userID}/{customerID}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<ObjectBean>>> getAllObjects(@Path("userID") int userID,
                                                                 @Path("customerID") int customerID,
                                                                 @Path("pageIndex") int pageIndex,
                                                                 @Path("pageSize") int pageSize);

    /**
     * 根据用户ID和热力公司ID获取当前小区下面的项目信息
     * @param userID
     * @param area
     * @return
     */
    @GET("object/getobjectsbyuseridandarea/{userID}/{area}")
    Observable<BaseResponse<List<ObjectBean>>> getAllObjectsByArea(@Path("userID") int userID,
                                                             @Path("area") String area);

    /**
     * 获取当前项目下的住户信息
     * @param objectID
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("resident/getresidentbyobjectid/{objectID}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<ResidentBean>>> getResidents(@Path("objectID") int objectID,
                                                                  @Path("pageIndex") int pageIndex,
                                                                  @Path("pageSize") int pageSize);
    /**
     * 获取指定的住户信息
     * @param localityId
     * @return
     */
    @GET("resident/getresidentbylocalityid/{localityId}")
    Observable<BaseResponse<List<ResidentBean>>> getResidents(@Path("localityId") int localityId);
    /**
     * 获取当前项目下的热量表信息
     * @param objectID
     * @return
     */
    @GET("meter/getbuildmetersbyobjectid/{objectID}")
    Observable<BaseResponse<List<HeatMeterBean>>> getHeatMetersByObjectId(@Path("objectID") int objectID);

    /**
     * 获取当前项目下的测温设备信息
     * @param objectID
     * @return
     */
    @GET("meter/gettempbyobjectid/{objectID}")
    Observable<BaseResponse<List<MeterBean>>> getTempByObjectId(@Path("objectID") int objectID);

    /**
     * 获取当前住户的测温设备信息
     * @param localityId
     * @return
     */
    @GET("meter/gettempbylocalityid/{localityId}")
    Observable<BaseResponse<List<MeterBean>>> getTempByLocalityId(@Path("localityId") int localityId);

    /**
     * 获取当前房间下的热分配计信息
     * @param localityId
     * @return
     */
    @GET("meter/gethcabylocalityid/{localityId}")
    Observable<BaseResponse<List<HCABean>>> getHCAByLocalityId(@Path("localityId") int localityId);

    /**
     * 获取当前房间下的某个时间段的热分配计读数
     * @param localityId 房间号
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageIndex 当前页码
     * @param pageSize 每页数量
     * @return
     */
    @GET("readings/gethcareadingsbylocalityld/{localityId}/{startDate}/{endDate}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<HCAReading>>> getHCAReadings(@Path("localityId") int localityId,
                                                                  @Path("startDate") String startDate,
                                                                  @Path("endDate") String endDate,
                                                                  @Path("pageIndex") int pageIndex,
                                                                  @Path("pageSize") int pageSize);

    /**
     * 获取当前房间下热分配计最新读数
     * @param localityId 房间号
     * @return
     */
    @GET("readings/gethcalastreading/{localityId}")
    Observable<BaseResponse<PageBean<HCAReading>>> getHCALastReadings(@Path("localityId") int localityId);

    /**
     * 获取当前时间段内当前热量表的读数
     * @param meterId
     * @param startDate
     * @param endDate
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("readings/getbuildmetereadingsbymeterid/{meterId}/{startDate}/{endDate}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<BuildMeterReadingBean>>> getBuildMeterReadings(@Path("meterId") int meterId,
                                                                                    @Path("startDate") String startDate,
                                                                                    @Path("endDate") String endDate,
                                                                                    @Path("pageIndex") int pageIndex,
                                                                                    @Path("pageSize") int pageSize);
    /**
     * 获取当前热量表的最新读数
     * @param meterId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("readings/getbuildmeterlastreading/{meterId}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<BuildMeterReadingBean>>> getBuildMeterLastReadings(@Path("meterId") int meterId,
                                                                                    @Path("pageIndex") int pageIndex,
                                                                                    @Path("pageSize") int pageSize);
    /**
     * 获取当前时间段内当前测温设备的读数
     * @param meterId
     * @param startDate
     * @param endDate
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("readings/gettempreadingsbymeterid/{meterId}/{startDate}/{endDate}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<ReadingBean>>> getTempReadings(@Path("meterId") int meterId,
                                                                    @Path("startDate") String startDate,
                                                                    @Path("endDate") String endDate,
                                                                    @Path("pageIndex") int pageIndex,
                                                                    @Path("pageSize") int pageSize);

    /**
     * 获取当前测温设备的最新读数
     * @param meterId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("readings/gettemplastreading/{meterId}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<ReadingBean>>> getTempLastReadings(@Path("meterId") int meterId,
                                                                    @Path("pageIndex") int pageIndex,
                                                                    @Path("pageSize") int pageSize);

    /**
     * 获取指定房间的供暖季信息
     * @param localityId
     * @return
     */
    @GET("billing/getbillingseasion/{localityId}")
    Observable<BaseResponse<List<HeatSeasonBean>>> getBillingSeason(@Path("localityId") int localityId);

    /**
     * 获取指定房间的供暖季信息
     * @param objectId
     * @return
     */
    @GET("billing/getbillingseasionbyobjectid/{objectId}")
    Observable<BaseResponse<List<HeatSeasonBean>>> getBillingSeasonByObjectId(@Path("objectId") int objectId);
    /**
     * 获取制定住户的指定供暖季的账单信息
     * @param localityId
     * @param date
     * @return
     */
    @GET("billing/getbillingbylocalityid/{localityId}/{date}")
    Observable<BaseResponse<List<BillingInfoBean>>> getBillingByLocalityId(@Path("localityId") int localityId,
                                                                     @Path("date") String date);

    /**
     * 获取指定的项目的所有住户的账单
     * @param objectId
     * @param date
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("billing/getbillingbyobjectid/{objectId}/{date}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<BillingInfoBean>>> getBillingByObjectId(@Path("objectId") int objectId,
                                                                           @Path("date") String date,
                                                                         @Path("pageIndex") int pageIndex,
                                                                         @Path("pageSize") int pageSize);

    /**
     * 获取当前项目下的报警信息
     * @param objectId
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GET("object/getincidentsbyobjectid/{objectId}/{pageIndex}/{pageSize}")
    Observable<BaseResponse<PageBean<IncidentBean>>> getIncidentsByObjectId(@Path("objectId") int objectId,
                                                                            @Path("pageIndex") int pageIndex,
                                                                            @Path("pageSize") int pageSize);


    //根据用户ID获取换热站(分组)
    @GET("heatstation/getheatstations/{userID}")
    Observable<BaseResponse<List<HeatStation>>> getAllHeatStationsByUserID(@Path("userID") int userID);

    //根据用户ID获取换热站（不分组）
    @GET("heatstation/getheatstationbeans/{userID}")
    Observable<BaseResponse<List<HeatStationBean>>> getAllHeatStationBeans(@Path("userID") int userID);

    //获取当前换热站的表计
    @GET("heatstation/getheatstationmeters/{id}/{type}")
    Observable<BaseResponse<List<HeatMeterBean>>> getHeatStationMeters(@Path("id") int id,@Path("type") int type);

    //获取换热站某个表计在某个时间段内的读数
    @GET("heatstation/getheatstationmetereadings/{meterId}/{startDate}/{endDate}/{pageIndex}/{pageSize}/{meterType}")
    Observable<BaseResponse<PageBean<HSMeterReadingBean>>> getHeatStationMeterReadings(@Path("meterId") int meterId,
                                                                                       @Path("startDate") String startDate,
                                                                                       @Path("endDate") String endDate,
                                                                                       @Path("pageIndex") int pageIndex,
                                                                                       @Path("pageSize") int pageSize,
                                                                                       @Path("meterType") int meterType);

    //获取换热站某个表计在最新的读数
    @GET("heatstation/getheatstationmetelastreadings/{meterId}/{meterType}")
    Observable<BaseResponse<PageBean<HSMeterReadingBean>>> getHeatStationMeterLastReadings(@Path("meterId") int meterId,
                                                                                           @Path("meterType") int meterType);


//    图表展示
    @GET("chartdata/getchartdatafortempdaytype/{meterIds}/{startDate}/{endDate}")
    Observable<BaseResponse<List<ChartDataBean>>> getChartData(@Path("meterIds") String meterIds,
                                                               @Path("startDate") String startDate,
                                                               @Path("endDate") String endDate);

}
