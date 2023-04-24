package edu.sust.doctor.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.DoctorRegister;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.service.IDoctorService;
import edu.sust.patient.entity.Patient;
import edu.sust.sys.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

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
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @ApiOperation("医生注册")
    @PostMapping("/register")
    public Result<?> registerDoctor(@RequestBody DoctorRegister doctorRegister) {
        doctorService.register(doctorRegister);
        return Result.success("注册成功，等待审核...");
    }
    @ApiOperation("分页条件查询医生列表")
    @GetMapping("/list")
    public Result<Map<String, Object>> getDoctorList(@RequestParam(value = "doctorName", required = false) String doctorName,
                                                     @RequestParam("pageNo") Long pageNo,
                                                     @RequestParam("pageSize") Long pageSize) {
        Map<String, Object> data = doctorService.getDoctorList(doctorName, pageNo, pageSize);
        return Result.success(data);
    }

    @ApiOperation("更新医生信息")
    @PutMapping("/update")
    public Result<?> updateDoctor(@RequestBody Doctor doctor) {
        doctor.setIncome(null);
        doctorService.updateById(doctor);
        return Result.success("修改医生成功");
    }

    @ApiOperation("根据医生ID获取患者信息")
    @GetMapping("/{id}")
    public Result<Doctor> getDoctorById(@PathVariable("id") Integer id) {
        Doctor doctor = doctorService.getById(id);
        if (doctor != null) {
            return Result.success(doctor);
        } else {
            return Result.fail("医生不存在");
        }
    }
}
