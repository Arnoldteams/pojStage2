package com.cskaoyan.service;

import com.cskaoyan.configuration.AliyunComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class FileServiceImpl implements FileService{

    @Autowired
    AliyunComponent aliyunComponent;


    @Override
    public void fileUpload(String fileName, File file){
        aliyunComponent.fileUpload(fileName,file);

    }

    @Override
    public void sendMsg(String phone,String code){
        aliyunComponent.sendMsg(phone,code);
    }
}
