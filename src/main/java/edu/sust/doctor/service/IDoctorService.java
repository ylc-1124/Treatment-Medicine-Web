package edu.sust.doctor.service;

import edu.sust.doctor.entity.Doctor;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
public interface IDoctorService extends IService<Doctor> {

    Map<String, Object> getDoctorList(String doctorName, Long pageNo, Long pageSize);
}
