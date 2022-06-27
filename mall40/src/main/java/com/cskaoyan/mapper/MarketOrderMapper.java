package com.cskaoyan.mapper;

import com.cskaoyan.bean.MarketOrder;
import com.cskaoyan.bean.MarketOrderExample;
<<<<<<< HEAD
import com.cskaoyan.bean.MarketOrderGoods;
import com.cskaoyan.bean.MarketUser;
import com.cskaoyan.bean.bo.AdminOrderShipBO;
import com.cskaoyan.bean.param.User;
=======
import com.cskaoyan.bean.vo.statForm.OrderRowsEntity;
>>>>>>> 822fe41a545c1960cdca5f381c85824ba2c97ac0
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MarketOrderMapper {
    long countByExample(MarketOrderExample example);

    int deleteByExample(MarketOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(MarketOrder record);

    int insertSelective(MarketOrder record);

    List<MarketOrder> selectByExample(MarketOrderExample example);

    MarketOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByExample(@Param("record") MarketOrder record, @Param("example") MarketOrderExample example);

    int updateByPrimaryKeySelective(MarketOrder record);

    int updateByPrimaryKey(MarketOrder record);

<<<<<<< HEAD
    void updateOrderStatus(AdminOrderShipBO adminOrderShipBO);

//    新增方法
    MarketOrder selectOrder(Integer id);

    MarketUser selectUser(Integer id);

=======
    List<OrderRowsEntity> countOrderByAddTime();
>>>>>>> 822fe41a545c1960cdca5f381c85824ba2c97ac0

}