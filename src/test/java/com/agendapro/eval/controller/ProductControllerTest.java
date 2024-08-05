package com.agendapro.eval.controller;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.agendapro.eval.domain.request.ProductDTO;
import com.agendapro.eval.domain.request.UpdateRequest;
import com.agendapro.eval.domain.response.CreateResponse;
import com.agendapro.eval.service.ProductService;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

  private static final Integer ID = 123;
  private static final String NAME = "name";
  private static final int PRICE = 321;
  private static final String CAT = "category";

  @InjectMocks ProductController controller;
  @Mock
  ProductService service;

  @Test
  void create() {

    ProductDTO request = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    CreateResponse response = CreateResponse.builder()
        .id(ID)
        .build();
    when(service.save(request)).thenReturn(response);
    CreateResponse result = controller.create(request);
    CreateResponse expected = CreateResponse.builder()
        .id(ID)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void update() throws NotFoundException {

    UpdateRequest request = UpdateRequest.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    CreateResponse response = CreateResponse.builder()
        .id(ID)
        .build();
    when(service.update(request)).thenReturn(response);
    CreateResponse result = controller.update(request);
    CreateResponse expected = CreateResponse.builder()
        .id(ID)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void get() throws NotFoundException {
    ProductDTO response = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();

    when(service.getProduct(ID)).thenReturn(response);
    ProductDTO result = controller.get(ID);
    ProductDTO expected = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void getProducts() {
    ProductDTO response = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();

    when(service.getProducts()).thenReturn(List.of( response));
    List<ProductDTO> result = controller.getProducts();
    ProductDTO expected = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(List.of(expected));
  }

  @Test
  void getByName() throws NotFoundException {
    ProductDTO response = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();

    when(service.getProductByName(NAME)).thenReturn(List.of( response));
    List<ProductDTO> result = controller.getByName(NAME);
    ProductDTO expected = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(List.of(expected));
  }

  @Test
  void delete() {
    doNothing().when(service).delete(ID);
    controller.delete(ID);
    verify(service).delete(ID);
  }
}