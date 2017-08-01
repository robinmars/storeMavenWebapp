package com.weixin.mapper;

import java.util.List;

import com.commons.mapper.GenericMapper;
import com.weixin.model.AdminRole;


/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017/3/1 13:32
 * @description admin_role Mapper接口
 */
public interface AdminRoleMapper extends GenericMapper<AdminRole, Long> {

    //根据admin的id查找AdminRole
    List<AdminRole> getAdminRoleByAdminSn(Long adminSn);


    //根据admin的id删除adminRole
    void deleteAdminRoleByAdminSn(Long adminSn);


}
