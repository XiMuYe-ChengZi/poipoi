package com.danfan.boot.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="log_info_tbl")
public class LogInfoTbl implements Serializable {
    
	private static final long serialVersionUID = -4763525501444910836L;

	/** id */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="log_id")
    private Long logId;
    
    /** 方法名 */
    @Column(name="method_name")
    private String methodName;
    
    /** 参数 */
    @Column(name="parameter")
    private String parameter;
    
    /** 请求地址 */
    @Column(name="url")
    private String url;
    
    /** 日志信息 */
    @Column(name="log_information")
    private String logInformation;
    
    /** 方法追踪 */
    @Column(name="method_track")
    private String methodTrack;
    
    /** 级别 */
    @Column(name="level")
    private String level;
    
    /** uuid */
    @Column(name="uuid")
    private String uuid;
    
    /** user_id */
    @Column(name="user_id")
    private String userId;
    
    /** 耗时 */
    @Column(name="time_consuming")
    private Integer timeConsuming;
    
    /** 添加时间 */
    @Column(name="add_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime addTime;
    
    /** 修改时间 */
    @Column(name="update_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
    
    /** 伪删除;0：正常1：删除 */
    @Column(name="delete_flag")
    private Integer deleteFlag;

    /** id */
    public Long getLogId(){
        return this.logId;
    }
    /** id */
    public void setLogId(Long logId){
        this.logId = logId;
    }
    /** 方法名 */
    public String getMethodName(){
        return this.methodName;
    }
    /** 方法名 */
    public void setMethodName(String methodName){
        this.methodName = methodName;
    }
    /** 参数 */
    public String getParameter(){
        return this.parameter;
    }
    /** 参数 */
    public void setParameter(String parameter){
        this.parameter = parameter;
    }
    /** 请求地址 */
    public String getUrl(){
        return this.url;
    }
    /** 请求地址 */
    public void setUrl(String url){
        this.url = url;
    }
    /** 日志信息 */
    public String getLogInformation(){
        return this.logInformation;
    }
    /** 日志信息 */
    public void setLogInformation(String logInformation){
        this.logInformation = logInformation;
    }
    /** 方法追踪 */
    public String getMethodTrack(){
        return this.methodTrack;
    }
    /** 方法追踪 */
    public void setMethodTrack(String methodTrack){
        this.methodTrack = methodTrack;
    }
    /** 级别 */
    public String getLevel(){
        return this.level;
    }
    /** 级别 */
    public void setLevel(String level){
        this.level = level;
    }
    /** uuid */
    public String getUuid(){
        return this.uuid;
    }
    /** uuid */
    public void setUuid(String uuid){
        this.uuid = uuid;
    }
    /** user_id */
    public String getUserId(){
        return this.userId;
    }
    /** user_id */
    public void setUserId(String userId){
        this.userId = userId;
    }
    /** 耗时 */
    public Integer getTimeConsuming(){
        return this.timeConsuming;
    }
    /** 耗时 */
    public void setTimeConsuming(Integer timeConsuming){
        this.timeConsuming = timeConsuming;
    }
    /** 添加时间 */
    public LocalDateTime getAddTime(){
        return this.addTime;
    }
    /** 添加时间 */
    public void setAddTime(LocalDateTime addTime){
        this.addTime = addTime;
    }
    /** 修改时间 */
    public LocalDateTime getUpdateTime(){
        return this.updateTime;
    }
    /** 修改时间 */
    public void setUpdateTime(LocalDateTime updateTime){
        this.updateTime = updateTime;
    }
    /** 伪删除;0：正常1：删除 */
    public Integer getDeleteFlag(){
        return this.deleteFlag;
    }
    /** 伪删除;0：正常1：删除 */
    public void setDeleteFlag(Integer deleteFlag){
        this.deleteFlag = deleteFlag;
    }
	@Override
	public String toString() {
		return "LogInfoTbl [logId=" + logId + ", methodName=" + methodName + ", parameter=" + parameter + ", url=" + url
				+ ", logInformation=" + logInformation + ", methodTrack=" + methodTrack + ", level=" + level + ", uuid="
				+ uuid + ", userId=" + userId + ", timeConsuming=" + timeConsuming + ", addTime=" + addTime
				+ ", updateTime=" + updateTime + ", deleteFlag=" + deleteFlag + "]";
	}
}