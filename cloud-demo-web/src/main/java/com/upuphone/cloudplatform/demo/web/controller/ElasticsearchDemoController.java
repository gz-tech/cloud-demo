package com.upuphone.cloudplatform.demo.web.controller;

import com.upuphone.cloudplatform.demo.business.entity.EsEntity;
import com.upuphone.cloudplatform.demo.business.service.ElasticsearchService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2022/1/26 1:50 PM
 */
@Slf4j
@RestController
@Api(value = "elasticsearch demo示例", tags = "elasticsearch demo示例")
@RequestMapping(value = "/esDemo")
public class ElasticsearchDemoController {

    @Resource
    private ElasticsearchService elasticsearchService;

    @GetMapping("/createIndex")
    @ApiOperation(value = "es创建索引接口示例")
    public boolean createIndex() {
        return elasticsearchService.createIndex(EsEntity.class);
    }

    @GetMapping("/getIndex")
    @ApiOperation(value = "es获取索引接口示例")
    public Map<String, Object> getIndex(@RequestParam("index") String index) {
        return elasticsearchService.getIndex(index);
    }

    @DeleteMapping("/delIndex")
    @ApiOperation(value = "es删除index接口示例")
    public boolean delIndex(String index) {
        return elasticsearchService.delIndex(index);
    }

    @DeleteMapping("/delIndexByClass")
    @ApiOperation(value = "es删除index示例")
    public boolean delIndexByClass() {
        return elasticsearchService.delIndexByClass(EsEntity.class);
    }

    @GetMapping("/save")
    @ApiOperation(value = "es新增doc接口示例")
    public void save() {
        EsEntity entity = EsEntity.builder()
                .id(104L)
                .name("上海电视塔")
                .price(BigDecimal.valueOf(66.38))
                .imageUrl("www.abc.com")
                .desc("上海电视塔")
                .build();
        elasticsearchService.save(entity);
    }

    @GetMapping("/saveBatch")
    @ApiOperation(value = "es批量新增doc接口示例")
    public void saveBatch() {
        List<EsEntity> list = new ArrayList<>();
        EsEntity lili = EsEntity.builder().id(101L).name("新疆西藏").imageUrl("xingjiang.com").build();
        EsEntity mali = EsEntity.builder().id(102L).name("黑龙江").imageUrl("heilongjiang.com").build();
        EsEntity toto = EsEntity.builder().id(103L).name("上海").imageUrl("shanghai.com").build();
        EsEntity toto4 = EsEntity.builder().id(104L).name("上海").imageUrl("shanghai.com").build();
        EsEntity toto5 = EsEntity.builder().id(105L).name("上海").imageUrl("shanghai.com").build();
        list.add(lili);
        list.add(mali);
        list.add(toto);
        list.add(toto4);
        list.add(toto5);
        elasticsearchService.saveBatch(list);
    }

    @GetMapping("/getOneById")
    @ApiOperation(value = "es根据id获取文档接口示例")
    public EsEntity getOneById(@RequestParam("id") String id) {
        return elasticsearchService.getOneById(id, EsEntity.class);
    }

    @GetMapping("/getOneByField")
    @ApiOperation(value = "es根据field获取文档接口示例")
    public EsEntity getOneByField(@RequestParam("field") String field, @RequestParam("content") String content) {
        return elasticsearchService.getOneByField(field, content, EsEntity.class);
    }

    @DeleteMapping("/delById")
    @ApiOperation(value = "es根据id删除文档接口示例")
    public String delById(@RequestParam("id") String id,
                          @RequestParam("index") String index) {
        return elasticsearchService.delById(id, index);
    }

    @DeleteMapping("/delByQuery")
    @ApiOperation(value = "es根据查询结果批量删除文档接口示例")
    public void delByQuery() {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery("id").gte(101).lte(103))
                .build();
        elasticsearchService.delByQuery(nativeSearchQuery, "test-zyz", EsEntity.class);
    }

    @GetMapping("/getList")
    @ApiOperation(value = "es查询列表接口示例")
    public List<EsEntity> getList(String field, String content, int page, int pageSize) {
        // 匹配查找
        NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                //查询条件
                .withQuery(QueryBuilders.matchQuery(field, content))
                //分页
                .withPageable(PageRequest.of(page, pageSize))
                //排序
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                //高亮字段显示,默认斜体
                //.withHighlightFields(new HighlightBuilder.Field(field))
                //指定样式高亮
                .withHighlightBuilder(new HighlightBuilder().field(field)
                        .preTags("<span style='color:red'>")
                        .postTags("</span>"))
                .build();

        // 模糊匹配
        /*NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.fuzzyQuery(field, content).fuzziness(Fuzziness.ONE))
                .withPageable(PageRequest.of(page, pageSize))
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                .withHighlightFields(new HighlightBuilder.Field(field))
                .build();*/

        // 前缀匹配
        /*NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.prefixQuery(field, content))
                .withPageable(PageRequest.of(page, pageSize))
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                .withHighlightFields(new HighlightBuilder.Field(field))
                .build();*/

        // 区间查询 [from，to]
        /*NativeSearchQuery nativeSearchQuery = new NativeSearchQueryBuilder()
                .withQuery(QueryBuilders.rangeQuery(field).from(from).to(to))
                .withPageable(PageRequest.of(page, pageSize))
                .withSort(SortBuilders.scoreSort().order(SortOrder.DESC))
                .withHighlightFields(new HighlightBuilder.Field(field))
                .build();*/

        return elasticsearchService.getList(nativeSearchQuery, EsEntity.class);
    }


}
