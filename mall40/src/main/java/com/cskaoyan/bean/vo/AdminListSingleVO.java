package com.cskaoyan.bean.vo;

import com.cskaoyan.bean.MarketAdmin;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @author xyg2597@163.com
 * @since 2022/06/26 16:40
 */
@Data
public class AdminListSingleVO {
    private Integer id;

    private String username;

    private String avatar;

    private Integer[] roleIds;

    public static AdminListSingleVO setAdminListSingleVO(MarketAdmin marketAdmin) {

        AdminListSingleVO adminListSingleVO = new AdminListSingleVO();
//            将str类型的id解析为Integer数组
        String roleIds = marketAdmin.getRoleIds();
        String strip = StringUtils.strip(roleIds, "[]");
        String[] split = strip.split(",");
        int length = split.length;
        Integer[] integers = new Integer[length];

        for (int i = 0; i < length; i++) {
            integers[i] = Integer.parseInt(split[i]);
        }


//            设置传给浏览器的值
        adminListSingleVO.setRoleIds(integers);
        adminListSingleVO.setAvatar(marketAdmin.getAvatar());
        adminListSingleVO.setId(marketAdmin.getId());
        adminListSingleVO.setUsername(marketAdmin.getUsername());
        return adminListSingleVO;
    }

}
