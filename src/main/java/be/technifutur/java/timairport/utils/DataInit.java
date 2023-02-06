package be.technifutur.java.timairport.utils;

import be.technifutur.java.timairport.model.entity.*;
import be.technifutur.java.timairport.repository.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DataInit implements InitializingBean {

    private final CompanyRepository companyRepository;
    private final TypePlaneRepository typePlaneRepository;
    private final PilotRepository pilotRepository;
    private final AirportRepository airportRepository;
    private final PlaneRepository planeRepository;
    private final UserRepository userRepository;

    public DataInit(CompanyRepository companyRepository, TypePlaneRepository typePlaneRepository, PilotRepository pilotRepository, AirportRepository airportRepository, PlaneRepository planeRepository, UserRepository userRepository) {
        this.companyRepository = companyRepository;
        this.typePlaneRepository = typePlaneRepository;
        this.pilotRepository = pilotRepository;
        this.airportRepository = airportRepository;
        this.planeRepository = planeRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        TypePlane type1 = new TypePlane();
        type1.setName("big_plane");
        type1.setCapacity(300);

        typePlaneRepository.save( type1 );

        TypePlane type2 = new TypePlane();
        type2.setName("average_plane");
        type2.setCapacity(200);

        typePlaneRepository.save( type2 );

        TypePlane type3 = new TypePlane();
        type3.setName("small_plane");
        type3.setCapacity(100);

        typePlaneRepository.save( type3 );


        Company company1 = new Company();
        company1.setName("big money company");
        company1.setOriginCountry("USA");

        companyRepository.save( company1 );

        Pilot pilot1 = new Pilot();
        pilot1.setFirstName("Alexis");
        pilot1.setLastName("Gabriel");
        pilot1.setPhone("1315464643");
        pilot1.setEmail("aemj@at.airline.be");
        pilot1.setAddress("rue d'aéroport 123, 2504");
        pilot1.setLicenseId("D1E54E6Z7S9");
        pilot1.setLicenseAcquisition(LocalDate.of(1999,2,15));
        pilot1.setCompany(company1);

        pilotRepository.save(pilot1);

        Pilot pilot2 = new Pilot();
        pilot2.setFirstName("Bruno");
        pilot2.setLastName("Rolando");
        pilot2.setPhone("1315464643");
        pilot2.setEmail("aemj@at.airline.be");
        pilot2.setAddress("rue d'aéroport 123, 2504");
        pilot2.setLicenseId("E5C58F7SAT5");
        pilot2.setLicenseAcquisition(LocalDate.of(1993,5,1));
        pilot2.setCompany(company1);

        pilotRepository.save(pilot2);

        Plane plane1 = new Plane();
        plane1.setCallSign("F-QGTE");
        plane1.setType(type1);
        plane1.setCompany(company1);
        plane1.setRegistrationDate(LocalDate.of(2003,4,25));

        planeRepository.save(plane1);

        Plane plane2 = new Plane();
        plane2.setCallSign("F-GEAT");
        plane2.setType(type1);
        plane2.setCompany(company1);
        plane2.setRegistrationDate(LocalDate.of(2003,4,25));

        planeRepository.save(plane2);

        Plane plane3 = new Plane();
        plane3.setCallSign("F-ATQT");
        plane3.setType(type2);
        plane3.setCompany(company1);
        plane3.setRegistrationDate(LocalDate.of(2003,4,25));

        planeRepository.save(plane3);

        Company company2 = new Company();
        company2.setName("Deedlamerd");
        company2.setOriginCountry("Belgium");

        companyRepository.save( company2 );

        Pilot pilot3 = new Pilot();
        pilot3.setFirstName("Chris");
        pilot3.setLastName("Mason");
        pilot3.setPhone("1315464643");
        pilot3.setEmail("aemj@at.airline.be");
        pilot3.setAddress("rue d'aéroport 123, 2504");
        pilot3.setLicenseId("E16F4X99YS9");
        pilot3.setLicenseAcquisition(LocalDate.of(2003,4,25));
        pilot3.setCompany(company2);

        pilotRepository.save(pilot3);

        Pilot pilot4 = new Pilot();
        pilot4.setFirstName("Vivian");
        pilot4.setLastName("Roberson");
        pilot4.setPhone("1315464643");
        pilot4.setEmail("aemj@at.airline.be");
        pilot4.setAddress("rue d'aéroport 123, 2504");
        pilot4.setLicenseId("X664X5RSATU");
        pilot4.setLicenseAcquisition(LocalDate.of(2013,9,16));
        pilot4.setCompany(company2);

        pilotRepository.save(pilot4);

        Airport airport1 = new Airport();
        airport1.setName("Zaventem");
        airport1.setCountry("Belgium");
        airport1.setCity("Bruxelles");
        airport1.setAddress("Banlieue de Bruxelles");
        airport1.setPlaneTypesAllowed(new ArrayList<>(List.of(new TypePlane[]{type1, type2, type3})));

        airportRepository.save(airport1);

        Airport airport2 = new Airport();
        airport2.setName("Charleroi");
        airport2.setCountry("Belgium");
        airport2.setCity("Charleroi");
        airport2.setAddress("Banlieue de Charleroi");
        airport1.setPlaneTypesAllowed(new ArrayList<>(List.of(new TypePlane[]{type1, type2, type3})));

        airportRepository.save(airport2);

        Airport airport3 = new Airport();
        airport3.setName("Liège");
        airport3.setCountry("Belgium");
        airport3.setCity("Liège");
        airport3.setAddress("Banlieue de Liège");
        airport1.setPlaneTypesAllowed(new ArrayList<>(List.of(new TypePlane[]{type1, type2})));

        airportRepository.save(airport3);
    }
}
