package dev.projetoostra.projetoostra.config.security;

import dev.projetoostra.projetoostra.user.role.Role;
import dev.projetoostra.projetoostra.user.role.RoleRepository;
import dev.projetoostra.projetoostra.user.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager,
                                    TokenService tokenService,
                                    UserRepository userRepository,
                                    PasswordEncoder passwordEncoder,
                                    RoleRepository roleRepository) {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO data) {
        try {
            var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());

            var auth = this.authenticationManager.authenticate(usernamePassword);

            var usuario = (User) auth.getPrincipal();

            var token = tokenService.generateToken(usuario);

            return ResponseEntity.ok(new LoginResponseDTO(token));

        } catch (AuthenticationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Falha na autenticação: " + e.getMessage());
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserRequestDTO data) {

        if (this.userRepository.findByEmail(data.getEmail()) != null) {
            return ResponseEntity.badRequest().body("Email já está em uso.");
        }

        String encryptedPassword = passwordEncoder.encode(data.getPassword());

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Erro: Role 'ROLE_USER' não encontrada. Verifique seu DataInitializer."));

        User newUser = new User();

        newUser.setName(data.getName());
        newUser.setCpf(data.getCpf());
        newUser.setEmail(data.getEmail());
        newUser.setPhone(data.getPhone());

        newUser.setPassword(encryptedPassword);
        newUser.setRole(userRole);

        try {
            this.userRepository.save(newUser);
            return ResponseEntity.status(HttpStatus.CREATED).body("Usuário registrado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao salvar usuário: " + e.getMessage());
        }
    }

}
