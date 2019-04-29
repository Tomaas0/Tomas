package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.mybatis.dao.CarsMapper;
import vu.lt.mybatis.dao.DriversMapper;
import vu.lt.mybatis.model.Cars;
import vu.lt.mybatis.model.Drivers;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Model
public class CarsMyBatis {
    @Inject
    private CarsMapper carsMapper;
    @Inject
    private DriversMapper driversMapper;

    @Getter
    private List<Cars> allCars;

    @Getter @Setter
    private Cars carToCreate = new Cars();

    @Getter @Setter
    private Drivers driver;

    @PostConstruct
    public void init() {

        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer driverId = Integer.parseInt(requestParameters.get("driverId"));
        this.driver = driversMapper.selectByPrimaryKey(driverId);
        this.loadCars();
    }

    private void loadCars() {
        this.allCars = carsMapper.getCarsByDriversID(driver.getId());
    }

    @Transactional
    public String createCar() {
        carToCreate.setDriverId(driver.getId());
        carsMapper.insert(carToCreate);
        return "/myBatis/cars?faces-redirect=true&driverId=" + driver.getId();
    }
}
