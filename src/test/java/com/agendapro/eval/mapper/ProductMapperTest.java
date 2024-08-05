package com.agendapro.eval.mapper;

import com.agendapro.eval.domain.ProductEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class ProductMapperTest {

  private static final Integer ID = 123;
  private static final String NAME = "name";
  private static final int PRICE = 321;
  @InjectMocks ProductMapper mapper;
  
  @Test
  void mapEntityToDto() {
    ProductEntity entity = ProductEntity.builder()
        .id(ID)
        .name(NAME)
        .price(PRICE)
        .build();
    mapper.map(entity);
  }

  @Test
  void testMap() {
  }
}