package java.com.upuphone.cloudplatform.demo.web.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import java.com.upuphone.cloudplatform.demo.api.dto.response.DemoResponse;
import java.com.upuphone.cloudplatform.demo.business.bo.DemoBo;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-19T16:39:59+0800",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 1.8.0_312 (Azul Systems, Inc.)"
)
public class WebDemoConverterImpl implements WebDemoConverter {

    @Override
    public PageDTO<DemoResponse> bo2Dto(PageDTO<DemoBo> bo) {
        if ( bo == null ) {
            return null;
        }

        PageDTO<DemoResponse> pageDTO = new PageDTO<DemoResponse>();

        pageDTO.setPages( bo.getPages() );
        pageDTO.setRecords( demoBoListToDemoResponseList( bo.getRecords() ) );
        pageDTO.setTotal( bo.getTotal() );
        pageDTO.setSize( bo.getSize() );
        pageDTO.setCurrent( bo.getCurrent() );

        return pageDTO;
    }

    protected DemoResponse demoBoToDemoResponse(DemoBo demoBo) {
        if ( demoBo == null ) {
            return null;
        }

        DemoResponse demoResponse = new DemoResponse();

        demoResponse.setId( demoBo.getId() );
        demoResponse.setUrl( demoBo.getUrl() );
        demoResponse.setDeleted( demoBo.isDeleted() );
        demoResponse.setCreateTime( demoBo.getCreateTime() );
        demoResponse.setUpdateTime( demoBo.getUpdateTime() );

        return demoResponse;
    }

    protected List<DemoResponse> demoBoListToDemoResponseList(List<DemoBo> list) {
        if ( list == null ) {
            return null;
        }

        List<DemoResponse> list1 = new ArrayList<DemoResponse>( list.size() );
        for ( DemoBo demoBo : list ) {
            list1.add( demoBoToDemoResponse( demoBo ) );
        }

        return list1;
    }
}
