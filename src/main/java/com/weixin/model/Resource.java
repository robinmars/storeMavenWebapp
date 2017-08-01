package com.weixin.model;

import java.io.Serializable;
import java.sql.Date;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 上午11:19:27
 * @description 资源的实体，实际上是资源和权限的结合，统称为资源
 */
public class Resource implements Serializable{

	private static final long serialVersionUID = 1L;

	private Long sn;//主键sn
	
	private String name;//资源的名字
	
	private String url;//资源的url
	
	private String description;//描述
	
	private Date insertTime;//资源的插入时间
	
	private Date updateTime;//资源的修改时间

	private int psn;//父节点

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
		this.name = name == null?null:name.trim();
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url == null?null:url.trim();
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null?null:description.trim();
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

	public int getPsn() {
		return psn;
	}

	public void setPsn(int psn) {
		this.psn = psn;
	}
}
