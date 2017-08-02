package com.minoapp.data.bean;

import com.chad.library.adapter.base.entity.SectionEntity;
import com.minoapp.data.model.ReadingModel;

/**
 * Created by Devin on 2017/7/27.
 */

public class HCAReadingSectionEntity extends SectionEntity<ReadingBean> {

    public String title;
    public ReadingBean readingBean;
    public HCAReadingSectionEntity(boolean isHeader, String header) {
        super(isHeader, header);
        this.title=header;
    }

    public HCAReadingSectionEntity(ReadingBean readingBean) {
        super(readingBean);
        this.readingBean=readingBean;
    }
}
