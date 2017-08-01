package com.weixin.model.query;

import com.commons.mybatis.BasePage;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 下午5:09:55
 * @description 角色的包装类
 */
public class RoleQueryForm extends BasePage {

	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since V.1.0.0.T.1
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer sn;//主键sn
	
	private String name;//角色名
	
	//private Integer status;//状态，1可用，0不可用
	
	private String description;//描述

	public Integer getSn() {
		return sn;
	}

	public void setId(Integer sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null? null:name.trim();
	}

	/*public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}*/

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description == null? null:description.trim();
	}
	
	
	
}
