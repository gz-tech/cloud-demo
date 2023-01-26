package com.upuphone.cloudplatform.demo.web.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.upuphone.cloudplatform.demo.api.dto.response.DemoResponse;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/1/6 14:34
 */
@Mapper
public interface WebDemoConverter {

    WebDemoConverter INSTANCE = Mappers.getMapper(WebDemoConverter.class);

    @Mapping(target = "searchCount", ignore = true)
    @Mapping(target = "orders", ignore = true)
    @Mapping(target = "optimizeJoinOfCountSql", ignore = true)
    @Mapping(target = "optimizeCountSql", ignore = true)
    @Mapping(target = "maxLimit", ignore = true)
    @Mapping(target = "countId", ignore = true)
    PageDTO<DemoResponse> bo2Dto(PageDTO<DemoBo> bo);

}
