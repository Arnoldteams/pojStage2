package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketPermission;
import com.cskaoyan.bean.MarketPermissionExample;
import com.cskaoyan.bean.vo.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketPermissionMapper {
    long countByExample(MarketPermissionExample example);

    int deleteByExample(MarketPermissionExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketPermission record);

    int insertSelective(MarketPermission record);

    List<MarketPermission> selectByExample(MarketPermissionExample example);

    MarketPermission selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketPermission record, @Param("example") MarketPermissionExample example);

    int updateByExample(@Param("record") MarketPermission record, @Param("example") MarketPermissionExample example);

    int updateByPrimaryKeySelective(MarketPermission record);

    int updateByPrimaryKey(MarketPermission record);

    List<PermissionsVo> queryAllPermissions();

    List<PermissionChildVo> selectPermissionChildByPermissionKey(@Param("key") Integer key);

    List<PermissionGrandChildVo> selectGrandChildByChildKey(@Param("key") Integer key);

    List<String> selectPermissionApiById(@Param("roleId") Integer roleId);

    List<String> selectAllPermissionApiById(@Param("roleId") Integer roleId);

    List<String> selectAllPermissionApi();

    List<AdminOptionsVo> selectAllPermission();
}