package org.example.fleetflow.Service;

import org.example.fleetflow.DTO.delivery.DeliveryGetDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPostDTO;
import org.example.fleetflow.DTO.delivery.DeliveryPutDTO;
import org.example.fleetflow.entities.*;
import org.example.fleetflow.mapper.DeliveryMapper;
import org.example.fleetflow.repositories.ClientRepository;
import org.example.fleetflow.repositories.DeliveryRepository;
import org.example.fleetflow.repositories.DriverRepository;
import org.example.fleetflow.repositories.VehicleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DeliveryServiceTest {

    @Mock
    private DeliveryRepository deliveryRepository;
    @Mock
    private DeliveryMapper deliveryMapper;
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private DriverRepository driverRepository;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private DeliveryGetDTO deliveryGetDTO;
    @InjectMocks
    private DeliveryService deliveryService;

    DeliveryPutDTO deliveryPutDTO;
    DeliveryGetDTO deliveryGetDTOChanged;
    DeliveryPostDTO deliveryPostDTO;
    Delivery delivery;
    Client client;
    Driver driver;
    Vehicle vehicle;
    Delivery deliveryChanged;

    @BeforeEach
    void setup(){
        deliveryPostDTO = DeliveryPostDTO.builder()
                .deliveryDate(LocalDate.now())
                .startAddress("start")
                .endAddress("end")
                .deliveryStatus(DeliveryStatus.IN_PROGRESS)
                .clientId(1)
                .driverId(1)
                .vehicleId(1)
                .build();

        deliveryGetDTO = DeliveryGetDTO.builder()
                .deliveryDate(LocalDate.now())
                .startAddress("start")
                .endAddress("end")
                .deliveryStatus(DeliveryStatus.IN_PROGRESS)
                .build();

        deliveryPutDTO = DeliveryPutDTO.builder()
                .deliveryDate(LocalDate.now())
                .startAddress("start")
                .endAddress("end")
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .clientId(1)
                .driverId(1)
                .vehicleId(1)
                .build();

        deliveryGetDTOChanged = DeliveryGetDTO.builder()
                .deliveryDate(LocalDate.now())
                .startAddress("start")
                .endAddress("end")
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .build();

        delivery = Delivery.builder()
                .deliveryDate(LocalDate.now())
                .startAddress("start")
                .endAddress("end")
                .deliveryStatus(DeliveryStatus.IN_PROGRESS)
                .build();

        deliveryChanged = Delivery.builder()
                .deliveryDate(LocalDate.now())
                .startAddress("start")
                .endAddress("end")
                .deliveryStatus(DeliveryStatus.DELIVERED)
                .build();

        client = Client.builder()
                .id(1)
                .name("reda")
                .email("ertyu@wertyui")
                .city("WERTYUIO")
                .number(1234678L)
                .build();

        driver = Driver.builder()
                .id(1)
                .name("Reda")
                .number(1234567L)
                .licenceType("b")
                .available(true)
                .build();

        vehicle = Vehicle.builder()
                .id(1)
                .registrationNumber("qwertyui")
                .type("wertyuio")
                .capacity(123456)
                .vehicleStatus(VehicleStatus.DELIVERY)
                .build();
    }
    @Test
    void ajouterDeliveryTest() {

        when(deliveryMapper.toEntity(deliveryPostDTO)).thenReturn(delivery);
        when(clientRepository.findById(client.getId())).thenReturn(Optional.ofNullable(client));
        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(driver));
        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.ofNullable(vehicle));

        delivery.setClient(client);
        delivery.setDriver(driver);
        delivery.setVehicle(vehicle);

        when(deliveryRepository.save(delivery)).thenReturn(delivery);
        when(deliveryMapper.toGetDTO(delivery)).thenReturn(deliveryGetDTO);

        DeliveryGetDTO result = deliveryService.ajouterDelivery(deliveryPostDTO);

        assertEquals(deliveryGetDTO,result);
    }

    @Test
    void updateDeliveryStatusTest(){
        when(deliveryRepository.findById(1)).thenReturn(Optional.ofNullable(delivery));
        when(deliveryMapper.updateEntityFromPutDTO(deliveryPutDTO,delivery)).thenReturn(delivery);
        when(clientRepository.findById(client.getId())).thenReturn(Optional.ofNullable(client));
        when(driverRepository.findById(driver.getId())).thenReturn(Optional.ofNullable(driver));
        when(vehicleRepository.findById(vehicle.getId())).thenReturn(Optional.ofNullable(vehicle));

        delivery.setClient(client);
        delivery.setDriver(driver);
        delivery.setVehicle(vehicle);

        when(deliveryRepository.save(delivery)).thenReturn(deliveryChanged);
        when(deliveryMapper.toGetDTO(deliveryChanged)).thenReturn(deliveryGetDTOChanged);

        DeliveryGetDTO result = deliveryService.updateDelivery(1,deliveryPutDTO);

        assertEquals(deliveryChanged.getDeliveryStatus(),result.getDeliveryStatus());
    }


}