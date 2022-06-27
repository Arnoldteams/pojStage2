package com.cskaoyan.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 处理数据库的输入输出映射
 * @author xyg2597@163.com
 * @since 2022/06/27 16:58
 */
@MappedTypes(Integer[].class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MybatisTypeHandler implements TypeHandler<Integer[]> {

    ObjectMapper objectMapper = new ObjectMapper();

    // 输入映射过程
    // insert into market_user (id,username,password,role_ids) values (?,?,?,?)
    @Override
    public void setParameter(PreparedStatement preparedStatement, int index, Integer[] integers, JdbcType jdbcType) throws SQLException {
        String value = null;
        try {
            value = objectMapper.writeValueAsString(integers);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        // 为第几个占位符提供的值是什么
        preparedStatement.setString(index, value);
    }

    // 输出映射过程
    @Override
    public Integer[] getResult(ResultSet resultSet, String columName) throws SQLException {
        // 获得结果
        String result = resultSet.getString(columName);
        return transfer(result);
    }

    @Override
    public Integer[] getResult(ResultSet resultSet, int index) throws SQLException {
        // 获得结果
        String result = resultSet.getString(index);
        return transfer(result);
    }

    @Override
    public Integer[] getResult(CallableStatement callableStatement, int i) throws SQLException {
        // 获得结果
        String result = callableStatement.getString(i);
        return transfer(result);
    }

    private Integer[] transfer(String result) {
        Integer[] integers = new Integer[0];
        // 使用jackson将字符串转换Integer[]
        try {
            integers = objectMapper.readValue(result, Integer[].class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return integers;
    }
}

