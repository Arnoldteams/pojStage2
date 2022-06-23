package com.cskaoyan.service;

import com.cskaoyan.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: raink3@126.com
 * @createTime: 2022年06月18日 15:35:11
 * @version:
 * @Description:
 */
@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    AccountMapper accountMapper;

    @Transactional
    @Override
    public void transfer(Integer fromAcc, Integer toAcc, String money) {

        Integer fromAccMoney =Integer.parseInt(accountMapper.queryById(1));
        Integer toAccMoney = Integer.parseInt(accountMapper.queryById(2));

        Integer trfMoney = Integer.parseInt(money);

        fromAccMoney -= trfMoney;
        toAccMoney += trfMoney;

        // 更新money
        accountMapper.updateById(fromAcc, fromAccMoney.toString());
        // int i = 1 / 0;
        accountMapper.updateById(toAcc, toAccMoney.toString());

    }
}
