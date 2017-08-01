package com.weixin.model.query;

import com.commons.mybatis.BasePage;

 /**
 * 项目名称：SmsMonitorPlate
 * 类名称：系统全局参数配置表
 * 类描述：系统全局参数配置表表单查询实体
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-18 14:29:50
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-18 14:29:50---(新建)
 *
 */
  
public class ConfigInfoQueryForm extends BasePage{

	private static final long serialVersionUID = 1L;
	private String sn;  //主键，自增
	
	public String getSn() {
		return sn;
	}
	
	public void setSn(String sn) {
		this.sn = sn;
	}
	
	private String key;
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	private String remark;
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}

