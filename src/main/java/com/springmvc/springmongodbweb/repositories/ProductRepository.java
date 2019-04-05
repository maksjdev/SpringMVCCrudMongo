
package com.springmvc.springmongodbweb.repositories;

import com.springmvc.springmongodbweb.models.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, String> {
    @Override
    Product findOne(String id);
    
    @Override
    void delete(Product deleted);
}