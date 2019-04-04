package vu.lt.persistence;

import vu.lt.entities.Feature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class FeaturesDAO {

    @Inject
    private EntityManager em;

    public void persist(Feature feature){
        this.em.persist(feature);
    }
}
