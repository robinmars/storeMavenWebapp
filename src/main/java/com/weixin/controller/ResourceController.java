package com.weixin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.commons.controller.BaseController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mysql.jdbc.StringUtils;
import com.weixin.model.Resource;
import com.weixin.model.query.ResourceQueryForm;
import com.weixin.service.ResourceService;

/**
 * @author zhongqihui
 * @date 2017年2月27日 下午5:44:08
 * @description 资源的Handle
 */
@Controller
@RequestMapping("/resource")
public class ResourceController extends BaseController {

    @Autowired
    private ResourceService resourceService;


    /**
     * 分页列表，包括模糊查询
     *
     * @param model
     * @param resourceQueryForm
     * @return
     */
    @RequestMapping(value = "/list")
    public String list(ModelMap model, ResourceQueryForm resourceQueryForm) {
        String url = "/resource/list.do?";
        //获取子权限
        PageHelper.startPage(resourceQueryForm.getCurPage(), resourceQueryForm.getPgSize());
        List<Object> list = resourceService.getListByPage(resourceQueryForm);
        PageInfo<Object> pageResult = new PageInfo<Object>(list);

        //获取父权限
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("psn", 0);
        List<Object> groupList = resourceService.getList(map);

        //封装模型数据和分页信息
        pageOper(model, pageResult);
        model.addAttribute("resourceQueryForm", resourceQueryForm);
        model.addAttribute("url", url);
        model.addAttribute("groupList", groupList);
        return "resource/list";
    }


    /**
     * 权限组的新增和修改跳转
     * @param sn
     * @param modelMap
     * @return
     */
    @RequestMapping(value = "/group",method = RequestMethod.GET)
    public String group(ModelMap modelMap, String sn) {
        if(!StringUtils.isNullOrEmpty(sn)) {
            Resource resource = resourceService.getByPk(Long.parseLong(sn));
            modelMap.addAttribute("resource", resource);
        }
        return "resource/group_view";
    }


    /**
     * 权限组新增和修改提交
     * @param resource
     * @return
     */
    @RequestMapping(value = "/group",method = RequestMethod.POST)
    @ResponseBody
    public String group(Resource resource) {
        String result = "0";
        if(resource.getSn() == null) {
            resource.setPsn(0);
            resourceService.insert(resource);
            result = "1";
        }else {
            Resource dbResource = resourceService.getByPk(resource.getSn());
            dbResource.setName(resource.getName());
            dbResource.setDescription(resource.getDescription());
            resourceService.update(dbResource);
            result = "2";
        }
        return result;
    }


    /**
     * 添加resource页面的跳转
     *
     * @param model
     * @param isParent 是否父节点资源 0为否；1为是
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(Model model, String isParent) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("psn", 0);
        List<Object> groupList = resourceService.getList(map);
        model.addAttribute("groupList", groupList);
        model.addAttribute("isParent", isParent);
        return "resource/view";
    }

    /**
     * 添加resource提交
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(Resource resource) {
        String result = "0";
        if (resource != null) {
            resourceService.insert(resource);
            result = "1";
        }
        return result;

    }

    /**
     * 修改resource页面的跳转
     * @param model
     * @param resource
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(Model model, Resource resource) {
        if (resource.getSn() != 0) {
            //根据id，查询数据库中的数据
            resource = resourceService.getByPk(resource.getSn());

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("psn", 0);
            List<Object> groupList = resourceService.getList(map);
            model.addAttribute("groupList", groupList);
        }
        //将数据放入模型
        model.addAttribute("resource", resource);
        //视图的跳转
        return "resource/view";
    }

    /**
     * 修改resource提交
     *
     * @param resource
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public String update(Resource resource) {
        String result = "0";
        //从数据库获取数据
        Resource db_resource = resourceService.getByPk(resource.getSn());
        //封装数据
        if (db_resource != null) {
            resource.setInsertTime(db_resource.getInsertTime());
            resourceService.update(resource);
            result = "2";
        }
        return result;
    }

    /**
     * resource
     *
     * @param sn
     * @return json数据返回，2表示存在子节点，1表示删除成功，0表示删除失败
     */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(String sn) {
        String result = "0";
        if (sn != null && sn.trim() != "") {
            Resource dbResource = resourceService.getByPk(Long.parseLong(sn));
            if (dbResource != null) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("psn", dbResource.getSn());
                List<Object> list = resourceService.getList(map);
                if (list == null || list.size() == 0) {
                    resourceService.delete("" + dbResource.getSn());
                    result = "1";
                } else {
                    result = "2";
                }
            }
        }
        return result;
    }

}
