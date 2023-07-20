package com.example.springbootautosuggestusingelasticsearch.service;

import com.example.springbootautosuggestusingelasticsearch.entity.Product;




public interface ProductService{





    Product insertProduct(Product product);


    Iterable<Product> getProduct() ;
}
