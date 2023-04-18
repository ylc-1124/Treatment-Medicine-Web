package edu.sust.patient.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.vo.Result;
import edu.sust.patient.entity.Patient;
import edu.sust.patient.service.IPatientService;
import edu.sust.sys.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-17
 */
@RestController
@Api("患者接口列表")
@RequestMapping("/patient")
public class PatientController {
    @Autowired
    private IPatientService patientService;

    @ApiOperation("分页条件查询患者列表")
    @GetMapping("/list")
    public Result<Map<String, Object>> getPatientList(@RequestParam(value = "patientName", required = false) String patientName,
                                                      @RequestParam("pageNo") Long pageNo,
                                                      @RequestParam("pageSize") Long pageSize) {
        //这个类的好处是：写列名时不用写字符串
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(StringUtils.hasLength(patientName), Patient::getPatientName, patientName);
        wrapper.orderByDesc(Patient::getId); //按userID进行降序排序
        Page<Patient> page = new Page<>(pageNo, pageSize);
        //进行条件分页查询,结果在page对象中
        patientService.page(page, wrapper);
        Map<String, Object> data = new HashMap<>();
        data.put("rows", page.getRecords());
        data.put("total", page.getTotal());
        return Result.success(data);
    }

    @ApiOperation("更新患者信息")
    @PutMapping("/update")
    public Result<?> updatePatient(@RequestBody Patient patient) {
        patient.setAccount(null); //不修改金额
        patientService.updateById(patient);
        return Result.success("修改患者成功");
    }

    @ApiOperation("根据患者ID获取患者信息")
    @GetMapping("/{id}")
    public Result<Patient> getPatientById(@PathVariable("id") Integer id) {
        Patient patient = patientService.getById(id);
        if (patient != null) {
            return Result.success(patient);
        } else {
            return Result.fail("患者不存在");
        }
    }
}
