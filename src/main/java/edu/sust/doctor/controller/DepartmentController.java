package edu.sust.doctor.controller;

import edu.sust.common.vo.Option;
import edu.sust.common.vo.Result;
import edu.sust.doctor.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
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
}
