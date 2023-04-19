package edu.sust.doctor.service.impl;

import edu.sust.doctor.entity.Hospital;
import edu.sust.doctor.mapper.HospitalMapper;
import edu.sust.doctor.service.IHospitalService;
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
public class HospitalServiceImpl extends ServiceImpl<HospitalMapper, Hospital> implements IHospitalService {

    @Override
    public String getHospitalNameById(Integer hospId) {
        return this.baseMapper.selectNameById(hospId);
    }
}
