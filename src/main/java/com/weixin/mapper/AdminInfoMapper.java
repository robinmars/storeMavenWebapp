package com.weixin.mapper;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.commons.mapper.GenericMapper;
import com.weixin.model.AdminInfo;

/**
 * 项目名称：dao
 * 类名称：文件
 * 类描述：文件的dao层业务接口
 * 创建人：chenxiaoyi
 * 创建时间：2016-12-12 12:21:59
 * @version V1.0.0.T.1
 * ----------------------------------------- 
 * 修改记录(迭代更新)：chenxiaoyi- 2016-12-12 12:21:59---(新建)
 *
 */ 
 
@Repository
public interface AdminInfoMapper extends GenericMapper<AdminInfo,Long>{

    //根据admin的主键查询资源的name列表
    List<String> getResourceNameListByAdminSn(Long sn);

    //根据admin的账号查询
    List<AdminInfo> getAdminByAdminCode(String string);
}













