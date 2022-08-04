package ControleDeCarrosSpring.cloudparking.controller;

import ControleDeCarrosSpring.cloudparking.controller.dto.ParkingCreateDTO;
import ControleDeCarrosSpring.cloudparking.controller.dto.ParkingDTO;
import ControleDeCarrosSpring.cloudparking.controller.mapper.ParkingMapper;
import ControleDeCarrosSpring.cloudparking.model.Parking;
import ControleDeCarrosSpring.cloudparking.service.ParkingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<List<ParkingDTO>> findAll(){
        List<Parking> parkingList=  parkingService.findAll();
        List<ParkingDTO> result = parkingMapper.toParkingDTOList(parkingList);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ParkingDTO> findById(@PathVariable String id){
        Parking parking=  parkingService.findById(id);

        ParkingDTO result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.ok(result);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable String id){
      parkingService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping
    public ResponseEntity<ParkingDTO> create(@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        var parking=  parkingService.create(parkingCreate);
        var result = parkingMapper.toParkingDTO(parking);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ParkingDTO> update(@PathVariable String id,@RequestBody ParkingCreateDTO dto){
        var parkingCreate = parkingMapper.toParkingCreate(dto);
        Parking parking=  parkingService.update(id, parkingCreate);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }

    @PostMapping("/{id}")
    public ResponseEntity<ParkingDTO> checkOut(@PathVariable String id){
        Parking parking=  parkingService.checkOut(id);
        return ResponseEntity.ok(parkingMapper.toParkingDTO(parking));
    }
}
