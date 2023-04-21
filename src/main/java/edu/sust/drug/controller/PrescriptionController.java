package edu.sust.drug.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.utils.JwtUtil;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.entity.DoctorCertification;
import edu.sust.doctor.service.IDoctorService;
import edu.sust.drug.entity.Manufacturer;
import edu.sust.drug.entity.Prescription;
import edu.sust.drug.entity.Product;
import edu.sust.drug.service.IPrescriptionService;
import edu.sust.patient.entity.Patient;
import edu.sust.patient.service.IPatientService;
import edu.sust.sys.entity.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-19
 */
@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

    @Autowired
    private IPrescriptionService prescriptionService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation("查询当前患者的所有处方")
    @GetMapping("/list")
    public Result<Map<String, Object>> getPresList(@RequestHeader("X-Token") String token,
                                                   @RequestParam(value = "startDate" ,required = false) String startDate,
                                                   @RequestParam(value = "endDate",required = false) String endDate,
                                                   @RequestParam(value = "status",required = false) Integer status,
                                                   @RequestParam("pageNo") Long pageNo,
                                                   @RequestParam("pageSize") Long pageSize) {
        User loginUser = jwtUtil.parseToken(token, User.class);
        //判断当前用户是患者
        Patient patient = patientService.getPatientByUserId(loginUser.getId());
        if (patient == null) {
            return Result.fail("无权限");
        }
        //添加查询条件
        LambdaQueryWrapper<Prescription> wrapper = new LambdaQueryWrapper<>();
        wrapper.ge(StringUtils.hasLength(startDate), Prescription::getKjDate, startDate)
                .lt(StringUtils.hasLength(endDate), Prescription::getKjDate, endDate);
        wrapper.eq(Prescription::getPatId, patient.getId());
        wrapper.eq(status != null, Prescription::getStatus, status);
        wrapper.orderByDesc(Prescription::getId);
        Page<Prescription> page = new Page<>(pageNo, pageSize);
        prescriptionService.page(page, wrapper);
        List<Prescription> prescriptionList = page.getRecords();
        for (Prescription prescription : prescriptionList) {
            //注入医生名
            Doctor doctor = doctorService.getById(prescription.getDocId());
            prescription.setDocName(doctor.getDoctorName());
        }
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

    @ApiOperation("修改处方状态为已完成")
    @PutMapping("/update")
    public Result<?> updatePres(@RequestBody Prescription pres) {
        prescriptionService.updateById(pres);
        return Result.success("修改处方成功");
    }

    @ApiOperation("处方开具")
    @PostMapping("/add")
    public Result<Integer> addPres(@RequestBody Prescription pres) {
        prescriptionService.savePres(pres);
        return Result.success(pres.getId(), "处方开具成功");
    }
}
