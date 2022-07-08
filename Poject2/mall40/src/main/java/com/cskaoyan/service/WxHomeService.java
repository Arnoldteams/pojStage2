package com.cskaoyan.service;

import com.cskaoyan.bean.vo.WxHomeAboutVO;
import com.cskaoyan.bean.vo.wxHomeIndex.WxHomeIndexVo;

public interface WxHomeService {
    /**
     * @description: 显示主页信息
     * @author: 很着急的 Sssd
     */
    WxHomeIndexVo homeIndex();

    /**
     * @description: 显示主页信息
     * @author: 佛系的sprinkle
     */
    WxHomeAboutVO getAbout();
}
