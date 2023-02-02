package be.technifutur.java.timairport.service;

import be.technifutur.java.timairport.exceptions.NoPlaneAvailableException;
import be.technifutur.java.timairport.exceptions.RessourceNotFoundException;
import be.technifutur.java.timairport.mapper.FlightMapper;
import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
public class FlightServiceImpl implements FlightService{

    private  PlaneRepository planeRepository;
    private final CompanyRepository companyRepository;
    private final TypePlaneRepository typePlaneRepository;
    private final AirportRepository airportRepository;
    private final PilotRepository pilotRepository;
    private final FlightRepository flightRepository;
    private final FlightMapper mapper;

    public FlightServiceImpl(PlaneRepository planeRepository, CompanyRepository companyRepository, TypePlaneRepository typePlaneRepository, AirportRepository airportRepository, PilotRepository pilotRepository, FlightRepository flightRepository, FlightMapper mapper) {
        this.planeRepository = planeRepository;
        this.companyRepository = companyRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.airportRepository = airportRepository;
        this.pilotRepository = pilotRepository;
        this.flightRepository = flightRepository;
        this.mapper = mapper;
    }


    @Override
    public FlightDTO create(FlightInsertForm form) {
        Airport airportDept = airportRepository.findById(form.getIdDepartureAirport())
                .orElseThrow(RessourceNotFoundException::new);
        Airport airportArrl = airportRepository.findById(form.getIdDestinationAirport())
                .orElseThrow(RessourceNotFoundException::new);
        Pilot captain = pilotRepository.findById(form.getIdCaptain())
                .orElseThrow(RessourceNotFoundException::new);
        Pilot firstOfficer = pilotRepository.findById(form.getIdFirstOfficer())
                .orElseThrow(RessourceNotFoundException::new);
        TypePlane typePlane = typePlaneRepository.findById( form.getIdDesiredType() )
                .orElseThrow( RessourceNotFoundException::new );
        Company company = companyRepository.findById( form.getIdDesiredCompany() )
                .orElseThrow( RessourceNotFoundException::new );
        List<Plane> planes = planeRepository.findPlaneBy(typePlane, company);

        if (planes.isEmpty()){
            throw new NoPlaneAvailableException();
        }
        if (form.getArrivalTime().isBefore(form.getDepartureTime().plusDays(2))) {
            throw new RuntimeException("Arrival time should be after at least 2 days after depature time");
        }
        if (form.getIdDestinationAirport() == form.getIdDepartureAirport()) {
            throw new RuntimeException("Arrival airport and departure airport cannot be the same");
        }
        if(form.getIdCaptain() == form.getIdFirstOfficer()) {
            throw new RuntimeException("Captain and first officer cannot be the same");
        }

        Random rand = new Random();
        Plane plane = planes.get(rand.nextInt(planes.size()));

        Flight flight = new Flight();
        flight.setDepartureTime(form.getDepartureTime());
        flight.setArrivalTime(form.getArrivalTime());
        flight.setDeparture(airportDept);
        flight.setDestination(airportArrl);
        flight.setPlane(plane);
        flight.setCaptain(captain);
        flight.setFirstOfficer(firstOfficer);

        flightRepository.save(flight);

        return mapper.toDto(flight);
    }

    @Override
    public List<FlightDTO> getAllNonCancelled() {
        return  flightRepository.findAllNonCancelled().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void update(long idFlight, Map<String, Object> updateData) {
        if (updateData == null || updateData.isEmpty()) {return;}
        Flight flight = flightRepository.findById(idFlight).orElseThrow(RessourceNotFoundException::new);
        if (updateData.containsKey("departureTime")) {
            LocalDateTime departureTime = (LocalDateTime) updateData.get("departureTime");
            flight.setDepartureTime(departureTime);
        }

        if (updateData.containsKey("arrivalTime")) {
            LocalDateTime arrivalTime = (LocalDateTime) updateData.get("arrivalTime");
            flight.setArrivalTime(arrivalTime);
        }

        flightRepository.save(flight);
    }

    @Override
    public void delete(long idFlight) {
        Flight flight = flightRepository.findById(idFlight).orElseThrow(RessourceNotFoundException::new);
        flight.setCancelled(true);
        flightRepository.save(flight);
    }

}
