package com.peng.mybatisplus.crud.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.peng.mybatisplus.crud.common.api.CommonResult;
import com.peng.mybatisplus.crud.entity.User;
import com.peng.mybatisplus.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by peng on 2020/2/18.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("list")
    @ResponseBody
    public CommonResult<List<User>> listUserByPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                   @RequestParam(value = "pageSize", defaultValue = "3") Integer pageSize,
                                                   @RequestBody User user) {
        // 模糊筛选姓名和邮箱
        IPage<User> iPage = userService.page(new Page<>(pageNum, pageSize), new QueryWrapper<User>().like("name",user.getName()).like("email",user.getEmail()));
        List<User> userList = iPage.getRecords();
        return CommonResult.success(userList);
    }

    @PostMapping("/create")
    @ResponseBody
    public CommonResult createUser(@Validated @RequestBody User user, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return CommonResult.failed(getBindingResult(bindingResult));
        }
        CommonResult commonResult;
        boolean status = userService.save(user);
        if(status) {
            commonResult = CommonResult.success(user, "创建用户成功");
        } else {
            commonResult = CommonResult.failed("创建用户失败");
        }
        return commonResult;
    }

    private String getBindingResult(BindingResult bindingResult){
        StringBuilder sb = new StringBuilder();
        if(bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().stream().forEach(e -> sb.append(e.getField() + ":" + e.getDefaultMessage() + "; "));
        }
        return sb.toString();
    }
}
