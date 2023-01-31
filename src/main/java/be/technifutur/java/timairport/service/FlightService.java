package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.model.form.PlaneInsertForm;
import org.springframework.stereotype.Service;

public interface FlightService {
    public FlightDTO create(FlightInsertForm form);
}
