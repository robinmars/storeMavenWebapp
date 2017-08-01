package com.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.service.GenericService;
import com.weixin.mapper.ConfigInfoMapper;
import com.weixin.model.ConfigInfo;

  /**
 * 项目名称：SmsMonitorPlate
 * 类名称：系统全局参数配置表
 * 类描述：系统全局参数配置表的Service层业务实现
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-18 14:29:50
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-18 14:29:50---(新建)
 *
 */
 
@Service
public class ConfigInfoService extends GenericService<ConfigInfo,Long>{

	private ConfigInfoMapper configInfoMapper;
	
	public ConfigInfoService() {
		super();
	}
	
	@Autowired
	public ConfigInfoService(ConfigInfoMapper configInfoMapper) {
		super(configInfoMapper);
		this.configInfoMapper = configInfoMapper;
	}

	  /**
	   * 根据配置信息表的key获取对象
	   * @param key
	   * @return
	   */
	public ConfigInfo getConfigByKey(String key) {
		return configInfoMapper.getByKey(key);
	}

}

