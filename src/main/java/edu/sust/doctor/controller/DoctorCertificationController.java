package edu.sust.doctor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.entity.DoctorCertification;
import edu.sust.doctor.service.IDoctorCertificationService;
import edu.sust.patient.entity.Patient;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
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
@RequestMapping("/certi")
public class DoctorCertificationController {

    @Autowired
    private IDoctorCertificationService certificationService;
    @GetMapping("/list")
    public Result<Map<String, Object>> getCertiList(@RequestParam(value = "applicantName",required = false) String applicantName,
                                                      @RequestParam(value = "status") Integer status,
                                                      @RequestParam("pageNo") Long pageNo,
                                                      @RequestParam("pageSize") Long pageSize) {
        LambdaQueryWrapper<DoctorCertification> wrapper = new LambdaQueryWrapper<>();

        wrapper.eq(StringUtils.hasLength(applicantName), DoctorCertification::getApplicantName, applicantName);
        wrapper.eq( DoctorCertification::getStatus, status);
        wrapper.orderByDesc(DoctorCertification::getId);
        Page<DoctorCertification> page = new Page<>(pageNo, pageSize);
        certificationService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

    @ApiOperation("修改资质证明的状态")
    @PutMapping("/updateStatus")
    public Result<?> updateStatus(@RequestBody DoctorCertification certi) {
        certificationService.updateCerti(certi);
        return Result.success("修改状态成功");
    }
}
