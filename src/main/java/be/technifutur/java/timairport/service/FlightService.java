package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;

import java.util.List;
import java.util.Map;

public interface FlightService {
    public FlightDTO create(FlightInsertForm form);

    public List<FlightDTO> getAllNonCancelled();

    void update(long idFlight, Map<String, Object> mapValues);

    void delete(long idFlight);
}
