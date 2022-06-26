
package com.cskaoyan.controller;

import com.cskaoyan.bean.BasePageInfo;
import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketLog;
import com.cskaoyan.bean.bo.userManager.AdminUserListBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminListVO;
import com.cskaoyan.bean.vo.userManager.AdminUserListVO;
import com.cskaoyan.bean.vo.userManager.UserEntity;
import com.cskaoyan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


//package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.vo.DashBoardVO;
import com.cskaoyan.service.AdminService;
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

    AdminService marketAdminService;

    @RequestMapping("dashboard")
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
}
