package edu.sust.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import edu.sust.common.vo.Result;
import edu.sust.drug.entity.PrescriptionProduct;
import edu.sust.drug.entity.Product;
import edu.sust.drug.service.IPrescriptionProductService;
import edu.sust.drug.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/prescriptionProd")
public class PrescriptionProductController {

    @Autowired
    private IPrescriptionProductService presProdService;

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public Result<List<PrescriptionProduct>> getPresProdListByPresId(@RequestParam("presId") Integer presId) {
        LambdaQueryWrapper<PrescriptionProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PrescriptionProduct::getPresId, presId);
        List<PrescriptionProduct> presProdList = presProdService.list(wrapper);
        for (PrescriptionProduct pp : presProdList) {
            Product product = productService.getById(pp.getProdId());
            //注入属性
            pp.setProdName(product.getName());
            pp.setDosageForm(product.getDosageForm());
            pp.setApprovalNumber(product.getApprovalNumber());
        }
        return Result.success(presProdList);
    }

}
