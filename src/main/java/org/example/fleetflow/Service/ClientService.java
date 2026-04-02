package org.example.fleetflow.Service;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.example.fleetflow.entities.Client;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class ClientService {
  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  public ClientGetDTO ajouterClient(ClientPostDTO clientPostDTO){
         Client client=clientMapper.toEntity(clientPostDTO);
         Client savedClient =clientRepository.save(client);
         return clientMapper.toGetDTO(savedClient);
                                        }


  public List<ClientGetDTO> getAllClient(){
      return clientRepository.findAll()
              .stream().map(client->clientMapper.toGetDTO(client)).toList();


  }



    public ClientGetDTO getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));
        return clientMapper.toGetDTO(client);
    }

    public ClientGetDTO updateClient(Integer id, ClientPutDTO clientPutDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));

        clientMapper.updateEntityFromPutDTO(clientPutDTO, client);
        Client updatedClient = clientRepository.save(client);

        return clientMapper.toGetDTO(updatedClient);
    }



    public void deleteClient(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));

        clientRepository.delete(client);
    }








}
