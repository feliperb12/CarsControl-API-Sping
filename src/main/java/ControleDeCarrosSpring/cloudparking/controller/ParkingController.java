package ControleDeCarrosSpring.cloudparking.controller;

import ControleDeCarrosSpring.cloudparking.controller.dto.ParkingDTO;
import ControleDeCarrosSpring.cloudparking.controller.mapper.ParkingMapper;
import ControleDeCarrosSpring.cloudparking.model.Parking;
import ControleDeCarrosSpring.cloudparking.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking")
public class ParkingController {

    private final ParkingMapper parkingMapper;
    private final ParkingService parkingService;



    public ParkingController(ParkingMapper parkingMapper, ParkingService parkingService) {
        this.parkingMapper = parkingMapper;
        this.parkingService = parkingService;
    }

    @GetMapping
    public List<ParkingDTO> findAll(){
        List<Parking> parkingList=  parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);

    }
}
