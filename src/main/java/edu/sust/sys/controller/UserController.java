package edu.sust.sys.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.sys.entity.User;
import edu.sust.sys.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-03-14
 */
@Api(tags = {"用户接口列表"})
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/getUsernameByDocId")
    public Result<String> getUsernameByDocId(@RequestParam("docId") Integer docId) {
        String username = userService.getUsernameByDocId(docId);
        return Result.success(username, "获取成功");
    }

    @GetMapping("/getUsernameByPatId")
    public Result<String> getUsernameByPatId(@RequestParam("patId") Integer patId) {
        String username = userService.getUsernameByPatId(patId);
        return Result.success(username, "获取成功");
    }

    @GetMapping("/all")
    public Result<List<User>> getAllUser() {
        List<User> list = userService.list();
        return Result.success(list, "查询成功");
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody User user) {
        Map<String, Object> data = userService.login(user);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20002, "用户名或密码错误");
    }

    @GetMapping("/info")
    public Result<Map<String, Object>> getUserInfo(@RequestParam("token") String token) {
        Map<String, Object> data = userService.getUserInfo(token);
        if (data != null) {
            return Result.success(data);
        }
        return Result.fail(20003, "登录信息无效,请重新登录");
    }

    @PostMapping("/logout")
    public Result<?> logout(@RequestHeader("X-Token") String token) {
        userService.logout(token);
        return Result.success("注销成功");
    }

    /**
     * 根据多条件查询用户信息
     */
    @GetMapping("/list")
    public Result<Map<String, Object>> getUserList(@RequestParam(value = "username", required = false) String username,
                                                   @RequestParam(value = "phone", required = false) String phone,
                                                   @RequestParam("pageNo") Long pageNo,
                                                   @RequestParam("pageSize") Long pageSize) {
        //这个类的好处是：写列名时不用写字符串
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(username), User::getUsername, username);
        wrapper.eq(StringUtils.hasLength(phone), User::getPhone, phone);
        wrapper.orderByDesc(User::getId); //按userID进行降序排序
        Page<User> page = new Page<>(pageNo, pageSize);  //baomidou的Page类
        //进行条件分页查询,结果在page对象中
        userService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

    /**
     * 新增用户
     */
    @PostMapping("/add")
    public Result<?> addUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        //设置默认头像
        user.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userService.addUser(user);
        return Result.success("用户添加成功");
    }

    /**
     * 修改用户
     */
    @PutMapping("/update")
    public Result<?> updateUser(@RequestBody User user) {
        userService.updateUser(user);
        return Result.success("修改用户成功");
    }

    /**
     * 根据id查询用户
     */
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable("id") Integer id) {
        User user = userService.getUserById(id);
        return Result.success(user);
    }

    /**
     * 删除用户（逻辑删除）
     */
    @DeleteMapping("/delete/{id}")
    public Result<?> deleteUser(@PathVariable("id") Integer id) {
        userService.deleteUser(id); //逻辑删除
        return Result.success("删除用户成功");
    }

}
