package org.example.fleetflow.Service;

import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.springframework.data.domain.Page;

public interface ClientService {
    ClientGetDTO ajouterClient(ClientPostDTO clientPostDTO);
    Page<ClientGetDTO> getAllClient(int page , int size , String sort, String type);
    ClientGetDTO getClientById(Integer id);
    ClientGetDTO updateClient(Integer id, ClientPutDTO clientPutDTO);
    void deleteClient(Integer id);
}
