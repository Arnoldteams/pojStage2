package com.cskaoyan.service;

import java.io.File;

public interface FileService {

    void fileUpload(String fileName, File file);

    public void sendMsg(String phone,String code);
}
