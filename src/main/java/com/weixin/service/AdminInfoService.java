package com.weixin.service;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.commons.redis.DC_Adapter;
import com.commons.service.GenericService;
import com.commons.util.Constants;
import com.weixin.mapper.AdminInfoMapper;
import com.weixin.model.AdminInfo;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：文件
 * 类描述：文件的Service层业务实现
 * 创建人：chenxiaoyi
 * 创建时间：2016-12-12 12:21:59
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2016-12-12 12:21:59---(新建)
 *
 */
 
@Service
public class AdminInfoService extends GenericService<AdminInfo,Integer>{
	public static Log log = LogFactory.getLog(AdminInfoService.class);
	@Resource(name = "redisCache")
	protected DC_Adapter redisCache;
	private AdminInfoMapper adminInfoMapper;
	
	public AdminInfoService() {
		super();
	}
	
	@Autowired
	public AdminInfoService(AdminInfoMapper adminInfoMapper) {
		super(adminInfoMapper);
		this.adminInfoMapper = adminInfoMapper;
	}
	
	public List<String> findResourceNameListByAdminId(Long sn) {
		return adminInfoMapper.getResourceNameListByAdminSn(sn);
	}

	//根据admin的账号查找admin
	public List<AdminInfo> findAdminByAdminCode(String adminId) {
		return adminInfoMapper.getAdminByAdminCode(adminId);
	}

	
	/**
	 * 用户登录,保存用户
	 * @Title: setUser
	 * @param cookieKey
	 * 区分登录平台 {@link UserConstans}
	 * @param valueStr
	 * 用户值(id or beanJson)
	 * @param autoLogin 是否自动登录（7天内）
	 * @return
	 * @author chenxiaoyi
	 * @throws
	 */
	public boolean setUser(String cookieKey, Object object, boolean autoLogin, HttpServletResponse response) {
		try {
			if(autoLogin){
				redisCache.setex(cookieKey, Constants.USER_OUT_TIME_7DAYS, JSONObject.toJSONString(object));
			}else{
				redisCache.setex(cookieKey, Constants.USER_OUT_TIME_2HOURS, JSONObject.toJSONString(object));
			}
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	
	
	/**
	 * 单用户登录,保存用户sessionId
	 * @Title: setUser
	 * @param cookieKey
	 * @return
	 * @author chenxiaoyi
	 * @throws
	 */
	public boolean setUser(String key, String valueStr, HttpServletResponse response) {
		try {
			redisCache.setex(Constants.SMS_ADMIN_SESSION+"_"+key, Constants.USER_OUT_TIME_7DAYS, valueStr);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}
	
	public boolean delUser(String key, HttpServletResponse response) {
		try {
			redisCache.del(Constants.SMS_ADMIN_SESSION+"_"+key);
			return true;
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}
	
	
}

