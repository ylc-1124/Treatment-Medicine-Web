package edu.sust.doctor.controller;

import edu.sust.common.vo.Option;
import edu.sust.common.vo.Result;
import edu.sust.doctor.entity.Department;
import edu.sust.doctor.service.IDepartmentService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author ylc
 * @since 2023-04-18
 */
@RestController
@RequestMapping("/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @GetMapping("/options")
    public Result<List<Option<Integer>>> getDepartmentOptionsByHospId(@RequestParam("hospId") Integer hospId) {
        List<Option<Integer>> optionList = departmentService.getDepartmentOptionsListByHospId(hospId);
        return Result.success(optionList);
    }

    @ApiOperation("查询出所有科室")
    @GetMapping("/all")
    public Result<List<Department>> getALLDepartments() {
        List<Department> departmentList = departmentService.list();
        return Result.success(departmentList);
    }

    @ApiOperation("查询出医院所有科室")
    @GetMapping("/{hospId}")
    public Result<List<Department>> getDepartmentListByHospId(@PathVariable("hospId") Integer hospId) {
        List<Department> departmentList = departmentService.getDepartmentListByHospId(hospId);
        return Result.success(departmentList);
    }
}
