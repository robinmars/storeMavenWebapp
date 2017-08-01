package com.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.service.GenericService;
import com.weixin.mapper.RoleResourceMapper;
import com.weixin.model.RoleResource;

/**
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017/3/2 17:37
 * @description roleResource  service层
 */
@Service
public class RoleResourceService extends GenericService<RoleResource,Long> {

    private RoleResourceMapper roleResourceMapper;

    public RoleResourceService() {
    }

    @Autowired
    public RoleResourceService(RoleResourceMapper roleResourceMapper) {
        super(roleResourceMapper);
        this.roleResourceMapper = roleResourceMapper;
    }

    public void deleteByRoleSn(Long sn) {
        roleResourceMapper.deleteByRoleSn(sn);
    }

    public void deleteBatch(String sns) {
        roleResourceMapper.deleteBatch(sns);
    }
}
