package edu.sust.doctor.mapper;

import edu.sust.doctor.entity.Department;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    String selectNameById(Integer departmentId);

    List<Department> selectDepartmentListByHospId(Integer hospId);
}
