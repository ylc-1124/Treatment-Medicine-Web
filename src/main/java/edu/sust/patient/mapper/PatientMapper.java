package edu.sust.patient.mapper;

import edu.sust.patient.entity.Patient;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ylc
 * @since 2023-04-17
 */
public interface PatientMapper extends BaseMapper<Patient> {

    Integer selectPatIdByUserId(Integer userId);
}
