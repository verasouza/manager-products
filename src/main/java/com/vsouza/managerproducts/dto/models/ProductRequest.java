package com.vsouza.managerproducts.dto.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ProductRequest {

    @NotEmpty(message = "Campo nome é obrigatório")
    private String name;
    @NotEmpty(message = "Campo descrição é obrigatório")
    private String description;
    @NotNull(message = "Campo preço é obrigatório")
    private Double price;
}
