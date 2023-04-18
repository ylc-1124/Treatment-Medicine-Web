package edu.sust.drug.service.impl;

import edu.sust.drug.entity.Drug;
import edu.sust.drug.mapper.DrugMapper;
import edu.sust.drug.service.IDrugService;
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
public class DrugServiceImpl extends ServiceImpl<DrugMapper, Drug> implements IDrugService {

}
