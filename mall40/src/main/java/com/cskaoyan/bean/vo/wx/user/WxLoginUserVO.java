package com.cskaoyan.bean.vo.wx.user;

import com.cskaoyan.bean.AdminInfoBean;
import lombok.Data;

/**
 * @author xyg2597@163.com
 * @since 2022/06/30 10:10
 */
@Data
public class WxLoginUserVO {

    private WxUserBean userInfo;
    private String token;
}
