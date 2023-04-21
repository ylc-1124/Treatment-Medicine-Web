package edu.sust.onlineConsult.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.onlineConsult.entity.ConsultRecord;
import edu.sust.onlineConsult.mapper.ConsultRecordMapper;
import edu.sust.onlineConsult.service.IConsultRecordService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.sust.patient.entity.Patient;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-21
 */
@Service
public class ConsultRecordServiceImpl extends ServiceImpl<ConsultRecordMapper, ConsultRecord> implements IConsultRecordService {

    @Override
    public Map<String, Object> getConsultRecordListByDocId(Integer docId,
                                                           String patientName,
                                                           Integer status,
                                                           Long pageNo,
                                                           Long pageSize) {
        LambdaQueryWrapper<ConsultRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(ConsultRecord::getDocId, docId);
        wrapper.eq(StringUtils.hasLength(patientName), ConsultRecord::getPatName, patientName);
        wrapper.eq(ConsultRecord::getStatus, status);
        wrapper.orderByDesc(ConsultRecord::getId);
        Page<ConsultRecord> page = new Page<>(pageNo, pageSize);
        //进行条件分页查询,结果在page对象中
        this.baseMapper.selectPage(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return data;
    }
}
