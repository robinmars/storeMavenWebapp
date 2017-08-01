package com.weixin.model.query;

import com.commons.mybatis.BasePage;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 下午5:40:38
 * @description 资源的扩展类
 */
public class ResourceQueryForm extends BasePage {

	
	/**
	 * serialVersionUID:TODO（用一句话描述这个变量表示什么）
	 *
	 * @since V.1.0.0.T.1
	 */
	
	private static final long serialVersionUID = 1L;

	private Integer sn;//资源的主键sn
	
	private String name;//资源的名字
	
	private String url;//资源的URL
	
	private String description;//资源的描述

	private Integer psn;//父节点

	public Integer getSn() {
		return sn;
	}

	public void setSn(Integer sn) {
		this.sn = sn;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPsn() {
		return psn;
	}

	public void setPsn(Integer psn) {
		this.psn = psn;
	}
}
