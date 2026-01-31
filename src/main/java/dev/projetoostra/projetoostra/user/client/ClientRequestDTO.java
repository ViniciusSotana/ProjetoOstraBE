package dev.projetoostra.projetoostra.user.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequestDTO {

    private String name;
    private String address;
    private String phone;

}
