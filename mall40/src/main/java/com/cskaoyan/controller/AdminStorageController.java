package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketKeyword;
import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminStorageVO;
import com.cskaoyan.service.AdminStorageService;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * @Author: 于艳帆
 * @createTime: 2022年06月26日 16:01:20
 * @version: v0.1
 * @Description: 系统管理-对象存储业务（CRUD）
 */
@RestController
@RequestMapping("admin/storage")
public class AdminStorageController {

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
     * @author: Sssd
     * @param file 形参接收请求参数
     * @description: 图片上传，单个图片
     */
    @PostMapping("create")
    public BaseRespVo adminStorageCreate(MultipartFile file) {
        // 创建一个 storage 存参数
        MarketStorage marketStorage = new MarketStorage();

        // 设置一个 UUID 作为图片存储的新名字（KEY）,注意消除 uuid 中的 ‘-’, 好像存数据库会有问题
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        // 获取 key
        String originalFilename = file.getOriginalFilename();
        String substring = originalFilename.substring(originalFilename.indexOf("."));
        String newName = uuid + substring;

        // 存值
        marketStorage.setAddTime(new Date());
        marketStorage.setUpdateTime(new Date());
        marketStorage.setDeleted(false);
        marketStorage.setSize(((int) file.getSize()));
        marketStorage.setName(file.getOriginalFilename());
        marketStorage.setType(file.getContentType());
        marketStorage.setKey(newName);

        // 拼接创建的 url
        marketStorage.setUrl("http://localhost:" + port + staticPathPattern.replace("**", "") + newName);

        // 将文件存入本地映射的静态资源文件中
        try {
            file.transferTo(new File(staticLocation.replace("file:", "") + newName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 调用业务层代码将图片存入数据库
        adminStorageService.addAdminStorage(marketStorage);

        // 返回值包含主键
        return BaseRespVo.ok(marketStorage);

    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 17:30:59
     * @description: 查询storage所有列
     * @param: baseParam - [BaseParam]
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.vo.AdminStorageVO>
     */
    @GetMapping("list")
    public BaseRespVo<AdminStorageVO> list(BaseParam baseParam,String key,String name) {
        BaseRespVo<AdminStorageVO> resp = new BaseRespVo<>();
        AdminStorageVO adminStorageVO = new AdminStorageVO();

        List<MarketStorage> list = adminStorageService.queryAllStorage(baseParam,key,name);

        Integer limit = baseParam.getLimit();
        Integer pages = list.size() / limit + 1;

        adminStorageVO.setPage(baseParam.getPage());
        adminStorageVO.setLimit(limit);
        adminStorageVO.setTotal(list.size());
        adminStorageVO.setPages(pages);
        adminStorageVO.setList(list);

        resp.setData(adminStorageVO);
        return resp;
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 19:26:02
     * @description: 根据id更新Storage表name
     * @param: marketStorage - [MarketStorage]
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.MarketStorage>
     */
    @PostMapping("update")
    public BaseRespVo<MarketStorage> update(@RequestBody MarketStorage marketStorage) {
        BaseRespVo<MarketStorage> resp = new BaseRespVo<>();

        marketStorage.setUpdateTime(new Date());
        adminStorageService.updateStorageById(marketStorage);
        resp.setData(marketStorage);

        return resp;
    }

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 19:50:34
     * @description: 根据id删除Storage表中数据
     * @param: marketStorage - [MarketStorage]
     * @return: com.cskaoyan.bean.BaseRespVo<java.lang.String>
     */
    @PostMapping("delete")
    public BaseRespVo<String> delete(@RequestBody MarketStorage marketStorage) {
        BaseRespVo<String> resp = new BaseRespVo<>();

        adminStorageService.deleteKeywordById(marketStorage);

        return resp;
    }

}
