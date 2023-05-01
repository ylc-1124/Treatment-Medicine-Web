package edu.sust.patient.service;

import edu.sust.patient.entity.Patient;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylc
 * @since 2023-04-17
 */
public interface IPatientService extends IService<Patient> {

    Patient getPatientByUserId(Integer id);

    Integer getPatIdByUserId(Integer id);
}
