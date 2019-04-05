package com.springmvc.springmongodbweb.repositories;

import com.springmvc.springmongodbweb.models.Product;
import com.springmvc.springmongodbweb.models.Provider;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProviderRepository extends CrudRepository<Provider, String> {
    @Query("{productName:'?0'}")
    List<Provider> findByName(String name);

    @Override
    Provider findOne(String id);

    @Override
    void delete(Provider deleted);
}
