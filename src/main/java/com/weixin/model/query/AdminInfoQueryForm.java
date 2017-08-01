package com.weixin.model.query;

import com.commons.mybatis.BasePage;

/**
 * 项目名称：model 类名称：分类 类描述：分类实体 创建人：chenxiaoyi 创建时间：2016-12-08 10:21:59
 * 
 * @version V1.0.0.T.1 -----------------------------------------
 *          修改记录(迭代更新)：chenxiaoyi- 2016-12-08 10:21:59---(新建)
 * 
 */

public class AdminInfoQueryForm extends BasePage{

	private static final long serialVersionUID = 1L;

	private Long sn; // 主键，自增

	private String adminId; // 账号

	private String adminName; // 用户名

	private String status; // 状态 0:关闭 1 开启\

	private String roleName;

	public Long getSn() {
		return sn;
	}

	public void setSn(Long sn) {
		this.sn = sn;
	}

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
