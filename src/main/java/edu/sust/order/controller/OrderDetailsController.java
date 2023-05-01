package edu.sust.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.sust.common.vo.Result;
import edu.sust.drug.entity.Product;
import edu.sust.drug.service.IProductService;
import edu.sust.order.entity.OrderDetails;
import edu.sust.order.service.IOrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/orderDetails")
public class OrderDetailsController {

    @Autowired
    private IOrderDetailsService orderDetailsService;

    @Autowired
    private IProductService productService;

    @GetMapping("/{orderId}")
    public Result<List<OrderDetails>> getOrderDetailListByOrderId(@PathVariable("orderId") Integer orderId) {
        LambdaQueryWrapper<OrderDetails> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(OrderDetails::getOrderId, orderId);
        List<OrderDetails> orderDetailsList = orderDetailsService.list(wrapper);
        for (OrderDetails orderDetails : orderDetailsList) {
            Product prod = productService.getById(orderDetails.getProdId());
            orderDetails.setProd(prod);
        }
        return Result.success(orderDetailsList);
    }
}
