package edu.sust.doctor.service.impl;

import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.entity.DoctorCertification;
import edu.sust.doctor.mapper.DoctorCertificationMapper;
import edu.sust.doctor.mapper.DoctorMapper;
import edu.sust.doctor.service.IDoctorCertificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.sust.doctor.service.IDoctorService;
import edu.sust.sys.entity.User;
import edu.sust.sys.entity.UserRole;
import edu.sust.sys.mapper.UserMapper;
import edu.sust.sys.service.IUserRoleService;
import edu.sust.sys.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@Service
public class DoctorCertificationServiceImpl extends ServiceImpl<DoctorCertificationMapper, DoctorCertification> implements IDoctorCertificationService {

    @Resource
    private DoctorMapper doctorMapper;

    @Autowired
    private IUserRoleService userRoleService;

    @Resource
    private UserMapper userMapper;

    @Override
    @Transactional
    public void updateCerti(DoctorCertification certi) {
        if (certi.getStatus() == 1) {
            //给医生授权
            //1、获取医生对应的用户ID
            Doctor doctor = doctorMapper.selectById(certi.getDocId());
            Integer userId = doctor.getUserId();
            //2、进行用户角色关联，医生对应的roleId = 2
            UserRole userRole = new UserRole(null, userId, 2);
            userRoleService.save(userRole);
            //3、设置用户状态为正常
            User user = userMapper.selectById(userId);
            user.setStatus(1);  //正常
            userMapper.updateById(user);
        }
        //设置更新时间
        certi.setProcessDate(new Date());
        this.baseMapper.updateById(certi);
    }
}
