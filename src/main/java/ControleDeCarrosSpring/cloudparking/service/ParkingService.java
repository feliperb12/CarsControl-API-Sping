package ControleDeCarrosSpring.cloudparking.service;

import ControleDeCarrosSpring.cloudparking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id = getUUID();
        var id1 = getUUID();
        Parking parking = new Parking(id, "DMS-111", "SC", "CELTA", "PRETO");
        Parking parking1 = new Parking(id1, "WXS-271", "RJ", "GOL", "AZUL");
        parkingMap.put(id,parking);
        parkingMap.put(id1,parking1);
    }

    public List<Parking> findAll(){
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");

    }

    public Parking findById(String id) {
        return parkingMap.get(id);
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUID();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDate.now());
        parkingMap.put(uuid, parkingCreate);
        return parkingCreate;
    }
}
