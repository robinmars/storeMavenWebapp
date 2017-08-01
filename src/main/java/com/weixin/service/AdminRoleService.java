package com.weixin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.service.GenericService;
import com.weixin.mapper.AdminRoleMapper;
import com.weixin.model.AdminRole;

/**
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017/3/2 13:13
 * @description
 */
@Service
public class AdminRoleService extends GenericService<AdminRole,Integer> {
    private AdminRoleMapper adminRoleMapper;

    public AdminRoleService() {

    }
    @Autowired
    public AdminRoleService(AdminRoleMapper adminRoleMapper) {
        super(adminRoleMapper);
        this.adminRoleMapper = adminRoleMapper;
    }

    //根据admin的id查询AdminRole
    public List<AdminRole> findAdminRoleByAdminSn(Long adminSn) {
        return adminRoleMapper.getAdminRoleByAdminSn(adminSn);
    }

    //根据admin的id删除AdminRole
    public void deleteAdminRoleByAdminSn(Long adminSn) {
        adminRoleMapper.deleteAdminRoleByAdminSn(adminSn);
    }


}
