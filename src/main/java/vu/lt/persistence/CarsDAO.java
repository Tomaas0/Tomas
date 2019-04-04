package vu.lt.persistence;

import vu.lt.entities.Car;
import vu.lt.entities.Feature;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@ApplicationScoped
public class CarsDAO {

    @Inject
    private EntityManager em;

    public void persist(Car car){
        this.em.persist(car);
    }

    public Car findOne(Integer id) {
        return em.find(Car.class, id);
    }

    public List<Feature> findOneCarFeatures(Integer id) {
        Query query = em.createNamedQuery("Car.findFeatures").setParameter("carid", id);
        return query.getResultList();
    }
}
