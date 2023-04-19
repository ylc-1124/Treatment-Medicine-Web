package edu.sust.doctor.service.impl;

import edu.sust.doctor.entity.Department;
import edu.sust.doctor.mapper.DepartmentMapper;
import edu.sust.doctor.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Override
    public String getDepartMentById(Integer departmentId) {
        return this.baseMapper.selectNameById(departmentId);
    }
}
