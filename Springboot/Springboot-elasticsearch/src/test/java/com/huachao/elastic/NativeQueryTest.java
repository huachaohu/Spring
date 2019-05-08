package com.huachao.elastic;

import com.huachao.elastic.bean.Item;
import com.huachao.elastic.mapper.ItemRepository;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.StringTerms;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.aggregation.AggregatedPage;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NativeQueryTest {
    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ElasticsearchTemplate template;

    @Test
    public void testNativeQuery(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        // 添加基本的分词查询
        queryBuilder.withQuery(QueryBuilders.matchQuery("title" , "小米"));
        // 设置分页参数
        queryBuilder.withPageable(PageRequest.of(1,2));

        //排序
        queryBuilder.withSort(SortBuilders.fieldSort("price").order(SortOrder.DESC));

        // 执行搜索，获取结果
        Page<Item> items = itemRepository.search(queryBuilder.build());

        // 打印总条数
        System.out.println(items.getTotalElements());
        // 打印总页数
        System.out.println(items.getTotalPages());
        // 每页大小
        System.out.println(items.getSize());
        // 当前页
        System.out.println(items.getNumber());
        //items.forEach(System.out::println);
        List<Item> list = items.getContent();
        for (Item item : list) {
            System.out.println("item = " + item);
        }
    }

    @Test
    public void testAgg(){
        // 构建查询条件
        NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder();
        String aggName = "popular_brand";
        queryBuilder.addAggregation(AggregationBuilders.terms(aggName).field("brand"));
        //搜索结果
        AggregatedPage<Item> result = template.queryForPage(queryBuilder.build(),Item.class);
        //解析聚合
        Aggregations aggs = result.getAggregations();
        //获取指定名称的聚合
        StringTerms agg = aggs.get(aggName);
        //获取桶
        List<StringTerms.Bucket> buckets = agg.getBuckets();
        //遍历
        for(StringTerms.Bucket bucket : buckets){
            // 3.4、获取桶中的key，即品牌名称
            System.out.println(bucket.getKeyAsString());
            // 3.5、获取桶中的文档数量
            System.out.println(bucket.getDocCount());
        }
    }
}
