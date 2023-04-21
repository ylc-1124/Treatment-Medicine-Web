package edu.sust.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.mapper.DoctorMapper;
import edu.sust.doctor.service.IDepartmentService;
import edu.sust.doctor.service.IDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.sust.doctor.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@Service
public class DoctorServiceImpl extends ServiceImpl<DoctorMapper, Doctor> implements IDoctorService {

    @Autowired
    private IHospitalService hospitalService;

    @Autowired
    private IDepartmentService departmentService;

    @Override
    public Map<String, Object> getDoctorList(String doctorName, Long pageNo, Long pageSize) {
        LambdaQueryWrapper<Doctor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(doctorName), Doctor::getDoctorName, doctorName);
        wrapper.orderByDesc(Doctor::getId);
        Page<Doctor> page = new Page<>(pageNo, pageSize);
        this.baseMapper.selectPage(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        for (Doctor doctor : page.getRecords()) {
            String hospName = hospitalService.getHospitalNameById(doctor.getHospId());
            doctor.setHospitalName(hospName);
            String departMentName = departmentService.getDepartMentById(doctor.getDepartmentId());
            doctor.setDepartmentName(departMentName);
        }
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;
    }

    @Override
    public Doctor getDoctorByUserId(Integer userId) {
        LambdaQueryWrapper<Doctor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Doctor::getUserId, userId);
        return this.baseMapper.selectOne(wrapper);
    }
}
