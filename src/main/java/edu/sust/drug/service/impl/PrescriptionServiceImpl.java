package edu.sust.drug.service.impl;

import edu.sust.drug.entity.Prescription;
import edu.sust.drug.entity.PrescriptionProduct;
import edu.sust.drug.entity.Product;
import edu.sust.drug.mapper.PrescriptionMapper;
import edu.sust.drug.service.IPrescriptionProductService;
import edu.sust.drug.service.IPrescriptionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.sust.onlineConsult.entity.ConsultRecord;
import edu.sust.onlineConsult.service.IConsultRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ylc
 * @since 2023-04-19
 */
@Service
public class PrescriptionServiceImpl extends ServiceImpl<PrescriptionMapper, Prescription> implements IPrescriptionService {

    @Autowired
    private IPrescriptionProductService presProdService;

    @Autowired
    private IConsultRecordService consultRecordService;

    @Override
    @Transactional
    public void savePres(Prescription pres) {
        //1、保存处方
        pres.setKjDate(new Date());
        pres.setStatus(0); //设置为未完成状态
        this.baseMapper.insert(pres);

        //2、更新问诊记录中的处方ID
        ConsultRecord consultRecord = consultRecordService.getById(pres.getConsultId());
        consultRecord.setPresId(pres.getId());
        consultRecordService.updateById(consultRecord);

        //3、维护中间表
        List<Product> productList = pres.getProductList();
        for (Product product : productList) {
            PrescriptionProduct pp = new PrescriptionProduct();
            pp.setPresId(pres.getId());
            pp.setProdId(product.getId());
            pp.setMethod(product.getMethod());
            presProdService.save(pp);
        }
    }
}
