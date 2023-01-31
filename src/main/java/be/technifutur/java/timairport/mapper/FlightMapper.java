package be.technifutur.java.timairport.mapper;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.Flight;
import org.springframework.stereotype.Service;

@Service
public class FlightMapper {

    public FlightDTO toDto(Flight entity){
        if( entity == null )
            return null;

        return FlightDTO.builder()
                .departureTime(entity.getDepartureTime())
                .arrivalTime(entity.getArrivalTime())
                .destinationAirportName(entity.getDestination().getName())
                .departureAirportName(entity.getDeparture().getName())
                .captainName(entity.getCaptain().getFirstName() + " " + entity.getCaptain().getLastName())
                .firstOfficerName(entity.getFirstOfficer().getFirstName() + " " + entity.getFirstOfficer().getLastName())
                .typeName(entity.getPlane().getType().getName())
                .companyName(entity.getPlane().getCompany().getName())
                .build();
    }
}
