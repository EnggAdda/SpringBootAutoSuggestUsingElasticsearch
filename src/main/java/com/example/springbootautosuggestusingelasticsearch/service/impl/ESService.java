package com.example.springbootautosuggestusingelasticsearch.service.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import com.example.springbootautosuggestusingelasticsearch.entity.Product;
import com.example.springbootautosuggestusingelasticsearch.util.ESUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.function.Supplier;

@Service
public class ESService {

   @Autowired
   private ElasticsearchClient  elasticsearchClient;


    public SearchResponse<Product> autoSuggestProduct(String partialProductName) throws IOException {

        Supplier<Query> supplier = ESUtil.createSupplierAutoSuggest(partialProductName);
       SearchResponse<Product> searchResponse  = elasticsearchClient
                .search(s->s.index("products").query(supplier.get()), Product.class);
        System.out.println(" elasticsearch auto suggestion query"+supplier.get().toString());
        return searchResponse;
    }

}
