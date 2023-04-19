package edu.sust.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.drug.entity.Drug;
import edu.sust.drug.entity.Manufacturer;
import edu.sust.drug.entity.Product;
import edu.sust.drug.service.IManufacturerService;
import edu.sust.drug.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/manufacturer")
public class ManufacturerController {

    @Autowired
    private IManufacturerService manufacturerService;

    @Autowired
    private IProductService productService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getManuList(@RequestParam(value = "name", required = false) String name, //厂家名
                                                   @RequestParam(value = "approvalNumber", required = false) String approvalNumber, //国字准号
                                                   @RequestParam("pageNo") Long pageNo,
                                                   @RequestParam("pageSize") Long pageSize) {
        LambdaQueryWrapper<Manufacturer> wrapper = new LambdaQueryWrapper<>();
        Product product = productService.getProductByApprovalNumber(approvalNumber);
        if (product != null) {
            wrapper.eq(Manufacturer::getId, product.getManuId());
        }
        wrapper.eq(StringUtils.hasLength(name), Manufacturer::getName, name);
        wrapper.orderByDesc(Manufacturer::getId);
        Page<Manufacturer> page = new Page<>(pageNo, pageSize);
        manufacturerService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

    @PostMapping("/add")
    public Result<?> addManu(@RequestBody Manufacturer manufacturer) {
        manufacturerService.save(manufacturer);
        return Result.success("厂家添加成功");
    }

    @PutMapping("/update")
    public Result<?> updateManu(@RequestBody Manufacturer manufacturer) {
        manufacturerService.updateById(manufacturer);
        return Result.success("修改厂家成功");
    }

    @GetMapping("/{id}")
    public Result<Manufacturer> getManuById(@PathVariable("id") Integer id) {
        Manufacturer manufacturer = manufacturerService.getById(id);
        return Result.success(manufacturer);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteManu(@PathVariable("id") Integer id) {
        manufacturerService.removeById(id);
        return Result.success("删除厂家成功");
    }
}
