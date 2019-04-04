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
import java.util.List;
import java.util.Map;

@Model
public class FeaturesForCars implements Serializable {

    @Inject
    private CarsDAO carsDAO;

    @Inject
    private FeaturesDAO featuresDAO;

    @Getter @Setter
    private List<Car> cars;

    @Getter @Setter
    private Feature featureToCreate = new Feature();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer carId = Integer.parseInt(requestParameters.get("carId"));
    }

    @Transactional
    public String createFeature() {
        featuresDAO.persist(featureToCreate);
        return "index?faces-redirect=true";
    }
}
