package ControleDeCarrosSpring.cloudparking.controller.mapper;

import ControleDeCarrosSpring.cloudparking.controller.dto.ParkingDTO;
import ControleDeCarrosSpring.cloudparking.model.Parking;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ParkingMapper {


    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return null;
    }
}
