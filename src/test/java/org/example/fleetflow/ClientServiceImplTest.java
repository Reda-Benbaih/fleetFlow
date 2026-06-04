package org.example.fleetflow;


import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.Service.ClientService;
import org.example.fleetflow.entities.Client;
import org.example.fleetflow.mapper.ClientMapper;
import org.example.fleetflow.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {





    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ClientMapper clientMapper;

    @InjectMocks
    private ClientService clientService;

    @Test
    void shouldAddClientSuccessfully() {
        // Given
        ClientPostDTO postDTO = new ClientPostDTO();
        postDTO.setName("Ali");
        postDTO.setEmail("ali@mail.com");
        postDTO.setCity("Beni Mellal");
        postDTO.setNumber(612345678L);

        Client client = new Client();
        client.setName("Ali");
        client.setEmail("ali@mail.com");
        client.setCity("Beni Mellal");
        client.setNumber(612345678L);

        Client savedClient = new Client();
        savedClient.setId(1);
        savedClient.setName("Ali");
        savedClient.setEmail("ali@mail.com");
        savedClient.setCity("Beni Mellal");
        savedClient.setNumber(612345678L);

        ClientGetDTO getDTO = new ClientGetDTO();
        getDTO.setId(1);
        getDTO.setName("Ali");
        getDTO.setEmail("ali@mail.com");
        getDTO.setNumber(612345678L);

        when(clientMapper.toEntity(postDTO)).thenReturn(client);
        when(clientRepository.save(client)).thenReturn(savedClient);
        when(clientMapper.toGetDTO(savedClient)).thenReturn(getDTO);

        // When
        ClientGetDTO result = clientService.ajouterClient(postDTO);

        // Then
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Ali", result.getName());
        assertEquals("ali@mail.com", result.getEmail());
        assertEquals(612345678L, result.getNumber());

        verify(clientMapper).toEntity(postDTO);
        verify(clientRepository).save(client);
        verify(clientMapper).toGetDTO(savedClient);
    }









    @Test
    void shouldThrowExceptionWhenEmailAlreadyExists() {
        // Given
        ClientPostDTO postDTO = new ClientPostDTO();
        postDTO.setName("Ali");
        postDTO.setEmail("ali@mail.com");
        postDTO.setCity("Beni Mellal");
        postDTO.setNumber(612345678L);

        when(clientRepository.existsByEmail("ali@mail.com")).thenReturn(true);

        // When
        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> clientService.ajouterClient(postDTO)
        );

        // Then
        assertEquals("Un client avec cet email existe déjà", exception.getMessage());

        verify(clientRepository).existsByEmail("ali@mail.com");
        verify(clientRepository, never()).save(any(Client.class));
        verify(clientMapper, never()).toEntity(any(ClientPostDTO.class));
    }




}