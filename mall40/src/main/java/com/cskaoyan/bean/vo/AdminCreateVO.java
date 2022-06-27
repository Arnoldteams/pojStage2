package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketAdmin;
import com.cskaoyan.bean.bo.AdminCreateBO;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * 创建管理员完成后的信息回显
 * @author xyg2597@163.com
 * @since 2022/06/26 17:23
 */
@Data
public class AdminCreateVO {
    private Integer id;
    private String username;
    private String password;
    private String avatar;
    private Integer[] roleIds;
    private Date addTime;
    private Date updateTime;

////    根据查询结果设置信息
//    public static AdminCreateVO setAdminCreateVO(AdminCreateBO adminCreateBO, Date date){
//        AdminCreateVO adminCreateVO = new AdminCreateVO();
//        adminCreateVO.setId(adminCreateBO.getId());
//        adminCreateVO.setUsername(adminCreateBO.getUsername());
//        adminCreateVO.setPassword(adminCreateBO.getPassword());
//        adminCreateVO.setAvatar(adminCreateBO.getAvatar());
//        adminCreateVO.setAddTime(date);
//        adminCreateVO.setUpdateTime(date);
//
//        adminCreateVO.setRoleIds(adminCreateBO.getRoleIds());
//
//        return adminCreateVO;
//    }


//    根据查询结果设置信息
    public static AdminCreateVO setAdminUpdateVO(MarketAdmin selectMarketAdmin) {
        AdminCreateVO adminCreateVO = new AdminCreateVO();
        adminCreateVO.setId(selectMarketAdmin.getId());
        adminCreateVO.setUsername(selectMarketAdmin.getUsername());
        adminCreateVO.setPassword(selectMarketAdmin.getPassword());
        adminCreateVO.setAvatar(selectMarketAdmin.getAvatar());
        adminCreateVO.setAddTime(selectMarketAdmin.getAddTime());
        adminCreateVO.setUpdateTime(selectMarketAdmin.getUpdateTime());

        String roleIds = selectMarketAdmin.getRoleIds();
        String[] split = StringUtils.strip(roleIds, "[]").split(",");
        int length = split.length;
        Integer[] integers = new Integer[length];
        for (int i = 0; i < length; i++) {
            integers[i] = Integer.parseInt(split[i]);
        }


        adminCreateVO.setRoleIds(integers);

        return adminCreateVO;
    }
}
