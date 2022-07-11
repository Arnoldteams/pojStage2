package com.cskaoyan.shopping.service.impl;

import com.cskaoyan.mall.constant.ShoppingRetCode;
import com.cskaoyan.shopping.converter.ContentConverter;
import com.cskaoyan.shopping.dal.entitys.PanelContent;
import com.cskaoyan.shopping.dal.persistence.PanelContentMapper;
import com.cskaoyan.shopping.dto.NavListResponse;
import com.cskaoyan.shopping.dto.PanelContentDto;
import com.cskaoyan.shopping.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author sprinkle
 * @since 2022/07/09 16:47
 */
@Service
public class IContentServiceImpl implements IContentService {

    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ContentConverter contentConverter;

    @Override
    public NavListResponse queryNavList() {

        NavListResponse response = new NavListResponse();

        try {

            Example example = new Example(PanelContent.class);
            example.createCriteria().
                    orEqualTo("panelId",0);

            List<PanelContent> panelContents = panelContentMapper.selectByExample(example);
            List<PanelContentDto> panelContentDtos = contentConverter.panelContents2Dto(panelContents);

            //执行成功
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
            response.setPannelContentDtos(panelContentDtos);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }
}
