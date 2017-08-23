package com.minoapp.data.bean;

/**
 * Created by Devin on 2017/8/10.
 */

public class IncidentBean {

    /**
     * Id : 104762
     * Description : 靛厂路6号院 10号楼5-401房间的热分配表1479截止2016年4月1日已1个月没有读数
     * IsDeleted : false
     * CreatedDate : 2016-07-01T18:10:26.563
     * CreatedBy :
     * IsVisibleToCustomer : false
     * StatusId : 1
     * MeterId : 94019
     * UpdatedBy :
     * UpdatedDate : 2016-07-01T18:10:26.563
     * DuplicateOfIncidentId : 0
     */

    private int Id;
    private String Description;
    private boolean IsDeleted;
    private String CreatedDate;
    private String CreatedBy;
    private boolean IsVisibleToCustomer;
    private int StatusId;
    private int MeterId;
    private String UpdatedBy;
    private String UpdatedDate;
    private int DuplicateOfIncidentId;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public boolean isIsDeleted() {
        return IsDeleted;
    }

    public void setIsDeleted(boolean IsDeleted) {
        this.IsDeleted = IsDeleted;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String CreatedDate) {
        this.CreatedDate = CreatedDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public boolean isIsVisibleToCustomer() {
        return IsVisibleToCustomer;
    }

    public void setIsVisibleToCustomer(boolean IsVisibleToCustomer) {
        this.IsVisibleToCustomer = IsVisibleToCustomer;
    }

    public int getStatusId() {
        return StatusId;
    }

    public void setStatusId(int StatusId) {
        this.StatusId = StatusId;
    }

    public int getMeterId() {
        return MeterId;
    }

    public void setMeterId(int MeterId) {
        this.MeterId = MeterId;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String UpdatedBy) {
        this.UpdatedBy = UpdatedBy;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String UpdatedDate) {
        this.UpdatedDate = UpdatedDate;
    }

    public int getDuplicateOfIncidentId() {
        return DuplicateOfIncidentId;
    }

    public void setDuplicateOfIncidentId(int DuplicateOfIncidentId) {
        this.DuplicateOfIncidentId = DuplicateOfIncidentId;
    }
}
