package org.example.fleetflow.mapper;

import org.example.fleetflow.DTO.delivery.DeliveryGetDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPostDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPutDTO;
import org.example.fleetflow.entities.Delivery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface DeliveryMapper {
    //Get
    DeliveryGetDTO toGetDTO(Delivery delivery);
    //Post
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    Delivery toEntity(DeliveryPostDTO deliveryPostDTO);
    //Put
    @Mapping(target = "client", ignore = true)
    @Mapping(target = "driver", ignore = true)
    @Mapping(target = "vehicle", ignore = true)
    void updateEntityFromPutDTO(DeliveryPutDTO deliveryPutDTO , @MappingTarget Delivery delivery);

}
