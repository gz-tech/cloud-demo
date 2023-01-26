package com.upuphone.cloudplatform.demo.business.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhangyz
 * @version 1.0
 * @since 2022/2/7 11:13 AM
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "test-zyz", shards = 2, replicas = 1, createIndex = false)
public class EsEntity implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    private Long id;

    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String name;

    @Field(type = FieldType.Double)
    private BigDecimal price;

    @Field(type = FieldType.Keyword)
    private String imageUrl;

    @Field(type = FieldType.Text)
    private String desc;
}
