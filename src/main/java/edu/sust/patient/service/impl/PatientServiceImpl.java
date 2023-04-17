package edu.sust.patient.service.impl;

import edu.sust.patient.entity.Patient;
import edu.sust.patient.mapper.PatientMapper;
import edu.sust.patient.service.IPatientService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-17
 */
@Service
public class PatientServiceImpl extends ServiceImpl<PatientMapper, Patient> implements IPatientService {

}
