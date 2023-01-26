package com.upuphone.cloudplatform.demo.business.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import com.upuphone.cloudplatform.demo.dao.entity.Demo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


/**
 * @author zhujiajun
 * @version 1.0
 * @since 2022/1/6 11:44
 */
@Mapper
public interface DemoConverter {

    DemoConverter INSTANCE = Mappers.getMapper(DemoConverter.class);

    PageDTO<DemoBo> poPage2BoPage(IPage<Demo> poPage);
}
