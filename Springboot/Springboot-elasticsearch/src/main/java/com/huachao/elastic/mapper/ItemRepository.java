package com.huachao.elastic.mapper;

import com.huachao.elastic.bean.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemRepository extends ElasticsearchRepository<Item,Long> {
    //自定义方法
    //空实现，方法名需要按照规则来
    List<Item> findByPriceBetween(Double start , Double end);
}
