package dev.projetoostra.projetoostra.user.user;

import dev.projetoostra.projetoostra.user.role.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequestDTO {

    private String name;
    @NotBlank
    @Size(min = 11, max = 11)
    private String cpf;
    @Email
    private String email;
    @Size(min = 6)
    private String password;
    private String address;
    @Size(min = 11)
    private String phone;

}
