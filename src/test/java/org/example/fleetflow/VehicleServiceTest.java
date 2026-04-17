package org.example.fleetflow;

import org.example.fleetflow.DTO.vehicle.VehicleGetDTO;
import org.example.fleetflow.Service.VehicleService;
import org.example.fleetflow.entities.Vehicle;
import org.example.fleetflow.entities.VehicleStatus;
import org.example.fleetflow.mapper.VehicleMapper;
import org.example.fleetflow.repositories.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class VehicleServiceTest {

    @Mock
    private VehicleRepository vehicleRepository;

    @Mock
    private VehicleMapper vehicleMapper;

    @InjectMocks
    private VehicleService vehicleService;

    @Test
    void shouldReturnVehiclesByStatus() {
        // Given
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setRegistrationNumber("123-A-1");
        vehicle1.setType("Truck");
        vehicle1.setCapacity(20);
        vehicle1.setVehicleStatus(VehicleStatus.AVAILABLE);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setRegistrationNumber("456-B-2");
        vehicle2.setType("Van");
        vehicle2.setCapacity(10);
        vehicle2.setVehicleStatus(VehicleStatus.AVAILABLE);

        VehicleGetDTO dto1 = new VehicleGetDTO();
        dto1.setId(1);
        dto1.setRegistrationNumber("123-A-1");
        dto1.setType("Truck");
        dto1.setCapacity(20);

        VehicleGetDTO dto2 = new VehicleGetDTO();
        dto2.setId(2);
        dto2.setRegistrationNumber("456-B-2");
        dto2.setType("Van");
        dto2.setCapacity(10);

        when(vehicleRepository.findByVehicleStatus(VehicleStatus.AVAILABLE))
                .thenReturn(List.of(vehicle1, vehicle2));

        when(vehicleMapper.toGetDTO(vehicle1)).thenReturn(dto1);
        when(vehicleMapper.toGetDTO(vehicle2)).thenReturn(dto2);

        // When
        List<VehicleGetDTO> result = vehicleService.getVehicleByStatus(VehicleStatus.AVAILABLE);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("123-A-1", result.get(0).getRegistrationNumber());
        assertEquals("456-B-2", result.get(1).getRegistrationNumber());

        verify(vehicleRepository).findByVehicleStatus(VehicleStatus.AVAILABLE);
        verify(vehicleMapper).toGetDTO(vehicle1);
        verify(vehicleMapper).toGetDTO(vehicle2);
    }







    @Test
    void shouldReturnVehiclesWithCapacityGreaterThanGivenValue() {
        // Given
        Vehicle vehicle1 = new Vehicle();
        vehicle1.setId(1);
        vehicle1.setRegistrationNumber("123-A-1");
        vehicle1.setType("Truck");
        vehicle1.setCapacity(20);
        vehicle1.setVehicleStatus(VehicleStatus.AVAILABLE);

        Vehicle vehicle2 = new Vehicle();
        vehicle2.setId(2);
        vehicle2.setRegistrationNumber("456-B-2");
        vehicle2.setType("Bus");
        vehicle2.setCapacity(30);
        vehicle2.setVehicleStatus(VehicleStatus.MAINTENANCE);

        VehicleGetDTO dto1 = new VehicleGetDTO();
        dto1.setId(1);
        dto1.setRegistrationNumber("123-A-1");
        dto1.setType("Truck");
        dto1.setCapacity(20);

        VehicleGetDTO dto2 = new VehicleGetDTO();
        dto2.setId(2);
        dto2.setRegistrationNumber("456-B-2");
        dto2.setType("Bus");
        dto2.setCapacity(30);

        when(vehicleRepository.findByCapacityGreaterThan(15))
                .thenReturn(List.of(vehicle1, vehicle2));

        when(vehicleMapper.toGetDTO(vehicle1)).thenReturn(dto1);
        when(vehicleMapper.toGetDTO(vehicle2)).thenReturn(dto2);

        // When
        List<VehicleGetDTO> result = vehicleService.getVehicleGreatThan(15);

        // Then
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.get(0).getCapacity() > 15);
        assertTrue(result.get(1).getCapacity() > 15);

        verify(vehicleRepository).findByCapacityGreaterThan(15);
        verify(vehicleMapper).toGetDTO(vehicle1);
        verify(vehicleMapper).toGetDTO(vehicle2);
    }






}