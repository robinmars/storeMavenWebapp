package com.weixin.model;

import java.io.Serializable;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：文件
 * 类描述：文件实体
 * 创建人：chenxiaoyi
 * 创建时间：2016-12-08 10:21:59
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2016-12-08 10:21:59---(新建)
 *
 */ 

public class AdminInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long sn;    //主键，自增
	
	private String adminId;	//账号
	
	private String adminName;    //用户名
	
	private String adminPwd;    //密码
	
	private String validateCode; //验证码
	
	private int status;    //状态 0:关闭 1 开启
	
	private String insertTime;	//插入时间
	
	private String updateTime;	//更新时间

	private String roleName; //所属的角色名
	
	private String rememberPwd;	//记住密码

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public long getSn() {
		return sn;
	}

	public void setSn(long sn) {
		this.sn = sn;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminPwd() {
		return adminPwd;
	}

	public void setAdminPwd(String adminPwd) {
		this.adminPwd = adminPwd;
	}

	public String getValidateCode() {
		return validateCode;
	}

	public void setValidateCode(String validateCode) {
		this.validateCode = validateCode;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getRememberPwd() {
		return rememberPwd;
	}

	public void setRememberPwd(String rememberPwd) {
		this.rememberPwd = rememberPwd;
	}
	
}

