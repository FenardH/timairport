package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
}
