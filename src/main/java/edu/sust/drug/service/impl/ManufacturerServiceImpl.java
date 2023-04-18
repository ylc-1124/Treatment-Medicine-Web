package edu.sust.drug.service.impl;

import edu.sust.drug.entity.Manufacturer;
import edu.sust.drug.mapper.ManufacturerMapper;
import edu.sust.drug.service.IManufacturerService;
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
public class ManufacturerServiceImpl extends ServiceImpl<ManufacturerMapper, Manufacturer> implements IManufacturerService {

}
