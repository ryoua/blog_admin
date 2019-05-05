package com.ryoua.myblog.controller;

import com.ryoua.myblog.common.Constants;
import com.ryoua.myblog.common.Result;
import com.ryoua.myblog.common.ResultGenerator;
import com.ryoua.myblog.controller.annotation.TokenToUser;
import com.ryoua.myblog.entity.AdminUser;
import com.ryoua.myblog.service.AdminUserService;
import com.ryoua.myblog.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sun.jvm.hotspot.debugger.Page;

import java.util.Map;

/**
 * @Author ryoua Created on 2019-05-04
 */
@RestController
@RequestMapping("/users")
public class AdminUserController {
    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody AdminUser user) {
        Result result = ResultGenerator.genFailResult("登录失败");
        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword())) {
            result.setMessage("请填写登录信息！");
        }
        AdminUser loginUser = adminUserService.updateTokenAndLogin(user.getUserName(), user.getPassword());
        if (loginUser != null) {
            result = ResultGenerator.genSuccessResult(loginUser);
        }
        return result;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result list(@RequestParam Map<String, Object> params) {
        // 检查参数
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit")))
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");

        // 查询列表数据
        PageUtil pageUtil = new PageUtil(params);
        return ResultGenerator.genSuccessResult(adminUserService.getAdminUserPage(pageUtil));
    }

    // 保存
    @RequestMapping(value = "/save")
    public Result save(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser) {
        if (loginUser == null)
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录");

        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword()))
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");

        AdminUser tempUser = adminUserService.selectByUserName(user.getUserName());

        if (tempUser != null)
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "用户已存在");

        if ("admin".endsWith(user.getUserName().trim()))
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "不能添加admin用户");

        if (adminUserService.save(user) > 0)
            return ResultGenerator.genSuccessResult();
        else
            return ResultGenerator.genFailResult("添加失败");
    }

    // 修改
    @RequestMapping(value = "/updatePassword")
    public Result update(@RequestBody AdminUser user, @TokenToUser AdminUser loginUser) {
        if (loginUser == null)
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_NOT_LOGIN, "未登录");

        if (StringUtils.isEmpty(user.getUserName()) || StringUtils.isEmpty(user.getPassword()))
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "参数异常");

        AdminUser tempUser = adminUserService.selectByUserName(user.getUserName());

        if (tempUser == null)
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "无此用户");

        if ("admin".endsWith(tempUser.getUserName().trim()))
            return ResultGenerator.genErrorResult(Constants.RESULT_CODE_PARAM_ERROR, "不能修改admin用户");

        tempUser.setPassword(user.getPassword());
        if (adminUserService.updatePassword(user) > 0)
            return ResultGenerator.genSuccessResult();
        else
            return ResultGenerator.genFailResult("添加失败");
    }
}
