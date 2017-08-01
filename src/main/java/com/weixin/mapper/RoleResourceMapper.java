package com.weixin.mapper;

import com.commons.mapper.GenericMapper;
import com.weixin.model.RoleResource;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017/3/2 17:36
 * @description  roleResource mapper 接口
 */
public interface RoleResourceMapper extends GenericMapper<RoleResource,Long> {

    //根据角色sn删除RoleResource
    void deleteByRoleSn(Long sn);

    //根据角色sn批量删除
    void deleteBatch(String sns);

}
