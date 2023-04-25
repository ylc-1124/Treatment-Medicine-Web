package edu.sust.doctor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.DoctorCertification;
import edu.sust.doctor.entity.Hospital;
import edu.sust.doctor.service.IHospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private IHospitalService hospitalService;

    @GetMapping("/list")
    public Result<Map<String, Object>> getHospitalList(@RequestParam(value = "hospName", required = false) String hospName,
                                                       @RequestParam("pageNo") Long pageNo,
                                                       @RequestParam("pageSize") Long pageSize) {
        LambdaQueryWrapper<Hospital> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StringUtils.hasLength(hospName), Hospital::getHospName, hospName);
        Page<Hospital> page = new Page<>(pageNo, pageSize);
        hospitalService.page(page, wrapper);
        //分隔出头衔
        for (Hospital hospital : page.getRecords()) {
            if (hospital.getTag() != null) {
                String[] tags = hospital.getTag().split(";");
                hospital.setTags(tags);
            }
        }
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

}
