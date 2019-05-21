package vu.lt.usecases;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.Driver;
import vu.lt.interceptors.LoggedInvocation;
import vu.lt.persistence.CarsDAO;
import vu.lt.persistence.DriversDAO;

@Model
public class CarsForDrivers implements Serializable {

    @Inject
    private DriversDAO driversDAO;

    @Inject
    private CarsDAO carsDAO;

    @Getter @Setter
    private Driver driver;

    @Getter @Setter
    private Car carToCreate = new Car();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer driverId = Integer.parseInt(requestParameters.get("driverId"));
        this.driver = driversDAO.findOne(driverId);
    }

    @Transactional
    @LoggedInvocation
    public String createCar() {
        carToCreate.setDriver(this.driver);
        carsDAO.persist(carToCreate);
        return "cars?faces-redirect=true&driverId=" + this.driver.getId();
    }
}
