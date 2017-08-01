package com.weixin.model.query;

import com.commons.mybatis.BasePage;

 /**
 * 项目名称：elephant-model
 * 类名称：待监控服务器列表
 * 类描述：待监控服务器列表表单查询实体
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-17 18:39:32
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-17 18:39:32---(新建)
 *
 */
  
public class UserInfoQueryForm extends BasePage{
	private static final long serialVersionUID = 1L;
	private Long sn;  //主键，自增
	public Long getSn() {
		return sn;
	}
	public void setSn(Long sn) {
		this.sn = sn;
	}
	
	private String userId;  //用户ID
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	private String userName;  //姓名
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	private Long status;  //0可用；1不可用
	public Long getStatus() {
		return status;
	}
	public void setStatus(Long status) {
		this.status = status;
	}
	
}

