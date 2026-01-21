package dev.projetoostra.projetoostra.user.user;

import dev.projetoostra.projetoostra.user.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String cpf;
    private String email;
    private String address;
    private String phone;
    private Role role;

}
