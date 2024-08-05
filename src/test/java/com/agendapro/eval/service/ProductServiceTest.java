package com.agendapro.eval.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.agendapro.eval.domain.ProductEntity;
import com.agendapro.eval.domain.request.ProductDTO;
import com.agendapro.eval.domain.request.UpdateRequest;
import com.agendapro.eval.domain.response.CreateResponse;
import com.agendapro.eval.mapper.ProductMapperImpl;
import com.agendapro.eval.repository.ProductRepository;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

  private static final Integer ID = 123;
  private static final String NAME = "name";
  private static final int PRICE = 321;
  private static final String CAT = "category";

  private static final String NAME_UPT = "name upt";
  private static final int PRICE_UPT = 321123;
  private static final String CAT_UPT = "category upt";

  @InjectMocks ProductService service;
  @Mock
  ProductMapperImpl mapper;
  @Mock
  ProductRepository repository;
  @Mock ProductDTO dto;
  @Mock
  UpdateRequest updt;

  @Test
  void save() {
    ProductEntity entity = ProductEntity.builder()
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();
    ProductEntity entitySaved = ProductEntity.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();

    when(mapper.map(dto)).thenReturn(entity);
    when(repository.save(entity)).thenReturn(entitySaved);

    CreateResponse result = service.save(dto);
    CreateResponse expected = CreateResponse.builder()
        .id(ID)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    verify(mapper).map(dto);
    verify(repository).save(entity);
    verifyNoMoreInteractions(mapper,repository);
  }

  @Test
  void update() throws NotFoundException {

    UpdateRequest request = UpdateRequest.builder()
        .id(ID)
        .category(CAT_UPT)
        .name(NAME_UPT)
        .price(PRICE_UPT)
        .build();
    ProductEntity entity = ProductEntity.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();

    ProductEntity entityToSave = ProductEntity.builder()
        .id(ID)
        .category(CAT_UPT)
        .name(NAME_UPT)
        .price(PRICE_UPT)
        .build();


    when(repository.findById(request.getId())).thenReturn(Optional.of(entity));
    when(repository.save(entityToSave)).thenReturn(entityToSave);

    CreateResponse result = service.update(request);
    CreateResponse expected = CreateResponse.builder()
        .id(ID)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    verify(repository).findById(ID);
    verify(repository).save(entityToSave);
    verifyNoMoreInteractions(repository);


  }

  @Test
  void getProduct() throws NotFoundException {

    ProductEntity entity = ProductEntity.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();

    ProductDTO productDto = ProductDTO.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();

    when(repository.findById(ID)).thenReturn(Optional.of(entity));
    when(mapper.map(entity)).thenReturn(productDto);

    ProductDTO result = service.getProduct(ID);

    ProductDTO expected = ProductDTO.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
    verify(repository).findById(ID);
    verify(mapper).map(entity);
    verifyNoMoreInteractions(mapper,repository);
  }

  @Test
  void getProducts() {

    ProductEntity entity = ProductEntity.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();

    ProductDTO productDto = ProductDTO.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();

    when(repository.findAll()).thenReturn(List.of(entity));
    when(mapper.map(entity)).thenReturn(productDto);

    List<ProductDTO> result = service.getProducts();

    ProductDTO expected = ProductDTO.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(List.of(expected));
    verify(repository).findAll();
    verify(mapper).map(entity);
    verifyNoMoreInteractions(mapper,repository);
  }

  @Test
  void getProductByName()  {
    ProductEntity entity = ProductEntity.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();
    ProductDTO productDto = ProductDTO.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();
    when(repository.findByName(NAME)).thenReturn(List.of(entity));
    when(mapper.map(entity)).thenReturn(productDto);

    List<ProductDTO> result = service.getProductByName(NAME);
    ProductDTO expected = ProductDTO.builder()
        .id(ID)
        .category(CAT)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(List.of(expected));
    verify(repository).findByName(NAME);
    verify(mapper).map(entity);
    verifyNoMoreInteractions(mapper,repository);
  }

  @Test
  void delete() {
    doNothing().when(repository).deleteById(ID);
    service.delete(ID);
    verify(repository).deleteById(ID);
  }
}