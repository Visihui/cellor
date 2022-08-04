package com.demo.code.common.utils;

import cn.hutool.core.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public  class FileUtils {
    private static final int MAX_POST_SIZE = 10 * 1024 * 1024;


//    @Value("${ng.address}")
//    private String ngAddress;
//    public String getUrlPreFix() {
//        return ngAddress + ":" + filePreviewPort + File.separator;
//    }

    /**
     * 文件上传
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static String uploadOne(MultipartFile multipartFile,String dir) throws IOException {
        // 参数检验
        if (multipartFile == null) {
            throw new GlobalException(500, "文件不能为空");
        }
        // 文件限制10M
        long size = multipartFile.getSize();
        if (size > MAX_POST_SIZE) {
            throw new GlobalException(500, "length exceeds limit of 10M");
        }
        String folder = dir + File.separator;
        if (!FileUtil.exist(folder)) {
            FileUtil.mkdir(folder);
        }
        String fileName = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddhhmmssSSS")) + multipartFile.getOriginalFilename();
        String path = folder + fileName;
        File file = new File(path);
        if (FileUtil.exist(file)) {
            throw new GlobalException("文件已存在");
        }
        File file1 = FileUtil.writeBytes(multipartFile.getBytes(), path);
        if (file1.length() < 0) {
            throw new GlobalException("文件上传失败");
        }
//        return fileName;
        return  path;
    }
}
