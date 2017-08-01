package com.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.controller.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.weixin.model.UserInfo;
import com.weixin.model.query.UserInfoQueryForm;
import com.weixin.service.UserInfoService;

/**
 * 项目名称：SmsMonitorPlate
 * 类名称：用户列表
 * 类描述：用户列表功能的实现
 * 创建人：chenxiaoyi
 * 创建时间：2017-01-17 18:39:32
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-01-17 18:39:32---(新建)
 *
 */ 
  
@Controller
@RequestMapping("userInfo")
public class UserInfoController extends BaseController{
	
	public static Log log = LogFactory.getLog(UserInfoController.class);
	
	@Autowired
	private UserInfoService userInfoService;
	
	
	@RequestMapping(value="list")
	public String list(ModelMap model,UserInfoQueryForm userInfoQueryForm){
		String url = "/userInfo/list.do?";
		PageHelper.startPage(userInfoQueryForm.getCurPage(),userInfoQueryForm.getPgSize());
		List<Object> list = userInfoService.getListByPage(userInfoQueryForm);
		PageInfo<Object> pageResult = new PageInfo<Object>(list);
		pageOper(model, pageResult);
		model.addAttribute("userInfoQueryForm", userInfoQueryForm);
		model.addAttribute("url", url);
		return "user/user_list";
	}
	
	@RequestMapping(value="view",method=RequestMethod.GET)
	public String view(ModelMap model,UserInfo userInfo){
		if(userInfo.getSn() != 0){
			userInfo = userInfoService.getByPk(userInfo.getSn());
		}
		model.addAttribute("userInfo", userInfo);
		return "user/view";
	}

	@RequestMapping(value="view",method=RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(ModelMap model,UserInfo userInfo){
		String result =  "0";
		try {
			if(0 ==userInfo.getSn()){
				Map<String, Object> userMap = new HashMap<String, Object>();
				userMap.put("userId", userInfo.getUserId());
				List<Object> list = userInfoService.getList(userMap);
				if (list != null && list.size() > 0) {
					result = "3";
				} else{
					userInfoService.insert(userInfo);
					result =  "1";
				}
				
			}else{
				UserInfo user_info = userInfoService.getByPk(userInfo.getSn());
				if(user_info!=null){
					user_info.setUserId(userInfo.getUserId());
					user_info.setUserName(userInfo.getUserName());
					user_info.setUserPwd(userInfo.getUserPwd());
					user_info.setMobile(userInfo.getMobile());
					user_info.setEmail(userInfo.getEmail());
					user_info.setStatus(userInfo.getStatus());
					userInfoService.update(user_info);
					result =  "2";
				}
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return result;
	}

	/*@RequestMapping(value="delete")
	@ResponseBody
	public String delete(ModelMap model,String sn){
		//del_sns可删除的sn，exit_sns存在不能删除的sn
		String del_sns="" , exit_sns="" ,result = "0";
		try {
			if(!StringUtils.isNullOrEmpty(sn)){
				//去除,
				if (sn.trim().endsWith(",")) {
					sn = sn.substring(0, sn.length() - 1);
				}
				String[] sns_arr = sn.split(",");
				if(sns_arr!=null && sns_arr.length>0){
					//获取用户通知组
					List<Object> userGroupList = userGroupService.getList(new HashMap());
					for(int i=0 ; i< sns_arr.length;i++){
						boolean b = true ;
						if(userGroupList!=null && userGroupList.size()>0){
							for(int j=0;j<userGroupList.size();j++){
								UserGroup userGroup = (UserGroup)userGroupList.get(j);
								if(!StringUtils.isNullOrEmpty(userGroup.getUserSn())){
									String[] users_arr = userGroup.getUserSn().split(",");
									if(users_arr!=null && users_arr.length>0){
										for(int k=0;k<users_arr.length;k++){
											if(sns_arr[i].equals(users_arr[k])){
												b = false ;
												break;
											}
										}
									}
								}
							}
						}
						if(b){
							del_sns = del_sns + sns_arr[i]+",";
						}else{
							UserInfo userInfo  = userInfoService.getByPk(Integer.parseInt( sns_arr[i]));
							if(userInfo!=null){
								exit_sns = exit_sns + userInfo.getUserId()+",";
							}
							
						}
					}
				}
			}
			
			if(!StringUtils.isNullOrEmpty(del_sns)){
				//去除,
				if (del_sns.trim().endsWith(",")) {
					del_sns = del_sns.substring(0, del_sns.length() - 1);
					userInfoService.delete(del_sns);
					result = "1";
				}
			}
			if(!StringUtils.isNullOrEmpty(exit_sns)){
				//去除,
				if (exit_sns.trim().endsWith(",")) {
					exit_sns = exit_sns.substring(0, exit_sns.length() - 1);
				}
				result = exit_sns;
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		
		return result;
	}

*/
	/**
	 * ajax根据用户名称查询用户列表
	 * @param userInfoQueryForm
	 * @return 返回用户list集合
	 */
	@RequestMapping("/ajaxUserList")
	@ResponseBody
	public List ajaxByUserName(UserInfoQueryForm userInfoQueryForm) {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userName", userInfoQueryForm.getUserName());
		List<Object> list = userInfoService.getList(map);
		return list;
	}
	
}