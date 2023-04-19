package edu.sust.drug.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.sust.drug.entity.Manufacturer;
import edu.sust.drug.entity.Product;
import edu.sust.drug.mapper.ProductMapper;
import edu.sust.drug.service.IProductService;
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
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

    @Override
    public Product getProductByApprovalNumber(String approvalNumber) {
        if (approvalNumber != null) {
            LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Product::getApprovalNumber, approvalNumber);
            return this.baseMapper.selectOne(wrapper);
        }
        return null;
    }
}
