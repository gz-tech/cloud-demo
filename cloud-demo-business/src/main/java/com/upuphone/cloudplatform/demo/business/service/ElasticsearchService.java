package com.upuphone.cloudplatform.demo.business.service;

import com.upuphone.cloudplatform.demo.business.entity.EsEntity;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2022/1/26 13:43
 */
public interface ElasticsearchService {

    boolean createIndex(Class<?> cls);

    Map<String, Object> getIndex(String index);

    void save(EsEntity esEntity);

    void saveBatch(List<EsEntity> list);

    boolean delIndex(String index);

    boolean delIndexByClass(Class<?> cls);

    EsEntity getOneByField(String field, String content, Class<?> cls);

    String delById(String id, String index);

    EsEntity getOneById(String id, Class<EsEntity> esEntityClass);

    List<EsEntity> getList(NativeSearchQuery nativeSearchQuery, Class<?> cls);

    void delByQuery(NativeSearchQuery nativeSearchQuery, String index, Class<?> cls);
}
