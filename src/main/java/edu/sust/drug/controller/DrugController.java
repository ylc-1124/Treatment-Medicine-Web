package edu.sust.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.drug.entity.Drug;
import edu.sust.drug.service.IDrugService;
import edu.sust.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/drug")
public class DrugController {

    @Autowired
    private IDrugService drugService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getDrugList(@RequestParam(value = "genericName", required = false) String genericName,
                                                   @RequestParam(value = "classification", required = false)
                                                   Integer classification, //0-非处方药 1-处方药
                                                   @RequestParam("pageNo") Long pageNo,
                                                   @RequestParam("pageSize") Long pageSize) {
        LambdaQueryWrapper<Drug> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(genericName), Drug::getGenericName, genericName);
        if (classification != null) {
            wrapper.eq(Drug::getClassification, classification);
        }
        wrapper.orderByDesc(Drug::getId);
        Page<Drug> page = new Page<>(pageNo, pageSize);
        //进行条件分页查询,结果在page对象中
        drugService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

    @PostMapping("/add")
    public Result<?> addDrug(@RequestBody Drug drug) {
        drugService.save(drug);
        return Result.success("药品添加成功");
    }

    @PutMapping("/update")
    public Result<?> updateDrug(@RequestBody Drug drug) {
        drugService.updateById(drug);
        return Result.success("修改药品成功");
    }

    @GetMapping("/{id}")
    public Result<Drug> getDrugById(@PathVariable("id") Integer id) {
        Drug drug = drugService.getById(id);
        return Result.success(drug);
    }

    @DeleteMapping("/delete/{id}")
    public Result<?> deleteDrug(@PathVariable("id") Integer id) {
        drugService.removeById(id);
        return Result.success("删除药品成功");
    }
}
