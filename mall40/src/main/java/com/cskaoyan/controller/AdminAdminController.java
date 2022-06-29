package com.cskaoyan.controller;

import com.cskaoyan.bean.BaseRespVo;
import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.bo.AdminCreateBO;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminCreateVO;
import com.cskaoyan.bean.vo.AdminListVO;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;


/**
 * 管理员管理接口
 * @author xyg2597@163.com
 * @since 2022/06/26 15:43
 */

@RestController
@RequestMapping("admin/admin")
public class AdminAdminController {

    @Autowired
    AdminAdminService adminAdminService;

    @Autowired
    HttpSession session;

    /**
     * 获得所有管理员
     * @param info     分页信息
     * @param username 根据管理员名称
     * @return com.cskaoyan.bean.BaseRespVo 传给浏览器的信息
     * @author xyg2597@163.com
     * @since 2022/06/26 15:58
     */
    @GetMapping("list")
    public BaseRespVo adminList(BaseParam info, String username) {

        AdminListVO adminListVO = adminAdminService.adminList(info, username);

        return BaseRespVo.ok(adminListVO);

    }

    /**
     * 创建管理员
     *
     * @param adminCreateBO 浏览器提供的管理员信息
     * @return com.cskaoyan.bean.BaseRespVo 返回给浏览器管理员的信息
     * @author xyg2597@163.com
     * @since 2022/06/26 17:31
     */
    @PostMapping({"create"})
    @LogAnnotation(value = "创建管理员",successResult = "管理员创建成功")
    public BaseRespVo adminCreate(@RequestBody AdminCreateBO adminCreateBO) {

//        判断管理员名称是否符合
        if (!adminCreateBO.getUsername().matches("\\w{6,15}")) {
            session.setAttribute("log",adminCreateBO.getUsername());
            return BaseRespVo.invalidAdminUsername();
        }
//        判断密码长度,长度小于六直接返回
        if (!adminCreateBO.getPassword().matches("\\w{6,20}")) {
            session.setAttribute("log",adminCreateBO.getUsername());
            return BaseRespVo.lessPassword();
        }


//        TODO:密码加密
        String encodingPassword = adminCreateBO.getPassword();
        adminCreateBO.setPassword(encodingPassword);


        AdminCreateVO adminCreateVO = adminAdminService.adminCreate(adminCreateBO);

        if(adminCreateVO == null){
            return BaseRespVo.invalidUsername("管理员名称重复");
        }

        return BaseRespVo.ok(adminCreateVO);
    }


    @PostMapping("update")
    @LogAnnotation(value = "修改管理员信息",successResult = "管理员修改成功")
    public BaseRespVo adminUpdate(@RequestBody MarketAdmin marketAdmin) {
//        判断管理员名称是否符合
        if (!marketAdmin.getUsername().matches("\\w{6,15}")) {
            session.setAttribute("log",marketAdmin.getUsername());
            return BaseRespVo.invalidAdminUsername();
        }
//        判断密码长度,长度小于六直接返回
        if (!marketAdmin.getPassword().matches("\\w{6,20}")) {
            session.setAttribute("log",marketAdmin.getUsername());
            return BaseRespVo.lessPassword();
        }


        //        TODO:密码加密
        String encodingPassword = marketAdmin.getPassword();
        marketAdmin.setPassword(encodingPassword);


        String adminUpdate = adminAdminService.adminUpdate(marketAdmin);
        if(adminUpdate == null){
            return BaseRespVo.invalidUsername("管理员名称重复");
        }

        AdminCreateVO adminCreateVO = AdminCreateVO.setAdminUpdateVO(marketAdmin);
        return BaseRespVo.ok(adminCreateVO);
    }

    /**
     * 删除指定管理员
     * @return com.cskaoyan.bean.BaseRespVo 删除状态的信息
     * @author xyg2597@163.com
     * @since 2022/06/26 20:20
     */
    @PostMapping("delete")
    @LogAnnotation(value = "删除管理员",successResult = "管理员删除成功")
    public BaseRespVo adminDelete(@RequestBody MarketAdmin marketAdmin){

        adminAdminService.adminDelete(marketAdmin);

        return BaseRespVo.ok(null);
    }
}

















