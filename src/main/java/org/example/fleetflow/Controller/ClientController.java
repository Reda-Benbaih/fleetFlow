package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.example.fleetflow.Service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ClientGetDTO ajouterClient(@Valid @RequestBody ClientPostDTO clientPostDTO) {
        return clientService.ajouterClient(clientPostDTO);
    }

    @GetMapping
    public Page<ClientGetDTO> getAllClient(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ) {
        return clientService.getAllClient(page, size, sort, type);
    }

    @GetMapping("/{id}")
    public ClientGetDTO getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    public ClientGetDTO updateClient(@PathVariable Integer id,
                                    @Valid @RequestBody ClientPutDTO clientPutDTO) {
        return clientService.updateClient(id, clientPutDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}