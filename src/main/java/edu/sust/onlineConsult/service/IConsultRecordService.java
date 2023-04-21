package edu.sust.onlineConsult.service;

import edu.sust.onlineConsult.entity.ConsultRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ylc
 * @since 2023-04-21
 */
public interface IConsultRecordService extends IService<ConsultRecord> {

    Map<String, Object> getConsultRecordListByDocId(Integer docId, String patientName, Integer status, Long pageNo, Long pageSize);
}
