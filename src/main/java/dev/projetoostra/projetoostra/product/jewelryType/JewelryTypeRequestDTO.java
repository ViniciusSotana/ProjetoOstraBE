package dev.projetoostra.projetoostra.product.jewelryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class JewelryTypeRequestDTO {

    private String name;
    private Integer quantitySold;
    private Double soldValue;

}
