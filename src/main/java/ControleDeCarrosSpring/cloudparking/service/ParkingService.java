package ControleDeCarrosSpring.cloudparking.service;

import ControleDeCarrosSpring.cloudparking.exception.ParkingNotFoundExecption;
import ControleDeCarrosSpring.cloudparking.model.Parking;
import ControleDeCarrosSpring.cloudparking.repository.ParkingRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ParkingService {


    private final ParkingRepository parkingRepository;


    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    public List<Parking> findAll(){
        return  parkingRepository.findAll();
    }

    private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");

    }

    public Parking findById(String id) {
        return  parkingRepository.findById(id).orElseThrow(() ->
        new ParkingNotFoundExecption(id));
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingRepository.save(parkingCreate);
        return parkingCreate;
    }

    public void delete(String id) {
         findById(id);
        parkingRepository.deleteById(id);
    }

    public Parking update(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parking.setState(parkingCreate.getState());
        parking.setModel(parkingCreate.getModel());
        parking.setLicense(parkingCreate.getLicense());
        parkingRepository.save(parking);
        return parking;
    }

   public Parking checkOut(String id) {
    Parking parking =findById(id);
    parking.setExitDate(LocalDateTime.now());
    parking.setBill(ParkingCheckOut.getBill(parking));
    parkingRepository.save(parking);
        return parking;
    }
}
