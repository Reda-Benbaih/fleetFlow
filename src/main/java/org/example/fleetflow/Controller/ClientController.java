package org.example.fleetflow.Controller;

import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.example.fleetflow.Service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ClientGetDTO ajouterClient(@RequestBody ClientPostDTO clientPostDTO) {
        return clientService.ajouterClient(clientPostDTO);
    }

    @GetMapping
    public List<ClientGetDTO> getAllClient() {
        return clientService.getAllClient();
    }

    @GetMapping("/{id}")
    public ClientGetDTO getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientGetDTO updateClient(@PathVariable Integer id,
                                     @RequestBody ClientPutDTO clientPutDTO) {
        return clientService.updateClient(id, clientPutDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}