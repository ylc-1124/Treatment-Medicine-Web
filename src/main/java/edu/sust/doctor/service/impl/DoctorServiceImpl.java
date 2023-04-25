package edu.sust.doctor.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.DoctorRegister;
import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.entity.DoctorCertification;
import edu.sust.doctor.mapper.DoctorMapper;
import edu.sust.doctor.service.IDepartmentService;
import edu.sust.doctor.service.IDoctorCertificationService;
import edu.sust.doctor.service.IDoctorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.sust.doctor.service.IHospitalService;
import edu.sust.sys.entity.User;
import edu.sust.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
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

    @Autowired
    private IUserService userService;


    @Autowired
    private IDoctorCertificationService certificationService;

    @Override
    public Map<String, Object> getDoctorList(String doctorName, Integer depId,Integer hospId,Long pageNo, Long pageSize) {
        LambdaQueryWrapper<Doctor> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(doctorName), Doctor::getDoctorName, doctorName);
        if (depId != null) {
            wrapper.eq(Doctor::getDepartmentId, depId);
        }
        if (hospId != null) {
            wrapper.eq(Doctor::getHospId, hospId);
        }
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

    //后端逻辑：1、保存到用户表 2、保存到医生表 3、创建审核记录，通过才能真正授予医生权限
    @Override
    @Transactional
    public void register(DoctorRegister doctorRegister) {
        //1、保存用户信息（未进行用户角色关联,只有通过审核才正式授予医生的权限）
        User userInfo = doctorRegister.getUserInfo();
        userInfo.setStatus(0); //用户处于异常状态，因为医生还未通过审核
        userInfo.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        userService.save(userInfo);
        //2、保存医生信息 （暂时认为信息有效 deleted = 0，审核未通过则删除此条医生信息）
        Doctor doctorInfo = doctorRegister.getDoctorInfo();
        doctorInfo.setUserId(userInfo.getId());  //注入医生信息对应的用户ID
        this.baseMapper.insert(doctorInfo);
        //3、创建审核信息给管理员审核
        DoctorCertification certification = new DoctorCertification(null,
                doctorInfo.getDoctorName(),
                doctorInfo.getId(),
                0,
                doctorInfo.getCertification(),
                new Date(),
                null,
                0
        );
        certificationService.save(certification);
    }
}
