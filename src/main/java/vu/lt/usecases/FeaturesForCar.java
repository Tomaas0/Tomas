package vu.lt.usecases;

import lombok.Getter;
import lombok.Setter;
import vu.lt.entities.Car;
import vu.lt.entities.Feature;
import vu.lt.persistence.CarsDAO;
import vu.lt.persistence.FeaturesDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import vu.lt.interceptors.LoggedInvocation;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;
import java.util.List;

@ViewScoped
@Named
@Getter @Setter
public class FeaturesForCar implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private FeaturesDAO featuresDAO;

    @Getter
    private Car car;

    @Getter @Setter
    private Integer featureid;

    @Getter @Setter
    private Feature featureToAdd = new Feature();

    @Getter @Setter
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

    @Transactional
    public String addFeature() {
        this.featureToAdd = featuresDAO.findOne(featureid);
        this.carFeatures.add(featureToAdd);
        this.car.setFeatures(this.carFeatures);
        carsDAO.merge(this.car);
        return "car?faces-redirect=true&carId=" + this.car.getId();
    }

    @Transactional
    @LoggedInvocation
    public String updateLicensePlate() {
        try{
            carsDAO.merge(this.car);
        } catch (OptimisticLockException e) {
            return "/car.xhtml?faces-redirect=true&carId=" + this.car.getId() + "&error=optimistic-lock-exception";
        }
        return "cars.xhtml?driverId=" + this.car.getDriver().getId() + "&faces-redirect=true";
    }

    private void loadAllFeatures(){
        this.allFeatures = featuresDAO.loadAll();
    }
}
