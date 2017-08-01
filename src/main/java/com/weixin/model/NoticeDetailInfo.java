package com.weixin.model;

import java.io.Serializable;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：告警通知信息表
 * 类描述：告警通知信息表实体
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-17 18:30:22
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-17 18:30:22---(新建)
 *
 */ 

public class NoticeDetailInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private int sn;    //
	
	private String comment;  //通知类型
	
	private String content;    //告警内容
	
	private String serverId;    //预警信息来源的服务器编号
	
	private Long monitorSn;    //monitor_info表的sn
	
	private String insertTime;    //插入时间
	
	private String indexMd5;    //对唯一索引进行md5加密
	
	public int getSn() {
		return sn;
	}
	public void setSn(int sn) {
		this.sn = sn;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}
	
	public Long getMonitorSn() {
		return monitorSn;
	}
	public void setMonitorSn(Long monitorSn) {
		this.monitorSn = monitorSn;
	}
	
	public String getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(String insertTime) {
		this.insertTime = insertTime;
	}
	
	public String getIndexMd5() {
		return indexMd5;
	}
	public void setIndexMd5(String indexMd5) {
		this.indexMd5 = indexMd5;
	}
	
}

