package edu.sust.doctor.mapper;

import edu.sust.doctor.entity.Hospital;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
public interface HospitalMapper extends BaseMapper<Hospital> {

    String selectNameById(Integer hospId);
}
