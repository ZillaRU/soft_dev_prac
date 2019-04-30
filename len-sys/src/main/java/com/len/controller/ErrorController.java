package com.len.controller;

import io.swagger.annotations.Api;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zhuxiaomeng
 * @date 2017/12/19.
 * @email 154040976@qq.com
 * 404 403 500
 */
@Controller
@RequestMapping(value = "/error")
@Api(value = "异常", description = "异常业务类")
public class ErrorController {

    @GetMapping(value = "404")
    public String pageNotFound() {
        return "error/404";
    }

    @GetMapping(value = "403")
    public String notFound(String message, Model model) {
        if (!StringUtils.isEmpty(message)) {
            model.addAttribute("message", message);
        }
        return "error/403";
    }

}
