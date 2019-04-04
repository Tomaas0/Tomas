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
public class Features implements Serializable {

    @Inject
    private FeaturesDAO featuresDAO;

    @Getter @Setter
    private Feature featureToCreate = new Feature();

    @Getter
    private List<Feature> allFeatures;

    @PostConstruct
    public void init(){
        loadAllFeatures();
    }

    @Transactional
    public String createFeature() {
        featuresDAO.persist(featureToCreate);
        return "features?faces-redirect=true";
    }

    private void loadAllFeatures(){
        this.allFeatures = featuresDAO.loadAll();
    }
}
