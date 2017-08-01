package com.weixin.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.service.GenericService;
import com.weixin.mapper.RoleMapper;
import com.weixin.model.Role;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 下午1:48:24
 * @description 角色的service层
 */
@Service
public class RoleService extends GenericService<Role, Long> {
	
	
	private RoleMapper roleMapper;

	public RoleService() {
		super();
	}

	@Autowired
	public RoleService(RoleMapper roleMapper) {
		super(roleMapper);
		this.roleMapper = roleMapper;
		
	}

	//根据admin的id查找角色列表
	public Set<String> findRoleSetByAdminSn(Long sn) {
		return roleMapper.getRoleSetByAdminSn(sn);
	}


	//根据admin的id查询角色id的集合
	public List<Integer> findRolesSnsByAdminSn(Long sn){
		return roleMapper.getRoleSnsByAdminSn(sn);
	}


	//根据role查询role的集合
	public List<Role> findRoles(Role role){
		return roleMapper.getRoles(role);
	}
	
}
