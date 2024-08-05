package com.agendapro.eval.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.GeneratedValue;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ProductEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)  private Integer id;
  private String name;
  private String category;
  private int price;
}
