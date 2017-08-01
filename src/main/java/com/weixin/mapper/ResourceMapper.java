package com.weixin.mapper;

import java.util.List;
import java.util.Set;

import com.commons.mapper.GenericMapper;
import com.weixin.model.Resource;

/**
 * 
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 下午4:01:22
 * @description 资源Mapper
 */
public interface ResourceMapper extends GenericMapper<Resource, Long> {

    //根据role的id查找资源的url集合
    Set<String> getResourceSetByRoleSn(Long sn);

    //得到所有的资源
    List<Resource> getResources();

}
