package org.example.fleetflow.mapper;

import org.example.fleetflow.DTO.client.ClientGetDTO;
import org.example.fleetflow.DTO.client.ClientPostDTO;
import org.example.fleetflow.DTO.client.ClientPutDTO;
import org.example.fleetflow.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    //Get
    ClientGetDTO toGetDTO(Client client);

    //Post
    Client toEntity(ClientPostDTO clientPostDTO);

    //put
    void updateEntityFromPutDTO(ClientPutDTO clientPutDTO,@MappingTarget Client client);
}
