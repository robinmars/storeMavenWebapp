package com.weixin.model;

import java.io.Serializable;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 上午11:45:12
 * @description 角色资源关系实体
 */
public class RoleResource implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long sn;//主键sn
	
	private Long roleSn;//角色sn
	
	private Long resourceSn;//资源sn

	public RoleResource() {
	}

	public RoleResource(Long roleSn, Long resourceSn) {
		this.roleSn = roleSn;
		this.resourceSn = resourceSn;
	}

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public Long getRoleSn() {
		return roleSn;
	}

	public void setRoleId(Long roleSn) {
		this.roleSn = roleSn;
	}

	public Long getResourceSn() {
		return resourceSn;
	}

	public void setResourceId(Long resourceSn) {
		this.resourceSn = resourceSn;
	}

	
	
}
