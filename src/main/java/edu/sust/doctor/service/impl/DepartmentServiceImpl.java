package edu.sust.doctor.service.impl;

import edu.sust.common.vo.Option;
import edu.sust.doctor.entity.Department;
import edu.sust.doctor.mapper.DepartmentMapper;
import edu.sust.doctor.service.IDepartmentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Option<Integer>> getDepartmentOptionsListByHospId(Integer hospId) {
        List<Department> departmentList = this.baseMapper.selectDepartmentListByHospId(hospId);
        //todo 封装成前端需要的对象
        List<Option<Integer>> optionList = new ArrayList<>();
        for (Department department : departmentList) {
            Option<Integer> op = new Option<>();
            op.setLabel(department.getDepName());
            op.setValue(department.getId());
            optionList.add(op);
        }
        return optionList;
    }
}
