package com.weixin.model;

import java.io.Serializable;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：待监控服务器列表
 * 类描述：待监控服务器列表实体
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-17 18:39:32
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-17 18:39:32---(新建)
 *
 */ 

public class UserInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long sn;    //主键，自增
	
	private String userId;    //用户ID
	
	private String userPwd;    //密码
	
	private String userName;    //姓名
	
	private String mobile;    //手机号
	
	private String email;    //邮箱
	
	private String insertTime;    //插入时间
	
	private int status;    //0可用；1不可用
	
	
	
	public Long getSn() {
		return sn;
	}
	public void setSn(Long sn) {
		this.sn = sn;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
}

