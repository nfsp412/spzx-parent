package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;

import com.atguigu.spzx.manager.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/fileUpload")
    public Result<String> fileUpload(@RequestParam MultipartFile file) {
        String url = fileUploadService.fileUpload(file);
        return Result.ok(url);
    }
}
