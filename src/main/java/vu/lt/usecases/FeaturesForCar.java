package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.Feature;
import vu.lt.persistence.CarsDAO;
import vu.lt.persistence.FeaturesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.List;

@Model
public class FeaturesForCar implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private FeaturesDAO featuresDAO;

    @Getter @Setter
    private Car car;

    @Getter
    private List<Feature> carFeatures;

    @Getter
    private List<Feature> allFeatures;

    @PostConstruct
    public void init(){
        loadAllFeatures();
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carId = Integer.parseInt(requestParameters.get("carId"));
        this.car = carsDAO.findOne(carId);
        this.carFeatures = carsDAO.findOneCarFeatures(carId);
    }

    /*@Transactional
    public String addFeature() {
        carToCreate.setDriver(this.driver);
        carsDAO.(carToCreate);
        return "cars?faces-redirect=true&driverId=" + this.driver.getId();
    }*/

    private void loadAllFeatures(){
        this.allFeatures = featuresDAO.loadAll();
    }
}
