package com.agendapro.eval.service;

import com.agendapro.eval.domain.ProductEntity;
import com.agendapro.eval.domain.request.ProductDTO;
import com.agendapro.eval.domain.request.UpdateRequest;
import com.agendapro.eval.domain.response.CreateResponse;
import com.agendapro.eval.mapper.ProductMapper;
import com.agendapro.eval.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import lombok.AllArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductService {

  private final ProductMapper mapper;
  private final ProductRepository repository;

  public CreateResponse save(ProductDTO request){

    ProductEntity saved = repository.save(mapper.map(request));

    return getResponse(saved);
  }

  public CreateResponse update(UpdateRequest request) throws NotFoundException {
    Optional<ProductEntity> entityOpt = repository.findById(request.getId());
    if (entityOpt.isPresent()){
      ProductEntity entity = entityOpt.get();
      entity.setCategory(request.getCategory());
      entity.setName(request.getName());
      entity.setPrice(request.getPrice());
      return getResponse(repository.save(entity));
    }
    throw new NotFoundException();
  }

  private CreateResponse getResponse(ProductEntity entity){
    return CreateResponse.builder().id(entity.getId()).build();
  }

  public ProductDTO getProduct(Integer id) throws NotFoundException {
    Optional<ProductEntity> byId = repository.findById(id);
    if(byId.isPresent()){
      return mapper.map(byId.get());
    }
    throw new NotFoundException();
  }

  public List<ProductDTO> getProducts() {

    return StreamSupport.stream(repository.findAll()
            .spliterator(), false)
        .map(mapper::map)
        .toList();
  }

  public List<ProductDTO> getProductByName(String name) {
    return repository.findByName(name)
            .stream()
        .map(mapper::map)
        .toList();
  }

  public void delete(Integer id) {
    repository.deleteById(id);
  }
}
