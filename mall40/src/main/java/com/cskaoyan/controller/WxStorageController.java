package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.service.AdminStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 14:11
 */
@RestController
@RequestMapping("/wx/storage")
public class WxStorageController {

    @Autowired
    AdminStorageService adminStorageService;

    // 文件本地地址
    @Value("${spring.web.resources.static-locations}")
    String staticLocation;

    // 图片映射地址
    @Value("${spring.mvc.static-path-pattern}")
    String staticPathPattern;

    // 监听端口号
    @Value("${server.port}")
    String port;

    /**
     * @param file 形参接收请求参数
     * @author: Sssd
     * @description: 图片上传，单个图片
     */
    @PostMapping("upload")
    public BaseRespVo adminStorageCreate(MultipartFile file) {

        // 创建一个 storage 存参数
        MarketStorage marketStorage = new MarketStorage();

//        // 设置一个 UUID 作为图片存储的新名字（KEY）,注意消除 uuid 中的 ‘-’, 好像存数据库会有问题
//        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//
//        // 获取 key
//        String originalFilename = file.getOriginalFilename();
//        String substring = originalFilename.substring(originalFilename.indexOf("."));
//        String newName = uuid + substring;
//
//        // 存值
//        marketStorage.setAddTime(new Date());
//        marketStorage.setUpdateTime(new Date());
//        marketStorage.setDeleted(false);
//        marketStorage.setSize(((int) file.getSize()));
//        marketStorage.setName(file.getOriginalFilename());
//        marketStorage.setType(file.getContentType());
//        marketStorage.setKey(newName);
//
//        // 拼接创建的 url
//        marketStorage.setUrl("http://localhost:" + port + staticPathPattern.replace("**", "") + newName);
//
//        // 将文件存入本地映射的静态资源文件中
//        try {
//            File newFile = new File(staticLocation.replace("file:", "") + newName);
//            // 如果父级目录不存在则创建
//            if (!newFile.getParentFile().exists()) {
//                newFile.getParentFile().mkdirs();
//            }
//            file.transferTo(newFile);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        // 调用业务层代码将图片存入数据库
//        adminStorageService.addAdminStorage(marketStorage);

        // 返回值包含主键
        return BaseRespVo.ok(marketStorage);

    }
}
