package org.example.fleetflow.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.DTO.driver.DriverPostDTO;
import org.example.fleetflow.DTO.driver.DriverPutDTO;
import org.example.fleetflow.entities.Driver;
import org.example.fleetflow.mapper.DriverMapper;
import org.example.fleetflow.repositories.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service @RequiredArgsConstructor
public class DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;

    @Transactional
    public DriverGetDTO saveDriver(DriverPostDTO postDTO){
        Driver driver = driverMapper.toEntity(postDTO);
        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toGetDTO(savedDriver);
    }

    @Transactional
    public Page<DriverGetDTO> getAllDrivers(Pageable pageable){
        return driverRepository.findAll(pageable)
                .map(driverMapper::toGetDTO);
    }

    public DriverGetDTO updateDriver(Integer id, DriverPutDTO driverPutDTO){
        Driver driverExist = driverRepository.findById(id)
                .orElseThrow(()->new RuntimeException("driver n'exite pas"));

        driverMapper.updateEntityFromPutDTO(driverPutDTO,driverExist);

        Driver updatedDriver = driverRepository.save(driverExist);

        return driverMapper.toGetDTO(updatedDriver);
    }

    @Transactional
    public List<DriverGetDTO> getAllDriversAvailable(){
        return driverRepository.findByAvailableTrue().stream()
                .map(driver -> driverMapper.toGetDTO(driver))
                .toList();
    }

    @Transactional
    public void deleteDriver(Integer id){
        driverRepository.deleteById(id);
    }
}
