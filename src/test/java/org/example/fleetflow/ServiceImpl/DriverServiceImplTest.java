package org.example.fleetflow.ServiceImpl;

import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.entities.Driver;
import org.example.fleetflow.mapper.DriverMapper;
import org.example.fleetflow.repositories.DriverRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DriverServiceImplTest {
    List<Driver> mockDriverList;
    List<DriverGetDTO> mockDriverListDTO;
    @BeforeEach
    void setup(){
        Driver driver = Driver.builder()
                .id(1)
                .name("Reda")
                .number(1234567L)
                .licenceType("b")
                .available(true)
                .build();

        DriverGetDTO driverGetDTO = DriverGetDTO.builder()
                .id(1)
                .name("Reda")
                .number(1234567L)
                .available(true)
                .build();

        mockDriverList = List.of(driver);
        mockDriverListDTO = List.of(driverGetDTO);

    }

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private DriverMapper driverMapper;

    @InjectMocks
    private DriverServiceImpl driverServiceImpl;

    @Test
    @DisplayName("first test")
    void shouldReturnAvailableDrivers() {
        when(driverRepository.findByAvailableTrue()).thenReturn(mockDriverList);

        when(driverMapper.toGetDTO(mockDriverList.get(0))).thenReturn(mockDriverListDTO.get(0));

        List<DriverGetDTO> result = driverServiceImpl.getAllDriversAvailable();

        assertEquals(1, result.size());
        assertEquals(mockDriverListDTO,result);
        verify(driverRepository).findByAvailableTrue();
    }
}