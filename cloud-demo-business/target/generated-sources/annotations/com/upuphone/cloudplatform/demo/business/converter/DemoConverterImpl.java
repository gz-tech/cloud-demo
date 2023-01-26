package com.upuphone.cloudplatform.demo.business.converter;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import com.upuphone.cloudplatform.demo.dao.entity.Demo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-02T14:41:15+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_312 (Azul Systems, Inc.)"
)
public class DemoConverterImpl implements DemoConverter {

    @Override
    public PageDTO<DemoBo> poPage2BoPage(IPage<Demo> poPage) {
        if ( poPage == null ) {
            return null;
        }

        PageDTO<DemoBo> pageDTO = new PageDTO<DemoBo>();

        pageDTO.setPages( poPage.getPages() );
        pageDTO.setRecords( demoListToDemoBoList( poPage.getRecords() ) );
        pageDTO.setTotal( poPage.getTotal() );
        pageDTO.setSize( poPage.getSize() );
        pageDTO.setCurrent( poPage.getCurrent() );

        return pageDTO;
    }

    protected DemoBo demoToDemoBo(Demo demo) {
        if ( demo == null ) {
            return null;
        }

        DemoBo demoBo = new DemoBo();

        demoBo.setId( demo.getId() );
        demoBo.setUrl( demo.getUrl() );
        demoBo.setDeleted( demo.isDeleted() );
        demoBo.setCreateTime( demo.getCreateTime() );
        demoBo.setUpdateTime( demo.getUpdateTime() );

        return demoBo;
    }

    protected List<DemoBo> demoListToDemoBoList(List<Demo> list) {
        if ( list == null ) {
            return null;
        }

        List<DemoBo> list1 = new ArrayList<DemoBo>( list.size() );
        for ( Demo demo : list ) {
            list1.add( demoToDemoBo( demo ) );
        }

        return list1;
    }
}
