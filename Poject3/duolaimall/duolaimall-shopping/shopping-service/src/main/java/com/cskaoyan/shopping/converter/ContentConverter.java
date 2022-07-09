package com.cskaoyan.shopping.converter;


import com.cskaoyan.shopping.dal.entitys.Panel;
import com.cskaoyan.shopping.dal.entitys.PanelContent;
import com.cskaoyan.shopping.dal.entitys.PanelContentItem;
import com.cskaoyan.shopping.dto.PanelContentDto;
import com.cskaoyan.shopping.dto.PanelContentItemDto;
import com.cskaoyan.shopping.dto.PanelDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Set;


@Mapper(componentModel = "spring")
public interface ContentConverter {

    @Mappings({})
    PanelContentDto panelContent2Dto(PanelContent panelContent);

    @Mappings({})
    PanelContentDto panelContentItem2Dto(PanelContentItem panelContentItem);

    @Mappings({})
    PanelDto panel2Dto(Panel panel);

    Set<PanelDto> panels2Dto(List<Panel> panels);

    List<PanelContentDto> panelContents2Dto(List<PanelContent> panelContents);

    List<PanelContentItemDto> panelContentItem2Dto(List<PanelContentItem> panelContentItems);

}
