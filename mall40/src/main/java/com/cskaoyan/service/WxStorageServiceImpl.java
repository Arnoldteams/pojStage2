package com.cskaoyan.service;

import com.cskaoyan.bean.MarketStorage;
import com.cskaoyan.mapper.MarketStorageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @description:
 * @author: 无敌帅的 Sssd
 * @date: 2022年06月29日 20:54
 */
@Service
@Transactional
public class WxStorageServiceImpl implements WxStorageService{

    @Autowired
    MarketStorageMapper marketStorageMapper;

    @Override
    public void addAdminStorage(MarketStorage marketStorage) {
        marketStorage.setDeleted(false);
        marketStorageMapper.insertStorage(marketStorage);
    }
}
