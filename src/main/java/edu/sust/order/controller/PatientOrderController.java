package edu.sust.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.utils.JwtUtil;
import edu.sust.common.vo.OrderData;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Doctor;
import edu.sust.order.entity.DeliveryAddress;
import edu.sust.order.entity.PatientOrder;
import edu.sust.order.service.IDeliveryAddressService;
import edu.sust.order.service.IPatientOrderService;
import edu.sust.patient.service.IPatientService;
import edu.sust.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
@RestController
@RequestMapping("/order")
public class PatientOrderController {

    @Autowired
    private IPatientOrderService orderService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IDeliveryAddressService addressService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    public Result<?> addOrder(@RequestBody OrderData orderData,
                              @RequestHeader("X-Token") String token) {
        User user = jwtUtil.parseToken(token, User.class);
        Integer patId = patientService.getPatIdByUserId(user.getId());
        if (patId == null) {
            return Result.fail("无权限");
        }
        orderData.getOrder().setPatId(patId);
        orderService.addOrder(orderData);
        return Result.success("订单创建成功");
    }

    @GetMapping("/list")
    public Result<Map<String, Object>> getOrderList(@RequestParam(value = "orderNo", required = false) String orderNo,
                                                    @RequestParam(value = "status", required = false) Integer status,
                                                    @RequestParam("pageNo") Long pageNo,
                                                    @RequestParam("pageSize") Long pageSize,
                                                    @RequestHeader("X-Token") String token) {
        User user = jwtUtil.parseToken(token, User.class);
        Integer patId = patientService.getPatIdByUserId(user.getId());
        if (patId == null) {
            return Result.fail("无权限");
        }
        LambdaQueryWrapper<PatientOrder> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(orderNo), PatientOrder::getOrderNo, orderNo);
        if (status != null) {
            wrapper.eq(PatientOrder::getStatus, status);
        }
        wrapper.orderByDesc(PatientOrder::getId);
        Page<PatientOrder> page = new Page<>(pageNo, pageSize);
        //进行条件分页条件查询
        orderService.page(page, wrapper);
        //注入收货地址
        for (PatientOrder order : page.getRecords()) {
            DeliveryAddress address = addressService.getById(order.getAddressId());
            order.setAddress(address);
        }
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }
}
