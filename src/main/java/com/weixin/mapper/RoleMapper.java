package com.weixin.mapper;

import java.util.List;
import java.util.Set;

import com.commons.mapper.GenericMapper;
import com.weixin.model.Role;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017/3/1 13:32
 * @description 角色Mapper
 */
public interface RoleMapper extends GenericMapper<Role, Long> {


    //根据admin的id查找role的name集合
    Set<String> getRoleSetByAdminSn(Long sn);

    //根据admin的sn查找role的sn集合
    List<Integer> getRoleSnsByAdminSn(Long sn);

    //查询所有的roles
    List<Role> getRoles(Role role);

}
