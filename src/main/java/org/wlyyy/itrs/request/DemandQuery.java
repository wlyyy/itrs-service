package org.wlyyy.itrs.request;

import java.util.Date;

/**
 * 需求查询对象。
 */
public class DemandQuery {
    private Long id;
    private String demandNo;
    private Long publisherId;
    private String positionType;
    private String position;
    private Long departmentId;
    private String hrName;
    private Long totalStart;
    private Long totalEnd;
    private String workingPlace;
    private String degreeRequestStart;
    private String degreeRequestEnd;
    private Integer status;
    private String memo;
    private java.util.Date gmtCreateStart;
    private java.util.Date gmtCreateEnd;
    private java.util.Date gmtModifyStart;
    private java.util.Date gmtModifyEnd;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    public Long getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(Long publisherId) {
        this.publisherId = publisherId;
    }

    public String getPositionType() {
        return positionType;
    }

    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getHrName() {
        return hrName;
    }

    public void setHrName(String hrName) {
        this.hrName = hrName;
    }

    public Long getTotalStart() {
        return totalStart;
    }

    public void setTotalStart(Long totalStart) {
        this.totalStart = totalStart;
    }

    public Long getTotalEnd() {
        return totalEnd;
    }

    public void setTotalEnd(Long totalEnd) {
        this.totalEnd = totalEnd;
    }

    public String getWorkingPlace() {
        return workingPlace;
    }

    public void setWorkingPlace(String workingPlace) {
        this.workingPlace = workingPlace;
    }

    public String getDegreeRequestStart() {
        return degreeRequestStart;
    }

    public void setDegreeRequestStart(String degreeRequestStart) {
        this.degreeRequestStart = degreeRequestStart;
    }

    public String getDegreeRequestEnd() {
        return degreeRequestEnd;
    }

    public void setDegreeRequestEnd(String degreeRequestEnd) {
        this.degreeRequestEnd = degreeRequestEnd;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public Date getGmtCreateStart() {
        return gmtCreateStart;
    }

    public void setGmtCreateStart(Date gmtCreateStart) {
        this.gmtCreateStart = gmtCreateStart;
    }

    public Date getGmtCreateEnd() {
        return gmtCreateEnd;
    }

    public void setGmtCreateEnd(Date gmtCreateEnd) {
        this.gmtCreateEnd = gmtCreateEnd;
    }

    public Date getGmtModifyStart() {
        return gmtModifyStart;
    }

    public void setGmtModifyStart(Date gmtModifyStart) {
        this.gmtModifyStart = gmtModifyStart;
    }

    public Date getGmtModifyEnd() {
        return gmtModifyEnd;
    }

    public void setGmtModifyEnd(Date gmtModifyEnd) {
        this.gmtModifyEnd = gmtModifyEnd;
    }
}
