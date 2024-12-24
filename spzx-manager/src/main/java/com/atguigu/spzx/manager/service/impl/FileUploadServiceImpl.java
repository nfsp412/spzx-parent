package com.atguigu.spzx.manager.service.impl;

import com.atguigu.spzx.common.minio.MinioProperties;
import com.atguigu.spzx.manager.service.FileUploadService;
import io.minio.*;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Slf4j
public class FileUploadServiceImpl implements FileUploadService {

    @Resource
    private MinioClient minioClient;

    @Resource
    private MinioProperties minioProperties;

    @Override
    public String fileUpload(MultipartFile multipartFile) {
        String objectUrl;
        try {
            //判断桶是否存在
            boolean flag = minioClient.bucketExists(
                    BucketExistsArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .build());
            if (!flag) {
                //创建桶
                minioClient.makeBucket(
                        MakeBucketArgs
                                .builder()
                                .bucket(minioProperties.getBucketName())
                                .build());
                //设置权限
                minioClient.setBucketPolicy(
                        SetBucketPolicyArgs
                                .builder()
                                .bucket(minioProperties.getBucketName())
                                .config(this.createBucketPolicyConfig(minioProperties.getBucketName()))
                                .build());

            }
            //上传文件
            String filename = this.getDistinctFileName(multipartFile);
            minioClient.putObject(
                    PutObjectArgs
                            .builder()
                            .bucket(minioProperties.getBucketName())
                            .object(filename)
                            .stream(multipartFile.getInputStream(), multipartFile.getSize(), -1)
                            .contentType(multipartFile.getContentType())
                            .build());
            //返回url
            objectUrl = this.getObjectUrl(filename);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return objectUrl;
    }

    private String createBucketPolicyConfig(String bucketName) {

        return """
                {
                  "Statement" : [ {
                    "Action" : "s3:GetObject",
                    "Effect" : "Allow",
                    "Principal" : "*",
                    "Resource" : "arn:aws:s3:::%s/*"
                  } ],
                  "Version" : "2012-10-17"
                }
                """.formatted(bucketName);
    }

    private String getDistinctFileName(MultipartFile file) {
        return new SimpleDateFormat("yyyyMMdd").format(new Date()) + "/" + UUID.randomUUID() + "-" + file.getOriginalFilename();
    }

    private String getObjectUrl(String filename) {
        return minioProperties.getEndpoint() + "/" + minioProperties.getBucketName() + "/" + filename;
    }
}
