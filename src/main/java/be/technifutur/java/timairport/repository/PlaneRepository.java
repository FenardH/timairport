package be.technifutur.java.timairport.repository;

import be.technifutur.java.timairport.model.entity.Company;
import be.technifutur.java.timairport.model.entity.Plane;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface PlaneRepository extends JpaRepository<Plane, Long> {

    @Modifying  // utiles dans le cas d'un UPDATE/DELETE
    @Transactional  // utiles dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Plane p SET p.id = ?1 WHERE p.inMaintenance = ?2")
    void updateMaintenance(long id, boolean maintenance);

    @Modifying  // utiles dans le cas d'un UPDATE/DELETE
    @Transactional  // utiles dans le cas d'un UPDATE/DELETE
    @Query("UPDATE Plane p SET p.id = ?1 WHERE p.company = ?2")
    void updateCompany(long id, Company company);
}