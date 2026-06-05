package org.example.fleetflow.Controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.example.fleetflow.Service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clients")
@AllArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ClientGetDTO ajouterClient(@Valid @RequestBody ClientPostDTO clientPostDTO) {
        return clientService.ajouterClient(clientPostDTO);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN')")
    public Page<ClientGetDTO> getAllClient(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String type
    ) {
        return clientService.getAllClient(page, size, sort, type);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ClientGetDTO getClientById(@PathVariable Integer id) {
        return clientService.getClientById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ClientGetDTO updateClient(@PathVariable Integer id,
                                    @Valid @RequestBody ClientPutDTO clientPutDTO) {
        return clientService.updateClient(id, clientPutDTO);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public void deleteClient(@PathVariable Integer id) {
        clientService.deleteClient(id);
    }
}