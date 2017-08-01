package com.weixin.mapper;

import org.springframework.stereotype.Repository;

import com.commons.mapper.GenericMapper;
import com.weixin.model.ConfigInfo;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：系统全局参数配置表
 * 类描述：系统全局参数配置表的Mapper层业务接口
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-18 14:29:50
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-18 14:29:50---(新建)
 *
 */ 
 
@Repository
public interface ConfigInfoMapper extends GenericMapper<ConfigInfo,Long>{
	/**
	 * 根据配置表中的key获取
	 * @param str
	 * @return
	 */
	ConfigInfo getByKey(String str);
}













