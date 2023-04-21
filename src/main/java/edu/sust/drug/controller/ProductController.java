package edu.sust.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.drug.entity.Drug;
import edu.sust.drug.entity.Product;
import edu.sust.drug.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getProductList(@RequestParam("prodName") String prodName,
                                                   @RequestParam("pageNo") Long pageNo,
                                                   @RequestParam("pageSize") Long pageSize) {
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(prodName), Product::getName, prodName);
        wrapper.orderByDesc(Product::getId);
        Page<Product> page = new Page<>(pageNo, pageSize);
        productService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }
}
