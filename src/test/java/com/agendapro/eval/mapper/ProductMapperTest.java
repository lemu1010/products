package com.agendapro.eval.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import com.agendapro.eval.domain.ProductEntity;
import com.agendapro.eval.domain.request.ProductDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  private static final Integer ID = 123;
  private static final String NAME = "name";
  private static final int PRICE = 321;
  private static final String CAT = "category";

  @InjectMocks ProductMapperImpl mapper;
  
  @Test
  void mapEntityToDto() {
    ProductEntity entity = ProductEntity.builder()
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .category(CAT)
        .build();
    ProductDTO result = mapper.map(entity);
    ProductDTO expected = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
  }

  @Test
  void testMap() {
    ProductDTO dto = ProductDTO.builder()
        .category(CAT)
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();

    ProductEntity result = mapper.map(dto);
    ProductEntity expected = ProductEntity.builder()
        .name(NAME)
        .price(PRICE)
        .category(CAT)
        .build();
    assertThat(result).isNotNull().usingRecursiveComparison().isEqualTo(expected);
  }
}