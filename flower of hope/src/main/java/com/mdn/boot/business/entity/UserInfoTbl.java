package com.danfan.boot.business.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name="user_info_tbl")
public class UserInfoTbl implements Serializable {
    
	private static final long serialVersionUID = -480987622759216893L;

	/** 用户id */
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="user_id")
    private Long userId;
    
    /** 用户名 */
    @Column(name="user_name")
    private String userName;
    
    /** 密码 */
    @Column(name="pass_word")
    private String passWord;
    
    /** 盐 */
    @Column(name="salt")
    private String salt;
    
    /** 头像 */
    @Column(name="img")
    private String img;
    
    /** 最近一次登入时间 */
    @Column(name="recent_time")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recentTime;
    
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

    /** 用户id */
    public Long getUserId(){
        return this.userId;
    }
    /** 用户id */
    public void setUserId(Long userId){
        this.userId = userId;
    }
    /** 用户名 */
    public String getUserName(){
        return this.userName;
    }
    /** 用户名 */
    public void setUserName(String userName){
        this.userName = userName;
    }
    /** 密码 */
    public String getPassWord(){
        return this.passWord;
    }
    /** 密码 */
    public void setPassWord(String passWord){
        this.passWord = passWord;
    }
    /** 盐 */
    public String getSalt(){
        return this.salt;
    }
    /** 盐 */
    public void setSalt(String salt){
        this.salt = salt;
    }
    /** 头像 */
    public String getImg(){
        return this.img;
    }
    /** 头像 */
    public void setImg(String img){
        this.img = img;
    }
    /** 最近一次登入时间 */
    public LocalDateTime getRecentTime(){
        return this.recentTime;
    }
    /** 最近一次登入时间 */
    public void setRecentTime(LocalDateTime recentTime){
        this.recentTime = recentTime;
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
		return "UserInfoTbl [userId=" + userId + ", userName=" + userName + ", passWord=" + passWord + ", salt=" + salt
				+ ", img=" + img + ", recentTime=" + recentTime + ", addTime=" + addTime + ", updateTime=" + updateTime
				+ ", deleteFlag=" + deleteFlag + "]";
	}
}