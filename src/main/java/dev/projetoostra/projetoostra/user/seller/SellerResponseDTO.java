package dev.projetoostra.projetoostra.user.seller;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellerResponseDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String address;
    private Double commission;
    private Double soldValue;


}
