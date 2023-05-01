package edu.sust.drug.controller;

import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Department;
import edu.sust.drug.entity.ProductClassification;
import edu.sust.drug.service.IProductClassificationService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-28
 */
@RestController
@RequestMapping("/productClassification")
public class ProductClassificationController {

    @Autowired
    private IProductClassificationService productClassificationService;

    @ApiOperation("查询出所有产品分类")
    @GetMapping("/all")
    public Result<List<ProductClassification>> getAllProductClassifications() {
        List<ProductClassification> classificationList = productClassificationService.list();
        return Result.success(classificationList);
    }
}
