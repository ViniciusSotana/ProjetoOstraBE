package dev.projetoostra.projetoostra.user.client;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<ClientResponseDTO>> getClients() {
        List<ClientResponseDTO> lClients = clientService.getAllClients();
        return ResponseEntity.ok(lClients);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClientById(@PathVariable UUID id) {
        ClientResponseDTO client = clientService.getClientById(id);
        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ClientResponseDTO> addClient(@RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO createdClient = clientService.addClient(clientRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> updateClient(@PathVariable UUID id, @RequestBody @Valid ClientRequestDTO clientRequestDTO) {
        ClientResponseDTO updatedClient = clientService.updateClient(id, clientRequestDTO);
        if (updatedClient != null) {
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID id) {
        clientService.deleteClient(id);
        return ResponseEntity.noContent().build();
    }
    
}
