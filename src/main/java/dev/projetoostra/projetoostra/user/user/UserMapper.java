package dev.projetoostra.projetoostra.user.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public UserResponseDTO toResponse(UserRequestDTO userRequestDTO){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setCpf(userRequestDTO.getCpf());
        userResponseDTO.setName(userRequestDTO.getName());
        userResponseDTO.setEmail(userRequestDTO.getEmail());
        userResponseDTO.setAddress(userRequestDTO.getAddress());
        userResponseDTO.setPhone(userRequestDTO.getPhone());
        return userResponseDTO;
    }

    public User toUser(UserRequestDTO userRequestDTO){
        User user = new User();
        user.setCpf(userRequestDTO.getCpf());
        user.setName(userRequestDTO.getName());
        user.setEmail(userRequestDTO.getEmail());
        user.setPassword(userRequestDTO.getPassword());
        user.setAddress(userRequestDTO.getAddress());
        user.setPhone(userRequestDTO.getPhone());
        return user;
    }

    public UserResponseDTO toResponse(User user){
        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setId(user.getId());
        userResponseDTO.setCpf(user.getCpf());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setEmail(user.getEmail());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setPhone(user.getPhone());
        userResponseDTO.setRole(user.getRole());
        return userResponseDTO;
    }

}
