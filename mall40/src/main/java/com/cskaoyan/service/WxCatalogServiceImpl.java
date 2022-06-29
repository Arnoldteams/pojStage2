package com.cskaoyan.service;

import com.cskaoyan.bean.AdminCategoryOne;
import com.cskaoyan.bean.MarketCategory;
import com.cskaoyan.bean.MarketCategoryChildren;
import com.cskaoyan.bean.MarketCategoryExample;
import com.cskaoyan.bean.vo.WxCatalogCurrentVO;
import com.cskaoyan.bean.vo.WxCatalogIndexVO;
import com.cskaoyan.mapper.MarketCategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.error.Mark;

import java.util.List;

/**
 * @Author: 夏一男
 * @createTime: 2022-06-28 22:54:13
 * @version:
 * @Description:
 */

@Service
public class WxCatalogServiceImpl implements WxCatalogService {

    @Autowired
    MarketCategoryMapper marketCategoryMapper;

    @Override
    public WxCatalogIndexVO showCatalogIndex() {
        MarketCategoryExample marketCategoryExample = new MarketCategoryExample();
        MarketCategoryExample.Criteria criteria = marketCategoryExample.createCriteria();
        MarketCategory marketCurrentCategory = marketCategoryMapper.selectByPrimaryKey(1005000);
        criteria.andPidEqualTo(0);
        List<MarketCategory> categoryList = marketCategoryMapper.selectByExample(marketCategoryExample);
        MarketCategoryExample.Criteria criteria1 = marketCategoryExample.createCriteria();
        criteria1.andPidEqualTo(1005000);
        List<MarketCategory> currentSubCategory = marketCategoryMapper.selectByExample(marketCategoryExample);
        WxCatalogIndexVO wxCatalogIndexVO = new WxCatalogIndexVO();
        wxCatalogIndexVO.setCurrentCategory(marketCurrentCategory);
        wxCatalogIndexVO.setCategoryList(categoryList);
        wxCatalogIndexVO.setCurrentSubCategory(currentSubCategory);

        return wxCatalogIndexVO;
    }

    /**
     * @author sprinkle
     * @since 2022/06/29 15:50
     */
    @Override
    public WxCatalogCurrentVO queryOneCatalog(String id) {
        MarketCategory category = marketCategoryMapper.selectByPrimaryKey(Integer.parseInt(id));
        List<MarketCategory> list = marketCategoryMapper.selectL2CategoryByPid(Integer.parseInt(id));
        WxCatalogCurrentVO current = new WxCatalogCurrentVO();
        current.setCurrentCategory(category);
        current.setCurrentSubCategory(list);
        return current;
    }
}
