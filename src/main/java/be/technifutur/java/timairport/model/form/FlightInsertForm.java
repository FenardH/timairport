package be.technifutur.java.timairport.model.form;
import be.technifutur.java.timairport.constraints.Not0;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import lombok.Data;


import java.time.LocalDateTime;

@Data
public class FlightInsertForm {
    @NotNull
    @Future
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime departureTime;

    @NotNull
    @Future()
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
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
    @Not0
    private long idDesiredCompany;

}
