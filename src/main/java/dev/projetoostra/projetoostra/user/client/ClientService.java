package dev.projetoostra.projetoostra.user.client;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientService(ClientRepository clientRepository, ClientMapper clientMapper){
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    public List<ClientResponseDTO> getAllClients(){
        List<Client> lClients = clientRepository.findAll();

        return lClients.stream()
                .map(clientMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ClientResponseDTO getClientById(UUID id){
        Optional<Client> Client = clientRepository.findById(id);

        return Client.map(clientMapper::toResponse).orElse(null);
    }

    public ClientResponseDTO addClient(ClientRequestDTO ClientRequestDTO){
        Client Client = clientMapper.toClient(ClientRequestDTO);

        Client savedClient = clientRepository.save(Client);
        return clientMapper.toResponse(savedClient);
    }

    public ClientResponseDTO updateClient(UUID id, ClientRequestDTO clientRequestDTO){
        Client existentClient = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente nao encontrado(A)"));

        if (clientRequestDTO.getAddress() != null && !clientRequestDTO.getAddress().isBlank()){
            existentClient.setAddress(clientRequestDTO.getAddress());
        }
        if (clientRequestDTO.getName() != null && !clientRequestDTO.getName().isBlank()){
            existentClient.setName(clientRequestDTO.getName());
        }
        if (clientRequestDTO.getPhone() != null && !clientRequestDTO.getPhone().isBlank()){
            existentClient.setPhone(clientRequestDTO.getPhone());
        }

        Client savedClient = clientRepository.save(existentClient);

        return clientMapper.toResponse(savedClient);
    }

    public void deleteClient(UUID id){
        Client Client = clientRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Vendedora não encontrada"));

        clientRepository.delete(Client);
    }
    
}
