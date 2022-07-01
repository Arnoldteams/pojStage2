
package com.cskaoyan.controller;


import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.MarketAddress;
import com.cskaoyan.bean.MarketCollect;
import com.cskaoyan.bean.MarketFootprint;
import com.cskaoyan.bean.MarketSearchHistory;
import com.cskaoyan.bean.MarketFeedback;
import com.cskaoyan.bean.MarketLog;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.CommonData;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.*;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminListVO;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.DashBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    @RequiresPermissions(value = {"admin:user:list","*"},logical = Logical.OR)
    public BaseRespVo list(String username,String mobile,Integer id, BasePageInfo pageInfo) {
        CommonData<AdminUserListVO> data = userService.queryUserList(username,mobile,id, pageInfo);
        return BaseRespVo.ok(data);
    }

    /**
     * @author: 文陶
     * @createTime: 2022/06/26 16:34:53
     * @description: admin/user/detail
     * @param: id
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @RequestMapping("user/detail")
    @RequiresPermissions(value = {"admin:user:detail","*"},logical = Logical.OR)
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
    @RequiresPermissions(value = {"admin:user:update","*"},logical = Logical.OR)
    @LogAnnotation(value = "修改用户",successResult = "成功",unSuccessResult = "修改失败")
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
    @RequiresPermissions(value = {"admin:address:list","*"},logical = Logical.OR)
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
    @RequiresPermissions(value = {"admin:collect:list","*"},logical = Logical.OR)
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
    @RequiresPermissions(value = {"admin:footprint:list","*"},logical = Logical.OR)
    public BaseRespVo footprintList(Integer userId, Integer goodsId, BasePageInfo pageInfo) {
        CommonData<MarketFootprint> data = footprintService.queryFootprint(userId, goodsId, pageInfo);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("history/list")
    @RequiresPermissions(value = {"admin:history:list","*"},logical = Logical.OR)
    public BaseRespVo historyList(Integer userId, String keyword, BasePageInfo pageInfo) {
        CommonData<MarketSearchHistory> data = historyService.queryHistroy(userId,keyword,pageInfo);
        return BaseRespVo.ok(data);
    }

    @RequestMapping("feedback/list")
    @RequiresPermissions(value = {"admin:feedback:list","*"},logical = Logical.OR)
    public BaseRespVo feedbackList(String username,Integer id,BasePageInfo pageInfo){
        CommonData<MarketFeedback> data = feedbackService.queryFeedback(username,id,pageInfo);
        return BaseRespVo.ok(data);
    }


    @Autowired
    AdminService marketAdminService;

    /**
    * @author: ZY
    * @createTime: 2022/06/27 23:24:51
    * @description: 商城首页
    * @param:
    * @return: com.cskaoyan.bean.BaseRespVo
            */
    @RequestMapping("dashboard")
    @RequiresPermissions(value = "dashboard",logical = Logical.OR)
    public BaseRespVo dashboard() {
        DashBoardVO data = marketAdminService.queryAllCount();
        return BaseRespVo.ok(data);
    }

    /**
     * 获得所有日志信息
     * @param info 分页信息
     * @param name 根据操作管理员查询
     * @return com.cskaoyan.bean.BaseRespVo
     * @author xyg2597@163.com
     * @since 2022/06/26 20:45
     */
    @GetMapping("log/list")
    public BaseRespVo logList(BaseParam info, String name){

        List<MarketLog> marketLogList = userService.getLogList(info, name);

        AdminListVO<MarketLog> adminListVO = new AdminListVO<>();
        PageInfo<MarketLog> marketLogPageInfo = new PageInfo<>(marketLogList);

//        封装到VO中
        adminListVO.setBaseParam(marketLogPageInfo);
        adminListVO.setList(marketLogList);
        return BaseRespVo.ok(adminListVO);
    }

    @GetMapping("profile/nnotice")
    public BaseRespVo nNotice() {
        return BaseRespVo.ok(0);
    }


}
