package dev.projetoostra.projetoostra.user.client;

import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public Client toClient(ClientRequestDTO clientRequestDTO){
        Client client = new Client();
        client.setName(clientRequestDTO.getName());
        client.setAddress(clientRequestDTO.getAddress());
        client.setPhone(clientRequestDTO.getPhone());

        return client;
    }

    public ClientResponseDTO toResponse(Client client){
        ClientResponseDTO clientResponseDTO = new ClientResponseDTO();
        clientResponseDTO.setId(client.getId());
        clientResponseDTO.setName(client.getName());
        clientResponseDTO.setAddress(client.getAddress());
        clientResponseDTO.setPhone(client.getPhone());

        return clientResponseDTO;
    }

}
