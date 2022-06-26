
package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.DashBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ZY
 * @createTime: 2022年06月26日 14:25:04
 * @Description: 商场首页
 */


@RestController
@RequestMapping("admin")
public class AdminController {

    @Autowired
    UserService userService;

    @Autowired
    AdminAddressService addressService;

    @Autowired
    AdminCollectService collectService;

    @Autowired
    AdminFootprintService footprintService;

    @Autowired
    AdminHistoryService historyService;

    @Autowired
    AdminFeedbackService feedbackService;

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:39:36
     * @description: admin/user/list
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/list")
    public BaseRespVo list(AdminUserListBO userListBO, BasePageInfo pageInfo) {
        AdminUserListVO userListVO = userService.queryUserList(userListBO, pageInfo);
        return BaseRespVo.ok(userListVO);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:34:53
     * @description: admin/user/detail
     * @param: id
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/detail")
    public BaseRespVo detail(Integer id) {
        UserEntity user = userService.queryById(id);
        return BaseRespVo.ok(user);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:38:23
     * @description: admin/user/update
     * @param: user
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/update")
    public BaseRespVo update(@RequestBody UserEntity user) {
        userService.updateUser(user);
        return BaseRespVo.ok(1);
    }


    /**
     * @author: 文陶
     * @createTime: 2022/06/26 21:41:04
     * @description: admin/address/list
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("address/list")
    public BaseRespVo addressList(String name, Integer userId, BasePageInfo pageInfo) {
        CommonData<MarketAddress> data = addressService.queryAddress(name, userId, pageInfo);

        return BaseRespVo.ok(data);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 21:41:33
     * @description: admin/collect/list
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("collect/list")
    public BaseRespVo collectList(Integer userId, Integer valueId, BasePageInfo pageInfo) {
        CommonData<MarketCollect> data = collectService.queryCollect(userId, valueId, pageInfo);
        return BaseRespVo.ok(data);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 21:41:54
     * @description: admin/footprint/list
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("footprint/list")
    public BaseRespVo footprintList(Integer userId, Integer goodsId, BasePageInfo pageInfo) {
        CommonData<MarketFootprint> data = footprintService.queryFootprint(userId, goodsId, pageInfo);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("history/list")
    public BaseRespVo historyList(Integer userId, String keyword, BasePageInfo pageInfo) {
        CommonData<MarketSearchHistory> data = historyService.queryHistroy(userId,keyword,pageInfo);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("feedback/list")
    public BaseRespVo feedbackList(String username,Integer id,BasePageInfo pageInfo){
        CommonData<MarketFeedback> data = feedbackService.queryFeedback(username,id,pageInfo);
        return BaseRespVo.ok(data);    }

    AdminService marketAdminService;

    @RequestMapping("dashboard")
    public BaseRespVo dashboard() {
        DashBoardVO data = marketAdminService.queryAllCount();
        return BaseRespVo.ok(data);
    }

}
