package com.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.weixin.model.AdminInfo;
import com.weixin.model.AdminRole;
import com.weixin.model.Role;
import com.weixin.model.query.AdminInfoQueryForm;
import com.weixin.service.AdminInfoService;
import com.weixin.service.AdminRoleService;
import com.weixin.service.RoleService;

/*
 * 
 * 项目名称：SmsMonitorPlate
 * 类名称：AdminInfoController
 * 类描述：用户功能方法的实现
 * 创建人：chenxiaoyi
 * 创建时间：2017-1-16 上午11:42:32
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2017-1-16 上午11:42:32---(新建)
 *
 */
@Controller
@RequestMapping(value = "admin")
public class AdminInfoController extends BaseController {

	public static Log log = LogFactory.getLog(AdminInfoController.class);

	@Autowired
	AdminInfoService adminInfoService;
	@Autowired
	RoleService roleService;
	@Autowired
	AdminRoleService adminRoleService;

	@RequestMapping(value = "list")
	public String list(HttpServletRequest request, ModelMap model, AdminInfoQueryForm adminInfoQueryForm) {
		String url = "/admin/list.do?";
		PageHelper.startPage(adminInfoQueryForm.getCurPage(), adminInfoQueryForm.getPgSize());
		String admin_id = "";
		ShiroAdmin shiroAdmin = (ShiroAdmin) SessionUtil.get(request, Constants.SMS_ADMIN_SESSION);
		if (shiroAdmin != null) {
			admin_id = shiroAdmin.getAdminSn();
		}

		//封装角色名称
		List<Object> list = adminInfoService.getListByPage(adminInfoQueryForm);
		AdminInfo adminInfo = null;
		for(int i=0;i<list.size();i++) {
			adminInfo = (AdminInfo) list.get(i);
			Set<String> roleNames = roleService.findRoleSetByAdminSn(adminInfo.getSn());
			StringBuffer roleName = new StringBuffer();
			for (String str : roleNames) {
				roleName.append(",");
				roleName.append(str);
			}
			int length = roleName.length();
			if (roleName.indexOf(",")==0) {
				adminInfo.setRoleName(roleName.substring(1, length));
			}
		}

		PageInfo<Object> pageResult = new PageInfo<Object>(list);
		pageOper(model, pageResult);
		model.addAttribute("adminInfoQueryForm", adminInfoQueryForm);
		model.addAttribute("admin_id", admin_id);
		model.addAttribute("url", url);
		return "adminInfo/admin_list";
	}

	@RequestMapping(value = "view", method = RequestMethod.GET)
	public String view(HttpServletRequest request, ModelMap model, AdminInfo adminInfo) {
		if (adminInfo.getSn() != 0) {
			adminInfo = adminInfoService.getByPk(adminInfo.getSn());
			// 根据admin的主键找到adminRole的集合
			List<AdminRole> adminRoleList = adminRoleService.findAdminRoleByAdminSn(adminInfo.getSn());
			model.addAttribute("adminRoleList", adminRoleList);
		}
		String admin_id = "";
		ShiroAdmin shiroAdmin = (ShiroAdmin) SessionUtil.get(request, Constants.SMS_ADMIN_SESSION);
		if (shiroAdmin != null) {
			admin_id = shiroAdmin.getAdminSn();
		}
		// 查询roles的集合并填充数据
		List<Role> roles = roleService.findRoles(new Role());
		model.addAttribute("roles", roles);

		model.addAttribute("admin_id", admin_id);
		model.addAttribute("adminInfo", adminInfo);
		return "adminInfo/view";
	}

	@RequestMapping(value = "view", method = RequestMethod.POST)
	@ResponseBody
	public String addOrUpdate(HttpServletRequest request, ModelMap model, AdminInfo adminInfo, String roleSn) {
		String result = "0";
		try {
			Map<String, Object> adminMap = new HashMap<String, Object>();
			adminMap.put("adminId", adminInfo.getAdminId());
			List<Object> list = adminInfoService.getList(adminMap);
			if (0 == adminInfo.getSn()) {
				if (list != null && list.size() > 0) {
					result = "3";
				} else {
					// 插入adminInfo并返回插入的主键
					adminInfoService.insertGetPk(adminInfo);
					if (!StringUtils.isNullOrEmpty(roleSn)) {
						// 获取主键
						long sn = adminInfo.getSn();
						// 把roleSn切割成string[]数组
						String[] str = roleSn.split(",");
						// 遍历并插入AdminRole
						for (int i = 0; i < str.length; i++) {
							if (!StringUtils.isNullOrEmpty(str[i])) {
								long id = Integer.parseInt(str[i]);
								adminRoleService.insert(new AdminRole(sn, id));
							}
						}
					}
					result = "1";
				}
			} else {
				AdminInfo admin_info = adminInfoService.getByPk(adminInfo.getSn());
				if (list != null && list.size() > 0) {
					if(!adminInfo.getAdminId().equals(admin_info.getAdminId())){
						AdminInfo adminObject = (AdminInfo) list.get(0);
						if(adminInfo.getAdminId().equals(adminObject.getAdminId())){
							result = "3";
						}
					}
				}
				// 先把数据库中的权限角色删除在插入，因为数据库admin对admin_role是一对多的关系
				adminRoleService.deleteAdminRoleByAdminSn(adminInfo.getSn());
				if (!StringUtils.isNullOrEmpty(roleSn)) {
					// 把roleSn切割成string[]数组
					String[] str = roleSn.split(",");
					// 遍历并插入AdminRole
					for (int i = 0; i < str.length; i++) {
						if (!StringUtils.isNullOrEmpty(str[i])) {
							long id = Integer.parseInt(str[i]);
							adminRoleService.insert(new AdminRole(adminInfo.getSn(), id));
						}
					}
				}
				if (admin_info != null) {
					admin_info.setAdminId(adminInfo.getAdminId());
					admin_info.setAdminName(adminInfo.getAdminName());
					admin_info.setAdminPwd(adminInfo.getAdminPwd());
					admin_info.setStatus(adminInfo.getStatus());
					adminInfoService.update(admin_info);
					result = "2";
				}
				/*ShiroAdmin shiroAdmin = (ShiroAdmin) SessionUtil.get(request, Constants.SMS_ADMIN_SESSION);
				if (shiroAdmin != null) {
					if (shiroAdmin.getAdminSn().equals(admin_info.getAdminId())) {
						SessionUtil.put(request, Constants.SMS_ADMIN_SESSION, admin_info);
					}
				}*/
			}
		} catch (Exception e) {
			log.info(e.getMessage(), e);
		}
		return result;
	}

	@RequestMapping(value = "delete")
	@ResponseBody
	public String delete(ModelMap model, String sn) {
		String result = "0";
		if (!StringUtils.isNullOrEmpty(sn)) {
			try {
				adminInfoService.delete(sn);
				adminRoleService.deleteAdminRoleByAdminSn(Long.parseLong(sn));
				result = "1";
			} catch (Exception e) {
				log.info(e.getMessage(), e);
			}
		}
		return result;
	}

	// 查看权限，根据admin的主键查询权限
	@RequestMapping("/permission")
	public String permission(Model model, Long sn) {
		// 查询数据并填充模型数据
		if (sn != null) {
			List<String> resourceNameList = adminInfoService.findResourceNameListByAdminId(sn);
			model.addAttribute("resourceNameList", resourceNameList);
		}
		// 返回模型视图
		return "adminInfo/permission";
	}

}
