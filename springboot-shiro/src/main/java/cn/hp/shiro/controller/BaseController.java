package cn.hp.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ironhide
 * @create 2020-04-25-16:10
 */
@Controller
public class BaseController {

    @GetMapping({"/", "index"})
    public String toIndexPage(Model model) {
        model.addAttribute("msg", "Hello Shiro");
        return "index";
    }

    @GetMapping("user/add")
    public String toAddPage() {
        return "user/add";
    }

    @GetMapping("user/update")
    public String toUpdatePage() {
        return "user/update";
    }

    @GetMapping("toLoginPage")
    public String toLoginPage() {
        return "user/login";
    }

    @PostMapping("login")
    public String login(String name, String password, Model model) {
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try {
            currentUser.login(token);
            model.addAttribute("msg", "欢迎 " + name + " 登录");
            return "index";
        } catch (UnknownAccountException uae) {
            model.addAttribute("msg", "用户名不存在!");
            return "user/login";
        } catch (IncorrectCredentialsException ice) {
            model.addAttribute("msg", "密码错误!");
            return "user/login";
        }
    }

    @ResponseBody
    @GetMapping("unAuthorized")
    public String unAuthorized() {
        return "您没有该权限!";
    }


}
