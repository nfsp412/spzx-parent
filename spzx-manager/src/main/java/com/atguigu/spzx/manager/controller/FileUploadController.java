package com.atguigu.spzx.manager.controller;

import com.atguigu.spzx.common.result.Result;
import com.atguigu.spzx.manager.service.FileUploadService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/admin/system")
@Tag(name = "文件相关接口")
public class FileUploadController {
    @Resource
    private FileUploadService fileUploadService;

    /**
     * 形参的multipartFile或者file
     * 这个名字不是乱写的
     * 原生html中，input标签设置type=file name默认也是file，所以这里的形参要保持一致
     * 否则就获取不到这个上传的文件对象了
     * 所以要么是@RequestParam标注value为file
     * 要么形参写file
     * @param multipartFile 默认是file这个名字
     * @return 返回图片的minio的url
     */
    @PostMapping("/fileUpload")
    @Operation(summary = "上传文件接口")
    public Result<String> fileUpload(@RequestParam(value = "file") MultipartFile multipartFile) {
        String url = fileUploadService.fileUpload(multipartFile);
        return Result.ok(url);
    }
}
