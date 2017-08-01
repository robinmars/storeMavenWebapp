package com.weixin.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.controller.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.weixin.model.Resource;
import com.weixin.model.Role;
import com.weixin.model.RoleResource;
import com.weixin.model.query.RoleQueryForm;
import com.weixin.service.ResourceService;
import com.weixin.service.RoleResourceService;
import com.weixin.service.RoleService;

/**
 * @author zhongqihui
 * @date 2017年2月27日 下午4:28:47
 * @description 角色的Handle
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    public static Log log = LogFactory.getLog(RoleController.class);

    @Autowired
    private RoleService roleService;
    @Autowired
    private ResourceService resourceService;
    @Autowired
    private RoleResourceService roleResourceService;


    /**
     * 分页展示role列表
     *
     * @param model
     * @param roleQueryForm
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap model, RoleQueryForm roleQueryForm) {
        String url = "/role/list.do?";
        //分页
        PageHelper.startPage(roleQueryForm.getCurPage(), roleQueryForm.getPgSize());
        //从数据库中查询数据
        List<Object> list = roleService.getListByPage(roleQueryForm);
        PageInfo<Object> pageResult = new PageInfo<Object>(list);
        //封装模型数据和分页信息
        pageOper(model, pageResult);
        model.addAttribute("roleQueryForm", roleQueryForm);
        model.addAttribute("url", url);
        return "role/role_list";
    }

    //修改页面的跳转
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Role role) {
    	if(role.getSn()!= 0 ){
			//根据id，查询数据库中的数据
    		role = roleService.getByPk(role.getSn());
    	}
    	//将数据放入模型
        model.addAttribute("role", role);
        //视图的跳转
        return "role/view";
    }

    /**
     * 修改role提交
     * @param role
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Role role) {
    	String result = "0";
    	if(role.getSn()!=0){
    		 //从数据库获取数据
            Role db_role = roleService.getByPk(role.getSn());
            //封装数据
            if (db_role != null) {
                role.setInsertTime(db_role.getInsertTime());
                roleService.update(role);
                result = "2";
            }
    	}
        return result;
    }

    /**
     * 添加role页面跳转
     * @param model
     * @return
     */
    @RequestMapping(value = "/add" ,method = RequestMethod.GET)
    public String add(Model model) {
        return "role/view";
    }


    /**
     * 添加role提交
     * @param role
     * @return
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public String add(Role role) {
        String result = "0";
        if (role != null) {
            roleService.insert(role);
            result = "1";
        }
        return result;
    }


    /**
     *  授权页面的跳转
     * @return
     */
    @RequestMapping(value = "/addPermission",method = RequestMethod.GET)
    public String addPermission(Model model,Long sn) {
        if (sn != null) {
            //所有资源
            List<Resource> resourceList = resourceService.findResources();
            //已有的资源
            Set<String> resourceSet  =resourceService.findResourceSetByRoleSn(sn);
            //资源父节点集合
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("psn", 0);
            List<Object> parentList = resourceService.getList(map);

            model.addAttribute("resourceList", resourceList);
            model.addAttribute("resourceSet", resourceSet);
            model.addAttribute("roleSn", sn);
            model.addAttribute("parentList", parentList);
        }
        return "role/permission";
    }


    @RequestMapping(value = "/addPermission",method = RequestMethod.POST)
    @ResponseBody
    public String addPermission(@RequestParam("resourceSn") String resourceSn, @RequestParam("roleSn") Long roleSn) {
        String result = "0";
        //首先删除数据，根据角色id，删除RoleResource
        if (roleSn != null &&resourceSn != null ) {
            roleResourceService.deleteByRoleSn(roleSn);
            String[] str = resourceSn.split(",");
            for(int i=0;i<str.length;i++) {
                if(!StringUtils.isNullOrEmpty(str[i])){
                     roleResourceService.insert(new RoleResource(roleSn,Long.parseLong(str[i])));
                }
            }
            result = "1";
        }
        return result;
    }
    
    /**
     * 删除role，包括批量删除
     *
     * @param sn
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String sn) {
        String result = "0";
        //从数据库去数据
        if (sn != null && sn.trim() != "") {
            //删除角色表的数据
            roleService.delete(sn);
            //删除role_resource表中的数据
            roleResourceService.deleteBatch(sn);
            result = "1";
        }
        //json数据返回，1表示删除成功，0表示删除失败
        return result;
    }

}
