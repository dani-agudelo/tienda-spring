package com.example.pal.dto;

import java.util.Set;
import lombok.Data;

@Data
public class UpdateProductDTO {
    private String name;
    private String description;
    private Double price;
    private Set<String> categories;
}