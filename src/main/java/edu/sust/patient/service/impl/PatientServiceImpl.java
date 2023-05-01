package edu.sust.patient.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.sust.drug.entity.Prescription;
import edu.sust.patient.entity.Patient;
import edu.sust.patient.mapper.PatientMapper;
import edu.sust.patient.service.IPatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-17
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

    @Override
    public Patient getPatientByUserId(Integer id) {
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Patient::getUserId, id);
        return this.baseMapper.selectOne(wrapper);
    }

    @Override
    public Integer getPatIdByUserId(Integer id) {
        return this.baseMapper.selectPatIdByUserId(id);
    }
}
