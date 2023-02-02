package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.dto.FlightDTO;
import be.technifutur.java.timairport.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    @Query("SELECT f FROM Flight f WHERE f.cancelled = false")
    List<Flight> findAllNonCancelled();
}
