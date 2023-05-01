package edu.sust.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.sust.common.vo.OrderData;
import edu.sust.order.entity.OrderDetails;
import edu.sust.order.entity.PatientOrder;
import edu.sust.order.entity.ShoppingCart;
import edu.sust.order.mapper.OrderDetailsMapper;
import edu.sust.order.mapper.PatientOrderMapper;
import edu.sust.order.mapper.ShoppingCartMapper;
import edu.sust.order.service.IPatientOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
@Service
public class PatientOrderServiceImpl extends ServiceImpl<PatientOrderMapper, PatientOrder> implements IPatientOrderService {

    @Resource
    private OrderDetailsMapper orderDetailsMapper;

    @Resource
    private ShoppingCartMapper shoppingCartMapper;

    @Override
    @Transactional
    public void addOrder(OrderData orderData) {
        //后端：添加订单和订单详情数据，删除相应的购物车记录，再付款
        PatientOrder order = orderData.getOrder();
        List<ShoppingCart> shopCartRecordList = orderData.getShopCartRecordList();
        //1、添加订单和订单详情数据
        //设置默认值
        order.setCreateDate(new Date());
        order.setStatus(0); //待支付
        order.setDeleted(0);
        this.baseMapper.insert(order);
        //添加订单详情记录
        for (ShoppingCart record : shopCartRecordList) {
            OrderDetails detail = new OrderDetails(
                    null,
                    order.getId(),
                    record.getProdId(),
                    null,
                    record.getPrice(),
                    record.getNum(),
                    record.getSum(),
                    0
            );
            orderDetailsMapper.insert(detail);

            //2、删除相应的购物车记录
            LambdaQueryWrapper<ShoppingCart> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ShoppingCart::getPatId, order.getPatId());
            wrapper.eq(ShoppingCart::getProdId, record.getProdId());
            shoppingCartMapper.delete(wrapper);

        }

    }
}
