package com.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.service.GenericService;
import com.weixin.mapper.UserInfoMapper;
import com.weixin.model.UserInfo;

  /**
 * 项目名称：SmsMonitorPlate
 * 类名称：待监控服务器列表
 * 类描述：待监控服务器列表的Service层业务实现
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-17 18:39:32
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-17 18:39:32---(新建)
 *
 */
 
@Service
public class UserInfoService extends GenericService<UserInfo,Long>{

	private UserInfoMapper userInfoMapper;
	
	public UserInfoService() {
		super();
	}
	
	@Autowired
	public UserInfoService(UserInfoMapper userInfoMapper) {
		super(userInfoMapper);
		this.userInfoMapper = userInfoMapper;
	}

}

