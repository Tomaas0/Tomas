package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Driver;
import vu.lt.persistence.DriversDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class Drivers {

    @Inject
    private DriversDAO driversDAO;

    @Getter @Setter
    private Driver driverToCreate = new Driver();

    @Getter
    private List<Driver> allDrivers;

    @PostConstruct
    public void init(){
        loadAllDrivers();
    }

    @Transactional
    public String createDriver(){
        this.driversDAO.persist(driverToCreate);
        return "index?faces-redirect=true";
    }

    private void loadAllDrivers(){
        this.allDrivers = driversDAO.loadAll();
    }
}
