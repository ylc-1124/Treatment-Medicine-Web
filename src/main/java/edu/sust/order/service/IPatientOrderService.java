package edu.sust.order.service;

import edu.sust.common.vo.OrderData;
import edu.sust.order.entity.PatientOrder;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylc
 * @since 2023-04-29
 */
public interface IPatientOrderService extends IService<PatientOrder> {

    void addOrder(OrderData orderData);
}
