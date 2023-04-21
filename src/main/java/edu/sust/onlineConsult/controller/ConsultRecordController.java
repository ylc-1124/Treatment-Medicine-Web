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
 *  前端控制器
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
        Map<String, Object> data = consultRecordService.getConsultRecordListByDocId(doctor.getId(),patientName, status, pageNo, pageSize);
        return Result.success(data);
    }

    @ApiOperation("修改问诊记录")
    @PutMapping("/update")
    public Result<?> updateConsultRecord(@RequestBody ConsultRecord conRec) {
        conRec.setProcessDate(new Date());
        consultRecordService.updateById(conRec);
        return Result.success("修改记录成功");
    }
}
