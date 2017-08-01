package com.weixin.model;

import java.io.Serializable;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 上午11:45:46
 * @description 用户角色的关系实体
 */
public class AdminRole implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long sn;//主键sn
	
	private Long AdminSn;//用户sn
	
	private Long RoleSn;//角色sn

	public AdminRole() {
	}

	public AdminRole(Long adminSn, Long roleSn) {
		AdminSn = adminSn;
		RoleSn = roleSn;
	}

	public Long getId() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public Long getAdminSn() {
		return AdminSn;
	}

	public void setAdminId(Long adminSn) {
		AdminSn = adminSn;
	}

	public Long getRoleSn() {
		return RoleSn;
	}

	public void setRoleId(Long roleSn) {
		RoleSn = roleSn;
	}

	
	
	
}
