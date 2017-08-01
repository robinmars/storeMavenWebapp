package com.weixin.model;

import java.io.Serializable;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：主页面实体
 * 类描述：主页面实体
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-17 18:39:32
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-17 18:39:32---(新建)
 *
 */ 

public class MainInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String noticeInfoDayNum;
	
	private String noticeInfoMonthNum;
	
	private String noticeDetailInfoDayNum;
	
	private String noticeDetailInfoMonthNum;

	public String getNoticeInfoDayNum() {
		return noticeInfoDayNum;
	}

	public void setNoticeInfoDayNum(String noticeInfoDayNum) {
		this.noticeInfoDayNum = noticeInfoDayNum;
	}

	public String getNoticeInfoMonthNum() {
		return noticeInfoMonthNum;
	}

	public void setNoticeInfoMonthNum(String noticeInfoMonthNum) {
		this.noticeInfoMonthNum = noticeInfoMonthNum;
	}

	public String getNoticeDetailInfoDayNum() {
		return noticeDetailInfoDayNum;
	}

	public void setNoticeDetailInfoDayNum(String noticeDetailInfoDayNum) {
		this.noticeDetailInfoDayNum = noticeDetailInfoDayNum;
	}

	public String getNoticeDetailInfoMonthNum() {
		return noticeDetailInfoMonthNum;
	}

	public void setNoticeDetailInfoMonthNum(String noticeDetailInfoMonthNum) {
		this.noticeDetailInfoMonthNum = noticeDetailInfoMonthNum;
	}
	
}

