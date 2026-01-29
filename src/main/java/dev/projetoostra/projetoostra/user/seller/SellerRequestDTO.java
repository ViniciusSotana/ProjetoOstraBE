package dev.projetoostra.projetoostra.user.seller;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerRequestDTO {

    private String name;
    @Size(min = 11, max = 11)
    private String cpf;
    private String address;
    private Double commission;
    private Double soldValue;


}
