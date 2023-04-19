package edu.sust.doctor.service;

import edu.sust.doctor.entity.Hospital;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
public interface IHospitalService extends IService<Hospital> {

    String getHospitalNameById(Integer hospId);
}
