package com.example.demo.entity;

import org.bson.types.Binary;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;
import java.util.Date;

@Document
public class UploadFile {

    @Id
    private String id;
    private String name; // 文件名
    private Binary content; // 文件内容
    private String contentType; // 文件类型
    private long size; // 文件大小

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public  Binary getContent(){return content;}
    public void  setContent(Binary content){this.content=content;}

    public String getContentType(){return contentType;}
    public void setContentType(String contentType){this.contentType=contentType;}

    public long getSize(){return  size;}
    public void setSize(long size){this.size=size;}

}
