package es.mi.microservicio.product.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import es.mi.microservicio.product.entity.ProductEntity;

public interface ProductRepository extends MongoRepository<ProductEntity, String>{

	

}
