package com.weixin.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.commons.model.ShiroAdmin;
import com.weixin.model.AdminInfo;
import com.weixin.service.AdminInfoService;
import com.weixin.service.ResourceService;
import com.weixin.service.RoleService;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月24日 下午2:51:23
 * @description
 */
public class AdminRealm extends AuthorizingRealm{
	
	@Autowired
	AdminInfoService adminInfoService;
	@Autowired
	RoleService roleService;
	@Autowired
	ResourceService resourceService;
	
	
	//用于用户的认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		//获取用户的身份信息
		String adminId = (String) token.getPrincipal();
		//根据用户身份信息从数据库中查找密码
		List<AdminInfo> list = adminInfoService.findAdminByAdminCode(adminId);
		//将数据库中的信息和用户填写的数据交给shiro进行比对！

		if(list != null && list.size()>0) {
			AdminInfo adminInfo = list.get(0);
			if(adminInfo.getStatus() == 1) {

				//根据admin的主键查找拥有的角色名
				Set<String> roles = roleService.findRoleSetByAdminSn(adminInfo.getSn());

				//根据admin的主键查找拥有的角色sn
				List<Integer> roles_sn = roleService.findRolesSnsByAdminSn(adminInfo.getSn());

				Set<String> urls = new HashSet<String>();

				//循环遍历角色id，获取权限urls路径
				for(long i:roles_sn) {
					Set<String> url = resourceService.findResourceSetByRoleSn(i);
					urls.addAll(url);
				}
				//封装信息
				ShiroAdmin shiroAdmin = new ShiroAdmin(adminInfo.getAdminId(), adminInfo.getAdminName(), urls, roles);
				SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(shiroAdmin,adminInfo.getAdminPwd(),getName());
				return info;
			}else {
				//throw new LockedAccountException();	//账号被锁定
				throw new DisabledAccountException();	//账号被禁用
			}
			
		}
		return null;
	}

	//用户的授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取传递时的身份
		ShiroAdmin shiroAdmin = (ShiroAdmin) principals.getPrimaryPrincipal();

		//授权信息
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

		info.addRoles(shiroAdmin.getRoles());
		info.addStringPermissions(shiroAdmin.getUrls());
		return info;
	}

	

}
