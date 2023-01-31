package be.technifutur.java.timairport.model.form;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class FlightInsertForm {
    @NotNull
    @Future
    private LocalDateTime departureTime;

    @NotNull
    @Future()
    private LocalDateTime arrivalTime;

    @NotNull
    private long idDestinationAirport;

    @NotNull
    private long idDepartureAirport;

    @NotNull
    private long idCaptain;

    @NotNull
    private long idFirstOfficer;

    @NotNull
    private long idDesiredType;

    @NotNull
    private long idDesiredCompany;

}
