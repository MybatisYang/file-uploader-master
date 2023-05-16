package cn.csix.project.system.controller;

import java.util.List;
import java.util.Set;

import cn.csix.framework.security.LoginUser;
import cn.csix.framework.security.service.SysLoginService;
import cn.csix.framework.security.service.SysPermissionService;
import cn.csix.framework.security.service.TokenService;
import cn.csix.framework.web.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.csix.common.constant.Constants;
import cn.csix.common.utils.ServletUtils;
import cn.csix.project.system.domain.SysMenu;
import cn.csix.project.system.domain.SysUser;
import cn.csix.project.system.service.ISysMenuService;

/**
 * 登录验证
 * 
 *
 */
@RestController
public class SysLoginController
{
    @Autowired
    private SysLoginService loginService;

    @Autowired
    private ISysMenuService menuService;

    @Autowired
    private SysPermissionService permissionService;

    @Autowired
    private TokenService tokenService;

    /**
     * 登录方法
     * 
     * @param username 用户名
     * @param password 密码
     * @param captcha 验证码
     * @param uuid 唯一标识
     * @return 结果
     */
    @PostMapping("/login")
    public AjaxResult login(String username, String password, String code, String uuid)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.login(username, password, code, uuid);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 登录方法
     *
     * @param name 用户名
     * @param password 密码
     * @return 结果
     */
    @PostMapping("/enterpriselogin")
    public AjaxResult enterpriseLogin(String name, String password)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.enterpriseLogin(name, password);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 登录方法
     *
     * @param name 用户名
     * @param password 密码
     * @return 结果
     */
    @PostMapping("/otherlogin")
    public AjaxResult otherLogin(String name, String password)
    {
        AjaxResult ajax = AjaxResult.success();
        // 生成令牌
        String token = loginService.otherLogin(name, password);
        ajax.put(Constants.TOKEN, token);
        return ajax;
    }

    /**
     * 获取用户信息
     * 
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        SysUser user = loginUser.getUser();
        // 角色集合
        Set<String> roles = permissionService.getRolePermission(user);
        // 权限集合
        Set<String> permissions = permissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        ajax.put("user", user);
        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     * 
     * @return 路由信息
     */
    @GetMapping("getRouters")
    public AjaxResult getRouters()
    {
        LoginUser loginUser = tokenService.getLoginUser(ServletUtils.getRequest());
        // 用户信息
        SysUser user = loginUser.getUser();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(user.getUserId());
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
