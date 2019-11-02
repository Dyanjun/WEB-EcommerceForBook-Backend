package com.example.demo.controller;

import com.example.demo.entity.UploadFile;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
public class UploadFileController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostMapping("/file/uploadImage")
    @ResponseBody
    public Map<String, Object> uploadImage(@RequestParam(value = "image") MultipartFile file){
        Map<String, Object> map = new HashMap<>(6);
        if(file.isEmpty()){
            map.put("flag",false);
            map.put("msc","文件为空");
            map.put("url",null);
            return map;
        }


        String fileName = file.getOriginalFilename();
        try {
            UploadFile uploadFile = new UploadFile();
            uploadFile.setName(fileName);
            uploadFile.setContent(new Binary(file.getBytes()));
            uploadFile.setContentType(file.getContentType());
            uploadFile.setSize(file.getSize());

            UploadFile savedFile = mongoTemplate.save(uploadFile);
            String url = "http://localhost:8088/file/image/"+ savedFile.getId();

            map.put("flag",true);
            map.put("msc","图片上传成功");
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
            map.put("flag",true);
            map.put("msc","图片上传失败");
            map.put("url",null);
        }
        return map;

    }


    @GetMapping(value = "/file/image/{id}", produces = {MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE})
    @ResponseBody
    public byte[] image(@PathVariable String id){
        byte[] data = null;
        UploadFile file = mongoTemplate.findById(id, UploadFile.class);
        if(file != null){
            data = file.getContent().getData();
        }
        return data;
    }



}
