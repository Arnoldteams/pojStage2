package com.cskaoyan.controller;

import com.cskaoyan.bean.*;
import com.cskaoyan.bean.param.BaseParam;
import com.cskaoyan.bean.vo.AdminBrandVO;
import com.cskaoyan.bean.vo.AdminCategoryOneVO;
import com.cskaoyan.bean.vo.AdminCategoryVO;
import com.cskaoyan.handler.LogAnnotation;
import com.cskaoyan.service.AdminBrandService;
import com.cskaoyan.service.AdminCategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/06/27 19:40
 * @Description: 商场管理-商品类目
 */
@RestController
@RequestMapping("admin/category")
public class AdminCategoryController {
    @Autowired
    AdminCategoryService adminCategoryService ;

    /**
     * @author: 于艳帆
     * @createTime: 2022-06-27 09:09:15
     * @description: 查询category表里全部数据
     * @return: com.cskaoyan.bean.BaseRespVo<com.cskaoyan.bean.vo.AdminCategoryVO>
     */
    @GetMapping("list")
    @RequiresPermissions(value = {"admin:category:list","*"},logical = Logical.OR)
    public BaseRespVo<AdminCategoryVO> list() {
        BaseRespVo<AdminCategoryVO> resp = new BaseRespVo<>();

        AdminCategoryVO adminCategoryVO = new AdminCategoryVO();

        List<MarketCategoryChildren> list = adminCategoryService.getAllCategory();
        Integer page = 1;
        int limit = list.size();
        //分页插件 PageHelper，辅助我们做分页以及分页信息的获得
        // PageHelper.startPage(page, limit);
        // 将查询结果作为有参构造方法的形参传入，可以获得PageInfo
        // PageInfo<MarketCategory> pageInfo = new PageInfo<>(list);
        // long total = pageInfo.getTotal();
        // Integer pages = pageInfo.getPages();

        adminCategoryVO.setTotal(limit);
        adminCategoryVO.setLimit(limit);
        adminCategoryVO.setPage(page);
        adminCategoryVO.setPages(page);
        adminCategoryVO.setList(list);
        resp.setData(adminCategoryVO);

        return resp;
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 20:43:30
     * @description: 查询category表里所有pid为0的数据
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @GetMapping("l1")
    @RequiresPermissions(value = {"admin:category:l1","*"},logical = Logical.OR)
    public BaseRespVo l1() {

        BaseRespVo<AdminCategoryOneVO> resp = new BaseRespVo<>();

        AdminCategoryOneVO adminCategoryOneVO = new AdminCategoryOneVO();

        List<AdminCategoryOne> list = adminCategoryService.getL1Category();
        Integer page = 1;
        int limit = list.size();
        //分页插件 PageHelper，辅助我们做分页以及分页信息的获得
        // PageHelper.startPage(page, limit);
        // 将查询结果作为有参构造方法的形参传入，可以获得PageInfo
        // PageInfo<AdminCategoryOne> pageInfo = new PageInfo<>(list);
        // long total = pageInfo.getTotal();
        // Integer pages = pageInfo.getPages();

        adminCategoryOneVO.setTotal(limit);
        adminCategoryOneVO.setLimit(limit);
        adminCategoryOneVO.setPage(page);
        adminCategoryOneVO.setPages(page);
        adminCategoryOneVO.setList(list);
        resp.setData(adminCategoryOneVO);

        return resp;
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-27 21:15:33
     * @description: 根据MarketCategory类的成员变量创建数据库里的一条数据
     * @param: MarketCategory - [MarketCategory] MarketCategory类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("create")
    @LogAnnotation(value = "增加商品类目", comment = "来自sprinkle")
    @RequiresPermissions(value = {"admin:category:create","*"},logical = Logical.OR)
    public BaseRespVo create(@RequestBody MarketCategory marketCategory) {
        MarketCategory category = adminCategoryService.createOneCategory(marketCategory);
        return BaseRespVo.ok(category);
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-28 13:52:11
     * @description: 根据MarketCategory类的成员变量更新数据库里的一条数据
     * @param: MarketCategory - [MarketCategory] MarketCategory类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("update")
    @LogAnnotation(value = "更新商品类目", comment = "来自sprinkle")
    @RequiresPermissions(value = {"admin:category:update","*"},logical = Logical.OR)
    public BaseRespVo update(@RequestBody MarketCategory marketCategory) {
        if(marketCategory.getPid() == 0){
            return BaseRespVo.errParam();
        }
        adminCategoryService.updateOneCategory(marketCategory);
        return BaseRespVo.ok();
    }

    /**
     * @author: sprinkle
     * @createTime: 2022-06-28 14:29:36
     * @description: 根据MarketCategory类的成员变量删除数据库里的一条数据
     * @param: MarketCategory - [MarketCategory] MarketCategory类的成员变量
     * @return: com.cskaoyan.bean.BaseRespVo
     */
    @PostMapping("delete")
    @RequiresPermissions(value = {"admin:category:delete","*"},logical = Logical.OR)
    @LogAnnotation(value = "删除商品类目", comment = "来自sprinkle")
    public BaseRespVo delete(@RequestBody MarketCategory marketCategory) {
        adminCategoryService.deleteOneCategory(marketCategory);
        return BaseRespVo.ok();
    }

}
