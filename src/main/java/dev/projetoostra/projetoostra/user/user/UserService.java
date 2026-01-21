package dev.projetoostra.projetoostra.user.user;

import dev.projetoostra.projetoostra.user.role.Role;
import dev.projetoostra.projetoostra.user.role.RoleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<UserResponseDTO> getAllUser(){
        List<User> lUsers = userRepository.findAll();
        return lUsers.stream()
                .map(userMapper::toResponse)
                .collect(Collectors.toList());
    }

    public UserResponseDTO getUserById(UUID id){
        Optional<User> user = userRepository.findById(id);
        return user.map(userMapper::toResponse).orElse(null);
    }

    public UserResponseDTO addUser(UserRequestDTO userRequestDTO){
        User user = userMapper.toUser(userRequestDTO);
        String defaultRoleName = "ROLE_USER";
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
        Role defaultRole = roleRepository.findByName(defaultRoleName)
                .orElseThrow(() -> new RuntimeException("Erro: A Role '" + defaultRoleName + "' não foi encontrada no banco."));
        user.setRole(defaultRole);
        User savedUser = userRepository.save(user);
        return userMapper.toResponse(savedUser);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO){

        User existentUser = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        if(userRequestDTO.getCpf() != null && !userRequestDTO.getCpf().isBlank())
            existentUser.setCpf(userRequestDTO.getCpf());
        if(userRequestDTO.getName() != null && !userRequestDTO.getName().isBlank())
            existentUser.setName(userRequestDTO.getName());
        if(userRequestDTO.getEmail() != null && !userRequestDTO.getEmail().isBlank())
            existentUser.setEmail(userRequestDTO.getEmail());
        if(userRequestDTO.getPassword() != null && !userRequestDTO.getPassword().isBlank())
            existentUser.setPassword(userRequestDTO.getPassword());
        if(userRequestDTO.getAddress() != null && !userRequestDTO.getAddress().isBlank())
            existentUser.setAddress(userRequestDTO.getAddress());
        if(userRequestDTO.getPhone() != null && !userRequestDTO.getPhone().isBlank())
            existentUser.setPhone(userRequestDTO.getPhone());

        User savedUser = userRepository.save(existentUser);

        return userMapper.toResponse(savedUser);
    }

    public void deleteUser(UUID id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        userRepository.delete(user);
    }

}
