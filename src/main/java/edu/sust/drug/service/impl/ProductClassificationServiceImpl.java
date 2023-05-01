package edu.sust.drug.service.impl;

import edu.sust.drug.entity.ProductClassification;
import edu.sust.drug.mapper.ProductClassificationMapper;
import edu.sust.drug.service.IProductClassificationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-28
 */
@Service
public class ProductClassificationServiceImpl extends ServiceImpl<ProductClassificationMapper, ProductClassification> implements IProductClassificationService {

}
