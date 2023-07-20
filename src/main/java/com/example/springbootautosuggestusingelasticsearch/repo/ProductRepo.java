package com.example.springbootautosuggestusingelasticsearch.repo;

import com.example.springbootautosuggestusingelasticsearch.entity.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProductRepo extends ElasticsearchRepository<Product,Integer> {
}
