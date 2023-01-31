package be.technifutur.java.timairport.model.dto;

import be.technifutur.java.timairport.model.entity.TypePlane;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

@Data
@Builder // pas nécessaire
public class FlightDTO {
    private LocalDateTime departureTime;

    private LocalDateTime arrivalTime;

    private String destinationAirportName;

    @NotNull
    private String departureAirportName;

    @NotNull
    private String captainName;

    @NotNull
    private String firstOfficerName;

    @NotNull
    private String typeName;

    @NotNull
    private String companyName;
}
