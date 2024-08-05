package com.agendapro.eval.mapper;

import com.agendapro.eval.domain.ProductEntity;
import com.agendapro.eval.domain.request.ProductDTO;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-08-05T09:15:46-0400",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.8.jar, environment: Java 17.0.8.1 (Amazon.com Inc.)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Override
    public ProductEntity map(ProductDTO request) {
        if ( request == null ) {
            return null;
        }

        ProductEntity.ProductEntityBuilder productEntity = ProductEntity.builder();

        productEntity.name( request.getName() );
        productEntity.category( request.getCategory() );
        productEntity.price( request.getPrice() );

        return productEntity.build();
    }

    @Override
    public ProductDTO map(ProductEntity request) {
        if ( request == null ) {
            return null;
        }

        ProductDTO.ProductDTOBuilder productDTO = ProductDTO.builder();

        productDTO.id( request.getId() );
        productDTO.name( request.getName() );
        productDTO.category( request.getCategory() );
        productDTO.price( request.getPrice() );

        return productDTO.build();
    }
}
