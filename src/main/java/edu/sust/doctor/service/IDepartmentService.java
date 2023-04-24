package edu.sust.doctor.service;

import edu.sust.common.vo.Option;
import edu.sust.doctor.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
public interface IDepartmentService extends IService<Department> {

    String getDepartMentById(Integer departmentId);

    List<Option<Integer>> getDepartmentOptionsListByHospId(Integer hospId);
}
