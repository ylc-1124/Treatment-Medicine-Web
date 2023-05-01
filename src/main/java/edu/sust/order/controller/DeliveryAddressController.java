package edu.sust.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.sust.common.utils.JwtUtil;
import edu.sust.common.vo.Result;
import edu.sust.drug.entity.Product;
import edu.sust.order.entity.DeliveryAddress;
import edu.sust.order.entity.ShoppingCart;
import edu.sust.order.service.IDeliveryAddressService;
import edu.sust.patient.entity.Patient;
import edu.sust.patient.service.IPatientService;
import edu.sust.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
@RestController
@RequestMapping("/address")
public class DeliveryAddressController {

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IDeliveryAddressService addressService;

    @GetMapping("/all")
    public Result<List<DeliveryAddress>> getDeliveryAddressList(@RequestHeader("X-Token") String token) {
        //判断是否是患者
        User user = jwtUtil.parseToken(token, User.class);
        Integer patId = patientService.getPatIdByUserId(user.getId());
        if (patId == null) {
            return Result.fail("无权限");
        }
        LambdaQueryWrapper<DeliveryAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(DeliveryAddress::getPatId, patId);
        List<DeliveryAddress> addressList = addressService.list(wrapper);
        return Result.success(addressList);
    }
}
