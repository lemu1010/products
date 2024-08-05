package com.agendapro.eval.controller;

import com.agendapro.eval.domain.request.ProductDTO;
import com.agendapro.eval.domain.request.UpdateRequest;
import com.agendapro.eval.domain.response.CreateResponse;
import com.agendapro.eval.service.ProductService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

  private final ProductService service;

  @PostMapping(
      value = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public CreateResponse create(@RequestBody ProductDTO request) {
    return service.save(request);
  }

  @PutMapping(
      value = "",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public CreateResponse update(@RequestBody UpdateRequest request) throws NotFoundException {
    return service.update(request);
  }

  @GetMapping(
      value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ProductDTO get(@PathVariable Integer id) throws NotFoundException {
    return service.getProduct(id);
  }

  @GetMapping(
      value = "",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProductDTO> getProducts(){
    return service.getProducts();
  }

  @GetMapping(
      value = "/by-name/{name}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ProductDTO> getByName(@PathVariable String name) throws NotFoundException {
    return service.getProductByName(name);
  }

  @DeleteMapping(
      value = "/{id}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public void delete(@PathVariable Integer id){
    service.delete(id);
  }
}
