package org.example.fleetflow.ServiceImpl;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.example.fleetflow.Service.ClientService;
import org.example.fleetflow.entities.Client;
import org.example.fleetflow.entities.UserRoles;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.repositories.ClientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final PasswordEncoder passwordEncoder; // <-- Inject Password Encoder

    @Override
    public ClientGetDTO ajouterClient(ClientPostDTO clientPostDTO){
        if (clientRepository.existsByEmail(clientPostDTO.getEmail())) {
            throw new RuntimeException("Un client avec cet email existe déjà");
        }
        Client client = clientMapper.toEntity(clientPostDTO);

        client.setUsername(clientPostDTO.getEmail()); // Using email as username
        client.setPassword(passwordEncoder.encode("client123")); // Default password for new clients
        client.setUserRoles(UserRoles.MANAGER); // Assigning Manager role

        Client savedClient = clientRepository.save(client);
        return clientMapper.toGetDTO(savedClient);
    }

    @Override
    public Page<ClientGetDTO> getAllClient(int page , int size , String sort,String type){
        Sort sortby = type.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page,size,sortby);
        return clientRepository.findAll(pageable)
                .map(clientMapper::toGetDTO);
    }

    @Override
    public ClientGetDTO getClientById(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));
        return clientMapper.toGetDTO(client);
    }

    @Override
    public ClientGetDTO updateClient(Integer id, ClientPutDTO clientPutDTO) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));

        clientMapper.updateEntityFromPutDTO(clientPutDTO, client);
        Client updatedClient = clientRepository.save(client);

        return clientMapper.toGetDTO(updatedClient);
    }

    @Override
    public void deleteClient(Integer id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client introuvable avec l'id : " + id));
        clientRepository.delete(client);
    }
}