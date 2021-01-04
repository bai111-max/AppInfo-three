package com.app.controller;

import com.app.pojo.DevUser;
import com.app.service.DevService;
import com.app.utils.EmptyUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.naming.NoPermissionException;
import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/dev")
public class DevLoginController {

    @Resource
    private DevService devService;

    /**
     * 权限处理
     * @return
     */
    @RequestMapping("/main")
    public String main(HttpServletRequest request) throws NoPermissionException {
        DevUser devUserSession = (DevUser) request.getSession().getAttribute("devUserSession");
        if (devUserSession == null){
            //进行全局异常处理
           throw new NoPermissionException();
        }
        return "/developer/main";
    }

    @RequestMapping(value = "/login")
    public String devLogin(){
        return "devlogin";
    }

    @RequestMapping(value = "/dologin",method = RequestMethod.POST)
    public String doLogin(HttpServletRequest request, @RequestParam(required = true) String devCode, @RequestParam(required = true) String devPassword){
        //1.进行用户名和密码的判空
        if (EmptyUtils.isNotEmpty(devCode) || EmptyUtils.isNotEmpty(devPassword)){
            //2.进行查询
            DevUser devUser = devService.doLogin(devCode,devPassword);
           //3.判空
            if (EmptyUtils.isNotEmpty(devUser)){
                //4.存储session
                request.getSession().setAttribute("devUserSession",devUser);
                //登录成功
                return "redirect:/dev/main";
            }else {
                request.setAttribute("error","用户名或者密码错误");
            }
        }else {
            request.setAttribute("error","用户名或者密码不能为空");
        }
        //登录失败进入devlogin.jsp页面
        return "devlogin";
    }
}
