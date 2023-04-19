package edu.sust.doctor.service;

import edu.sust.doctor.entity.Department;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
