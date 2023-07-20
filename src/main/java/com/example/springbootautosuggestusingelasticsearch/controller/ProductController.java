package com.example.springbootautosuggestusingelasticsearch.controller;

import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.example.springbootautosuggestusingelasticsearch.entity.Product;
import com.example.springbootautosuggestusingelasticsearch.service.ProductService;
import com.example.springbootautosuggestusingelasticsearch.service.impl.ESService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ESService esService;

    @GetMapping("/all")
    Iterable<Product> getAllProducts(){
        return productService.getProduct();
    }

    @PostMapping("/insert")
    Product insertProduct(@RequestBody Product product){
        return  productService.insertProduct(product);
    }

    @GetMapping("/autoSuggest/{partialProductName}")
    List<String> autoSuggestProductSearch(@PathVariable String partialProductName) throws IOException {
        SearchResponse<Product> searchResponse = esService.autoSuggestProduct(partialProductName);
        List<Hit<Product>> hitList  =  searchResponse.hits().hits();
        List<Product> productList = new ArrayList<>();
        for(Hit<Product> hit : hitList){
            productList.add(hit.source());
        }
        List<String> listOfProductNames = new ArrayList<>();
        for(Product product : productList){
            listOfProductNames.add(product.getName())  ;
        }
   return listOfProductNames;
    }
}


