package com.example.pal.dto;

import java.util.Set;
import lombok.Data;

@Data
public class CreateProductDTO {
  private String name;
  private String description;
  private Double price;
  private String[] categories;

}