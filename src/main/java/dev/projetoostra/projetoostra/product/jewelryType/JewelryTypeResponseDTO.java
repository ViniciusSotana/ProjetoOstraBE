package dev.projetoostra.projetoostra.product.jewelryType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JewelryTypeResponseDTO {

    private UUID id;
    private String name;
    private Integer quantitySold;
    private Double soldValue;


}
