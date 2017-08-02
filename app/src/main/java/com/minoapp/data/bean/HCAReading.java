package com.minoapp.data.bean;

import java.util.List;

/**
 * Created by Devin on 2017/7/28.
 */

public class HCAReading {


    /**
     * Date : 2017-04-01
     * Data : [{"Id":"2150806f-a6b7-4814-b486-cd090355f46c","Date":"2017-04-01T00:00:00","Value":131,"IsDeleted":false,"Origin":12,"MeterId":94038},{"Id":"48189e27-15bd-4edf-adbd-e60731610517","Date":"2017-04-01T00:00:00","Value":128.25,"IsDeleted":false,"Origin":12,"MeterId":94039},{"Id":"f69650ba-8472-42fb-a860-bbb8aca9641b","Date":"2017-04-01T00:00:00","Value":121.5,"IsDeleted":false,"Origin":12,"MeterId":94040},{"Id":"04080f1d-2462-4cd3-b23f-ff08a259ae39","Date":"2017-04-01T00:00:00","Value":131.5,"IsDeleted":false,"Origin":12,"MeterId":94041},{"Id":"1ba97bfa-15b6-4be2-aeb0-c30973cbb8f6","Date":"2017-04-01T00:00:00","Value":127.75,"IsDeleted":false,"Origin":12,"MeterId":94042},{"Id":"b1ae0cbb-6571-4966-85d6-29157f6e5f31","Date":"2017-04-01T00:00:00","Value":56.25,"IsDeleted":false,"Origin":12,"MeterId":94043},{"Id":"53e58cb3-169f-4ab1-be18-7c29d5c63825","Date":"2017-04-01T00:00:00","Value":4.25,"IsDeleted":false,"Origin":12,"MeterId":94044}]
     */

    private String Date;
    private List<ReadingBean> Data;

    public String getDate() {
        return Date;
    }

    public void setDate(String Date) {
        this.Date = Date;
    }

    public List<ReadingBean> getData() {
        return Data;
    }

    public void setData(List<ReadingBean> Data) {
        this.Data = Data;
    }


}
