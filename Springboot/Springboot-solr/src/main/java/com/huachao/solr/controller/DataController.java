package com.huachao.solr.controller;

import com.alibaba.fastjson.JSON;
import com.huachao.solr.pojo.TbItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SolrDataQuery;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/data")
public class DataController {

    //@Autowired
    //private SolrClient solrClient;
    @Autowired
    private SolrTemplate solrTemplate;

    @RequestMapping("/import")
    public String startImport(){
        System.out.println(solrTemplate);
        List<TbItem> tbItems = new ArrayList<TbItem>();
        for(int i=0 ; i<5 ; i++){
            TbItem item = new TbItem();
            item.setId(1l+i);
            item.setTitle("iPhone x金色 "+i);
            item.setCategory("手机类");
            item.setBrand("apple");
            item.setSeller("苹果旗舰店"+i);
            item.setGoodsId(1l);
            item.setPrice(new BigDecimal(1000.01).doubleValue());

            String json = "{\"机身内存\":\"16G\",\"网络\":\"联通3G\"}";
            Map<String, String> map = JSON.parseObject(json, Map.class);
            item.setSpecMap(map);

            tbItems.add(item);
        }
        solrTemplate.saveBeans("collection1" , tbItems);
        solrTemplate.commit("collection1");
        return "导入成功";
    }

    @RequestMapping("/deleteAll")
    public String startDelete(){
        SolrDataQuery query = new SimpleQuery("*:*");
        solrTemplate.delete("collection1",query);

        solrTemplate.commit("collection1");
        return "清空成功";
    }
}
