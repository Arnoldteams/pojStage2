package com.cskaoyan.service;

import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.bean.param.BaseParam;

import java.util.List;

public interface AdminStorageService {

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 16:38:43
     * @description: 获取Storage表所有数据
     * @param: baseParam - [BaseParam]
     * @return: java.util.List<com.cskaoyan.bean.MarketStorage>
     */
    List<MarketStorage> queryAllStorage(BaseParam baseParam);

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-26 17:34:46
     * @description: 根据id更新Storage表数据
     * @param: marketStorage - [MarketStorage]
     * @return: void
     */
    void updateStorageById(MarketStorage marketStorage);
}
