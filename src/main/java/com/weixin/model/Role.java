package com.weixin.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 上午11:15:08
 * @description 角色的实体类
 */
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long sn;//主键sn
	
	private String name;//角色名
	
	//private Integer status;//状态，1为可用；0为不可用
	
	private String description;//描述
	
	private Date insertTime;//插入时间
	
	private Date updateTime;//修改时间

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null? null : name.trim();
	}

/*	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}*/

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null ? null : description.trim();
	}

	public Date getInsertTime() {
		return insertTime;
	}

	public void setInsertTime(Date insertTime) {
		this.insertTime = insertTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	
}
