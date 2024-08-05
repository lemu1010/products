package com.agendapro.eval.mapper;


import com.agendapro.eval.domain.ProductEntity;
import com.agendapro.eval.domain.request.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface ProductMapper {


  @Mapping(target = "id", ignore = true)
  ProductEntity map(ProductDTO request);

  ProductDTO map(ProductEntity request);
}
