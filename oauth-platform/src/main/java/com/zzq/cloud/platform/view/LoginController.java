package com.zzq.cloud.platform.view;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author ZhangZiQiang
 * @Date 2021/3/4 14:26
 **/
@RestController
public class LoginController {

    @RequestMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }

}
