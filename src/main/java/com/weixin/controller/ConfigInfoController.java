package com.weixin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.controller.BaseController;
import com.commons.model.ShiroAdmin;
import com.commons.util.Constants;
import com.commons.util.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.weixin.model.ConfigInfo;
import com.weixin.model.query.ConfigInfoQueryForm;
import com.weixin.service.ConfigInfoService;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：系统全局参数配置表
 * 类描述：系统全局参数配置表功能的实现
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-18 14:29:50
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-18 14:29:50---(新建)
 *
 */ 
  
@Controller
@RequestMapping("configInfo")
public class ConfigInfoController extends BaseController{
	
	public static Log log = LogFactory.getLog(ConfigInfoController.class);
	
	@Autowired
	private ConfigInfoService configInfoService;
	
	
	@RequestMapping(value="list")
	public String noticeList(ModelMap model,ConfigInfoQueryForm configInfoQueryForm){
		String url = "/configInfo/list.do?";
		PageHelper.startPage(configInfoQueryForm.getCurPage(),configInfoQueryForm.getPgSize());
		List<Object> list = configInfoService.getListByPage(configInfoQueryForm);
		PageInfo<Object> pageResult = new PageInfo<Object>(list);
		pageOper(model, pageResult);
		model.addAttribute("configInfoQueryForm", configInfoQueryForm);
		model.addAttribute("url",url);
		return "configInfo/config_list";
	}
	
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String view(ModelMap model,ConfigInfo configInfo){
		if(configInfo.getSn() != 0){
			configInfo = configInfoService.getByPk(configInfo.getSn());
		}
		model.addAttribute("configInfo", configInfo);
		return "configInfo/view";
	}

	@RequestMapping(value="view",method=RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(HttpServletRequest request ,ModelMap model,ConfigInfo configInfo){
		String result =  "0";
		try {
			if(0 ==configInfo.getSn()){
				configInfo.setOperator("system");
				configInfoService.insert(configInfo);
				result =  "1";
			}else{
				ShiroAdmin adminInfo = (ShiroAdmin) SessionUtil.get(request,Constants.SMS_ADMIN_SESSION);
				String admin_name = "";
				if (adminInfo != null) {
					admin_name = adminInfo.getAdminName();
				}
				
				ConfigInfo config_info = configInfoService.getByPk(configInfo.getSn());
				if(config_info!=null){
					config_info.setKey(configInfo.getKey());
					config_info.setValue(configInfo.getValue());
					config_info.setRemark(configInfo.getRemark());
					config_info.setOperator(admin_name);
					configInfoService.update(config_info);
					result =  "2";
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping(value="delete")
	@ResponseBody
	public String delete(ModelMap model,String sn){
		String result =  "0";
		if(!StringUtils.isNullOrEmpty(sn)){
			try {
				configInfoService.delete(sn);
				result =  "1";
			} catch (Exception e) {
				log.info(e.getMessage(), e);
			}
		}
		return result;
	}
	
	
}