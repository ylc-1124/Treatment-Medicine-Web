package edu.sust.onlineConsult.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import edu.sust.common.utils.JwtUtil;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Doctor;
import edu.sust.doctor.entity.DoctorCertification;
import edu.sust.doctor.service.IDoctorService;
import edu.sust.onlineConsult.entity.ConsultRecord;
import edu.sust.onlineConsult.service.IConsultRecordService;
import edu.sust.patient.entity.Patient;
import edu.sust.patient.service.IPatientService;
import edu.sust.sys.entity.User;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-21
 */
@RestController
@RequestMapping("/consultRecord")
public class ConsultRecordController {

    @Autowired
    private IConsultRecordService consultRecordService;

    @Autowired
    private IDoctorService doctorService;

    @Autowired
    private IPatientService patientService;

    @Autowired
    private JwtUtil jwtUtil;

    @ApiOperation("分页条件查询发送给当前医生的不同状态的问诊请求")
    @GetMapping("/list")
    public Result<Map<String, Object>> getConsultRecordList(@RequestParam(value = "patName", required = false) String patientName,
                                                            @RequestParam("status") Integer status,
                                                            @RequestParam("pageNo") Long pageNo,
                                                            @RequestParam("pageSize") Long pageSize,
                                                            @RequestHeader("X-Token") String token) {
        //获取当前用户的医生ID
        User loginUser = jwtUtil.parseToken(token, User.class);
        //获取用户ID获取对应的医生对象
        Doctor doctor = doctorService.getDoctorByUserId(loginUser.getId());
        if (doctor == null) {
            return Result.fail("无权限");
        }
        Map<String, Object> data = consultRecordService.getConsultRecordListByDocId(doctor.getId(), patientName, status, pageNo, pageSize);
        return Result.success(data);
    }

    @ApiOperation("分页条件查询当前患者发送的不同状态的问诊请求")
    @GetMapping("/list2")
    public Result<Map<String, Object>> getConsultRecordListForPat(@RequestParam(value = "docName", required = false) String docName,
                                                                  @RequestParam("status") Integer status,
                                                                  @RequestParam("pageNo") Long pageNo,
                                                                  @RequestParam("pageSize") Long pageSize,
                                                                  @RequestHeader("X-Token") String token) {
        //获取当前用户的医生ID
        User loginUser = jwtUtil.parseToken(token, User.class);
        //获取用户ID获取对应的患者对象
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Patient::getUserId, loginUser.getId());
        Patient patient = patientService.getOne(wrapper);
        if (patient == null) {
            return Result.fail("无权限");
        }
        Map<String, Object> data = consultRecordService.getConsultRecordListByPatId(patient.getId(), docName, status, pageNo, pageSize);
        return Result.success(data);
    }


    @ApiOperation("修改问诊记录")
    @PutMapping("/update")
    public Result<?> updateConsultRecord(@RequestBody ConsultRecord conRec) {
        if (conRec.getStatus() == 1) {
            conRec.setProcessDate(new Date());
        } else if (conRec.getStatus() == 2) {
            conRec.setConsultDate(new Date());
        }
        consultRecordService.updateById(conRec);
        return Result.success("修改记录成功");
    }

    @PostMapping("/add")
    public Result<?> addConsultRecord(@RequestBody ConsultRecord conRec,
                                      @RequestHeader("X-Token") String token) {
        User user = jwtUtil.parseToken(token, User.class);
        //病人创建问诊记录
        LambdaQueryWrapper<Patient> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Patient::getUserId, user.getId());
        Patient patient = patientService.getOne(wrapper);
        if (patient == null) {
            return Result.fail("无权限");
        }
        //注入初始信息
        conRec.setPatId(patient.getId());
        conRec.setPatName(patient.getPatientName());
        conRec.setStatus(0);
        conRec.setCreateDate(new Date());
        consultRecordService.save(conRec);
        return Result.success("问诊请求已发送，等待医生处理");
    }
}
