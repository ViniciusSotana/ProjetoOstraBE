package dev.projetoostra.projetoostra.user.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponseDTO {

    private UUID id;
    private String name;
    private String address;
    private String phone;

}
