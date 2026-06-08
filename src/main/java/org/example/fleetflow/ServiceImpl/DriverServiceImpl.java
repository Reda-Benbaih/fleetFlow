package org.example.fleetflow.ServiceImpl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.fleetflow.DTO.driver.DriverGetDTO;
import org.example.fleetflow.DTO.driver.DriverPostDTO;
import org.example.fleetflow.DTO.driver.DriverPutDTO;
import org.example.fleetflow.Service.DriverService;
import org.example.fleetflow.entities.Driver;
import org.example.fleetflow.entities.UserRoles;
import org.example.fleetflow.mapper.DriverMapper;
import org.example.fleetflow.repositories.DriverRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DriverServiceImpl implements DriverService {
    private final DriverRepository driverRepository;
    private final DriverMapper driverMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public DriverGetDTO saveDriver(DriverPostDTO postDTO){
        Driver driver = driverMapper.toEntity(postDTO);

        // Simple setup exactly like the ClientService
        driver.setUsername(postDTO.getEmail());
        driver.setPassword(passwordEncoder.encode("driver123")); // Default password
        driver.setUserRoles(UserRoles.DRIVER);

        Driver savedDriver = driverRepository.save(driver);
        return driverMapper.toGetDTO(savedDriver);
    }

    @Override
    @Transactional
    public Page<DriverGetDTO> getAllDrivers(int page ,int size,String sort,String type){
        Sort sortby = type.equalsIgnoreCase("desc") ? Sort.by(sort).descending() : Sort.by(sort).ascending();
        Pageable pageable = PageRequest.of(page,size,sortby);

        return driverRepository.findAll(pageable)
                .map(driverMapper::toGetDTO);
    }

    @Override
    public DriverGetDTO updateDriver(Integer id, DriverPutDTO driverPutDTO){
        Driver driverExist = driverRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Driver n'exite pas"));

        driverMapper.updateEntityFromPutDTO(driverPutDTO,driverExist);
        Driver updatedDriver = driverRepository.save(driverExist);

        return driverMapper.toGetDTO(updatedDriver);
    }

    @Override
    @Transactional
    public List<DriverGetDTO> getAllDriversAvailable(){
        return driverRepository.findByAvailableTrue().stream()
                .map(driverMapper::toGetDTO)
                .toList();
    }

    @Override
    @Transactional
    public void deleteDriver(Integer id){
        driverRepository.deleteById(id);
    }
}