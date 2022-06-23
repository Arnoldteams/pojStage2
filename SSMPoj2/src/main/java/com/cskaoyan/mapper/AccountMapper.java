package com.cskaoyan.mapper;

import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    public String queryById(@Param("id") Integer id);

    public void updateById(@Param("id") Integer id, @Param("money") String money);
}
