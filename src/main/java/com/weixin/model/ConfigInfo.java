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

public class ConfigInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long sn;    //主键，自增
	
	private String key;	//参数key值
	
	private String value;    //参数对应的值
	
	private String operator;    //操作者
	
	private String insertTime;	//插入时间
	
	private String updateTime;	//更新时间
	
	private String remark ;   //备注

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

