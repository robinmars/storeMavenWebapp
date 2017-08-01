package com.weixin.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commons.service.GenericService;
import com.weixin.mapper.ResourceMapper;
import com.weixin.model.Resource;

/**
 * @author 钟启辉
 * @company www.jiweitech.com
 * @date 2017年2月27日 下午5:45:48
 * @description
 */
@Service
public class ResourceService extends GenericService<Resource, Integer> {

	private ResourceMapper resourceMapper;

	public ResourceService() {
		super();
	}

	@Autowired
	public ResourceService(ResourceMapper resourceMapper) {
		super(resourceMapper);
		this.resourceMapper = resourceMapper;
	}

	//根据角色的id查找资源
	public Set<String> findResourceSetByRoleSn(Long sn) {
		return resourceMapper.getResourceSetByRoleSn(sn);
	}


	//得到所有的资源
	public List<Resource> findResources() {
		return resourceMapper.getResources();
	}
}
