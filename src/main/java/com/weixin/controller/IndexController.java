package com.weixin.controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.commons.controller.BaseController;
import com.commons.model.ShiroAdmin;
import com.commons.redis.DC_Adapter;
import com.commons.util.Constants;
import com.commons.util.SessionUtil;
import com.commons.util.ValidateCode;
import com.github.pagehelper.PageHelper;
import com.mysql.jdbc.StringUtils;
import com.weixin.model.AdminInfo;
import com.weixin.model.MainInfo;
import com.weixin.service.AdminInfoService;

/**
 * 项目名称：SmsMonitorPlate 类名称：主页 类描述：主页功能的实现 创建人：chenxiaoyi 创建时间：2016-05-22
 * 22:21:59
 * 
 * @version V1.0.0.T.1 -----------------------------------------
 *          修改记录(迭代更新)：chenxiaoyi- 2016-05-22 22:21:59---(新建)
 */

@Controller
public class IndexController extends BaseController {

	public static Log log = LogFactory.getLog(IndexController.class);


	@Autowired
	AdminInfoService adminInfoService;


	@Resource(name = "redisCache")
	protected DC_Adapter redisCache;

	/**
	 * 进入管理后台页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String startWeb() {
		return "redirect:/index.do";
	}

	/**
	 * 进入管理后台页面
	 *
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String main(HttpServletRequest request, ModelMap model) {
		return "index";
	}

	/**
	 * 后台显示首页
	 *
	 * @param model
	 * @param mainInfo
	 * @return
	 */
	@RequestMapping(value = "/main")
	public String index(ModelMap model, MainInfo mainInfo) {
		
		return "main";
	}

	/**
	 * 进入登录页面
	 *
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	/**
	 * 登录
	 *
	 * @param request
	 * @param model
	 * @param adminInfo
	 *            用户输入信息
	 * @return 认证成功进入跳转首页；失败跳转登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String loginIn(HttpServletRequest request, HttpServletResponse response, Model model, AdminInfo adminInfo) {
		String result = "";
		boolean auto_login = false;
		if (!StringUtils.isNullOrEmpty(adminInfo.getAdminId()) && !StringUtils.isNullOrEmpty(adminInfo.getAdminPwd())
				&& !StringUtils.isNullOrEmpty(adminInfo.getValidateCode())) {
			// 获取redis的验证码
			String code = redisCache.get(Constants.VALIDATE_CODE + "_" + request.getSession().getId());
			if (StringUtils.isNullOrEmpty(code)) {
				code = SessionUtil.getString(request, "validateCode");
			}
			if (!StringUtils.isNullOrEmpty(code)
					&& code.toLowerCase().equals(adminInfo.getValidateCode().toLowerCase())) {
				UsernamePasswordToken token = new UsernamePasswordToken(adminInfo.getAdminId(),
						adminInfo.getAdminPwd());
				// 是否记住密码
				if ("1".equals(adminInfo.getRememberPwd())) {
					auto_login = true;
					token.setRememberMe(true);
				} else {
					token.setRememberMe(false);
				}
				// subject对象
				Subject subject = SecurityUtils.getSubject();
				// if(!subject.isAuthenticated()) {
				try {
					subject.login(token);
					ShiroAdmin admin = (ShiroAdmin) subject.getPrincipal();
					// 移除验证码
					SessionUtil.remove(request, "validateCode");
					model.addAttribute("adminInfo", admin);
					SessionUtil.put(request, Constants.SMS_ADMIN_SESSION, admin);
					// 单用户登录
					adminInfoService.setUser(admin.getAdminSn(), request.getSession().getId(), response);
//					adminInfoService.setUser(request.getSession().getId(), admin, auto_login, response);
					return "redirect:" + basePath() + "index.do";
				} catch (IncorrectCredentialsException e) {
					result = "登录密码不正确";
				} catch (UnknownAccountException e) {
					result = "账号不存在";
				} catch (DisabledAccountException e) {
					result = "该账号已被禁用";
				} catch (Exception e) {
					e.printStackTrace();
					result = "未知错误";
				}
				// }
			} else {
				if (StringUtils.isNullOrEmpty(code)) {
					result = "验证码失效，请重试";
				} else {
					result = "验证码错误，请重新输入";
				}
			}
		}
		model.addAttribute("adminInfo", adminInfo);
		model.addAttribute("result", result);
		return "login";
	}

	/**
	 * 生成验证码
	 *
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/validateCode")
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-Control", "no-cache");
		String verifyCode = ValidateCode.generateTextCode(ValidateCode.TYPE_NUM_ONLY, 4, null);
		request.getSession().setAttribute(Constants.VALIDATE_CODE, verifyCode);
		// 验证码放入redis
		redisCache.setex(Constants.VALIDATE_CODE + "_" + request.getSession().getId(), Constants.VALIDATE_CODE_TIME,
				verifyCode);
		response.setContentType("image/jpeg");
		BufferedImage bim = ValidateCode.generateImageCode(verifyCode, 90, 30, 3, true, Color.WHITE, Color.BLACK, null);
		ImageIO.write(bim, "JPEG", response.getOutputStream());
	}

	/**
	 * 无权限访问
	 *
	 * @return
	 */
	@RequestMapping("/unauthorized")
	public String unauthorized() {
		if (!SecurityUtils.getSubject().isAuthenticated()) {
			return "redirect:/login";
		}
		return "unauthorized";
	}


}
