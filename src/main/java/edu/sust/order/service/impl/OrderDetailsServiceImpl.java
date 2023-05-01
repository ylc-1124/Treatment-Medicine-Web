package edu.sust.order.service.impl;

import edu.sust.order.entity.OrderDetails;
import edu.sust.order.mapper.OrderDetailsMapper;
import edu.sust.order.service.IOrderDetailsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
@Service
public class OrderDetailsServiceImpl extends ServiceImpl<OrderDetailsMapper, OrderDetails> implements IOrderDetailsService {

}
