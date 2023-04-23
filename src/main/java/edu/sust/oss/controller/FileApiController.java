package edu.sust.oss.controller;

import edu.sust.common.vo.Result;
import edu.sust.oss.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/oss")
public class FileApiController {

    @Autowired
    private FileService fileService;

    @PostMapping("/fileUpload")
    public Result<String> fileUpload(MultipartFile file) {
        //上传文件，返回访问路径
        String url = fileService.upload(file);
        return Result.success(url,"上传成功");
    }
}
