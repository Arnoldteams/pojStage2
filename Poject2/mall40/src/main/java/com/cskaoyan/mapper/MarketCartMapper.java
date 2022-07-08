package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketCart;
import com.cskaoyan.bean.MarketCartExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface MarketCartMapper {
    long countByExample(MarketCartExample example);

    int deleteByExample(MarketCartExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketCart record);

    int insertSelective(MarketCart record);

    List<MarketCart> selectByExample(MarketCartExample example);

    MarketCart selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketCart record, @Param("example") MarketCartExample example);

    int updateByExample(@Param("record") MarketCart record, @Param("example") MarketCartExample example);

    int updateByPrimaryKeySelective(MarketCart record);

    int updateByPrimaryKey(MarketCart record);

    int selectAllNumberByUserId(@Param("userId") Integer userId);

    Integer selectIdByUserIdAndProductId(@Param("userId") Integer userId, @Param("productId") Integer productId);

    void updateCartInfo(@Param("cartId") Integer cartId, @Param("number") Short number, @Param("date") Date date);

    void updateCartGoodsChecked(@Param("userId") Integer userId, @Param("productIds") List<Integer> productIds, @Param("checked") Integer checked);

    void updateCartGoodsDeleted(@Param("userId") Integer userId, @Param("productIds") List<Integer> productIds);

    Integer selectCartGoodsCount(@Param("userId") Integer userId);

    void deleteCartInfoByUserId(@Param("userId") Integer userId);

    void updateCartGoodsDeletedByCartId(@Param("cartId") Integer cartId);
}