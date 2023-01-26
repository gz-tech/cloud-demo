package com.upuphone.cloudplatform.demo.business.service.impl;

import com.upuphone.cloudplatform.demo.business.entity.EsEntity;
import com.upuphone.cloudplatform.demo.business.service.ElasticsearchService;
import com.upuphone.cloudplatform.es.repository.CloudElasticsearchRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2022/1/26 1:46 PM
 */
@Service
@Slf4j
public class ElasticsearchServiceImpl implements ElasticsearchService {

    @Resource
    private CloudElasticsearchRepository elasticsearchRepository;


    @Override
    public void saveBatch(List<EsEntity> list) {
        elasticsearchRepository.saveBatch(list);
    }

    @Override
    public boolean createIndex(Class<?> cls) {
        return elasticsearchRepository.createIndex(cls);
    }

    @Override
    public Map<String, Object> getIndex(String index) {
        return elasticsearchRepository.getIndex(index);
    }

    @Override
    public void save(EsEntity esEntity) {
        elasticsearchRepository.save(esEntity);
    }

    @Override
    public boolean delIndex(String index) {
        return elasticsearchRepository.deleteIndex(index);
    }

    @Override
    public boolean delIndexByClass(Class<?> cls) {
        return elasticsearchRepository.deleteIndex(cls);
    }

    @Override
    public EsEntity getOneByField(String field, String content, Class<?> cls) {
        return elasticsearchRepository.getOneByField(field, content, EsEntity.class).getContent();
    }

    @Override
    public String delById(String id, String index) {
        return elasticsearchRepository.deleteById(index, id);
    }

    @Override
    public EsEntity getOneById(String id, Class<EsEntity> esEntityClass) {
        return elasticsearchRepository.getOneById(id, esEntityClass).getContent();
    }

    @Override
    public List getList(NativeSearchQuery nativeSearchQuery, Class<?> cls) {
        if (null != elasticsearchRepository.getList(nativeSearchQuery, cls).getSearchHits()) {
            return elasticsearchRepository.getList(nativeSearchQuery, cls).getSearchHits();
        }
        return new ArrayList();
    }

    @Override
    public void delByQuery(NativeSearchQuery nativeSearchQuery, String index, Class<?> cls) {
        elasticsearchRepository.deleteByQuery(nativeSearchQuery, index, cls);
    }

}
