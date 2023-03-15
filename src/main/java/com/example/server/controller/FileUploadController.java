package com.example.server.controller;


import com.example.server.common.R;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
@Slf4j
public class FileUploadController {


    @Getter
    private String headImageURL;

    @Getter
    private String resourcesURL;

    @PostMapping("/upload")
    public R<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String projectPath = System.getProperty("user.dir");
        headImageURL = projectPath+"\\src\\main\\resources\\image\\" + file.getOriginalFilename();
        resourcesURL= "image/"+file.getOriginalFilename();
//        log.info(headImageURL);
//        log.info(resourcesURL);
        Path filePath = Paths.get(headImageURL);
        Files.write(filePath, file.getBytes());
        return R.success(headImageURL);
    }


    public void upDataFileName(MultipartFile file,String projectPath) throws IOException {
        //雪花算法设置文件名防止重复
        String uuid= UUID.randomUUID().toString();


        //获取文件名
        String originalFilename = file.getOriginalFilename();
        //获取后缀
        String substring = originalFilename.substring(originalFilename.lastIndexOf("."));

        String fileName = uuid + substring;

        //判断目录是否存在
        File dir=new File(projectPath+"\\src\\main\\resources\\image\\");
        if(!dir.exists()){
            //不存在就创建
            dir.mkdirs();
        }

        file.transferTo(new File(projectPath+"\\src\\main\\resources\\image\\"+fileName));
    }
}
