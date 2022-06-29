package com.cskaoyan.service;

import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.WxFootprintVO;

public interface WxFootprintService {

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-29 15:18:20
     * @description: 根据goodsId 连接goods表和footprint，根据userId查询
     * @param: baseParam - [BaseParam]
     * @return: com.cskaoyan.bean.param.CommonData<com.cskaoyan.bean.vo.WxFootprintVO>
     */
    CommonData<WxFootprintVO> queryAllFootprint(BaseParam baseParam);
}
