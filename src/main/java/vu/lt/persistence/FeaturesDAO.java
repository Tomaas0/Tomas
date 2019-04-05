package vu.lt.persistence;

import vu.lt.entities.Feature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class FeaturesDAO {

    @Inject
    private EntityManager em;

    public List<Feature> loadAll() {
        return em.createNamedQuery("Feature.findAll", Feature.class).getResultList();
    }

    public void persist(Feature feature){
        this.em.persist(feature);
    }

    public Feature findOne(Integer id) {
        return em.find(Feature.class, id);
    }
}
