package be.technifutur.java.timairport.controller;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.dto.PlaneDTO;
import be.technifutur.java.timairport.model.form.FlightInsertForm;
import be.technifutur.java.timairport.service.FlightService;
import jakarta.validation.Valid;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/flight")
public class FlightController {
    private final FlightService flightService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @PostMapping("/add")
    public FlightDTO create(@RequestBody @Valid FlightInsertForm form, Model model){
        model.addAttribute("form", "form");
        return flightService.create( form );
    }

    // GET - http://localhost:8080/all
    @GetMapping("/all")
    public List<FlightDTO> getAll(){
        return flightService.getAllNonCancelled();
    }

    @PatchMapping(value = "/{idFlight:[0-9]+}/update")
    public void update(@PathVariable long idFlight, @RequestParam Map<String, String> params) {
        Map<String, Object> mapValues = new HashMap<>();
        if (params.containsKey("departureTime")) {
            mapValues.put("departureTime", LocalDate.parse(params.get("companyId"), formatter));
        }

        if (params.containsKey("arrivalTime")) {
            mapValues.put("arrivalTime", LocalDate.parse(params.get("arrivalTime"), formatter));
        }

        flightService.update(idFlight, mapValues);
    }

    // /flight/9 OU
    // /flight/9/delete
    @DeleteMapping({"/{idFlight:[0-9]+}", "/{idFlight:[0-9]+}/delete"})
    public void delete(@PathVariable long idFlight){
        flightService.delete(idFlight);
    }
}
