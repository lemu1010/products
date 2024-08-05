package com.agendapro.eval.repository;

import com.agendapro.eval.domain.ProductEntity;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<ProductEntity, Integer> {
  List<ProductEntity> findByName(String name);

}
